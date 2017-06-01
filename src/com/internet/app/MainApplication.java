package com.internet.app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.androidannotations.api.BackgroundExecutor;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.internet.db.DBManager;
import com.internet.db.SpHelper;
import com.internet.http.api.ApiException;
import com.internet.http.api.ApiManager;
import com.internet.http.data.post.EditSelfInfoPost;
import com.internet.http.data.vo.UserInfoVO;
import com.internet.util.JsonUtil;
import com.internet.util.ToastUtil;
import com.umeng.socialize.PlatformConfig;

public class MainApplication extends Application {

	private static MainApplication mInstance = null;
	private UserInfoVO userInfo;
	public double mLatitude;
	public double mLongitude;

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		DBManager.getDefault().init(this);
		SpHelper.getDefault().init(this);
		JPushInterface.setDebugMode(true); // 设置开启日志,发布时请关闭日志
		JPushInterface.init(this); // 初始化 JPush

	}

	{

		PlatformConfig.setWeixin("wx61935b0a15885da4",
				"70ca39629888067fe3be5cc974506420");
	}

	public static MainApplication getInstance() {
		return mInstance;
	}

	public UserInfoVO getUserInfo() {
		if (userInfo == null) {
			String json = SpHelper.getDefault().getString(SpHelper.USER_INFO);
			if (json == null)
				return null;
			userInfo = JsonUtil.jsonToObject(json, UserInfoVO.class);
		}
		return userInfo;
	}

	public void setUserInfo(UserInfoVO userInfo) {
		this.userInfo = userInfo;
		SpHelper.getDefault().putString(SpHelper.USER_INFO,
				JsonUtil.objectToJson(userInfo));

	}

	public void startPush() {

	}

	public void setPushTag() {
		String alias = SpHelper.getDefault().getString(SpHelper.USER_TYPE)
				+ "_" + SpHelper.getDefault().getString(SpHelper.DRIVER_ID);

		JPushInterface.setAlias(getApplicationContext(), alias,
				new TagAliasCallback() {

					@Override
					public void gotResult(int arg0, String arg1,
							Set<String> arg2) {
						String logs;
						switch (arg0) {
						case 0:
							logs = "Set tag and alias success";
							break;

						case 6002:
							logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
							if (isConnected(getApplicationContext())) {

							} else {

							}
							break;

						default:
							logs = "Failed with errorCode = " + arg0;
						}
//						ToastUtil.showShortToast(getApplicationContext(),
//								"设置推送别名 " + arg1);
						ToastUtil.showShortToast(getApplicationContext(), logs);
					}

				});

	}

	// 声明AMapLocationClient类对象
	public AMapLocationClient mLocationClient = null;

	public void initLocation() {

		// 初始化定位
		mLocationClient = new AMapLocationClient(getApplicationContext());
		// 设置定位回调监听
		mLocationClient.setLocationListener(mLocationListener);
	}

	public AMapLocationClientOption mLocationOption = null;

	public void startLocation() {
		// 声明mLocationOption对象

		// 初始化定位参数
		mLocationOption = new AMapLocationClientOption();
		// 设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
		mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
		// 设置是否返回地址信息（默认返回地址信息）
		mLocationOption.setNeedAddress(true);
		// 设置是否只定位一次,默认为false
		mLocationOption.setOnceLocation(false);
		// 设置是否强制刷新WIFI，默认为强制刷新
		mLocationOption.setWifiActiveScan(true);
		// 设置是否允许模拟位置,默认为false，不允许模拟位置
		mLocationOption.setMockEnable(false);
		// 设置定位间隔,单位毫秒,默认为2000ms
		mLocationOption.setInterval(2000);
		// 给定位客户端对象设置定位参数
		mLocationClient.setLocationOption(mLocationOption);
		// 启动定位
		mLocationClient.startLocation();
	}

	public void stopLocation() {
		if (mLocationClient != null && mLocationClient.isStarted())
			mLocationClient.stopLocation();// 停止定位
	}

	public void destroyLocation() {
		// 销毁定位客户端：
		// 销毁定位客户端之后，若要重新开启定位请重新New一个AMapLocationClient对象。
		mLocationClient.onDestroy();// 销毁定位客户端。
	}

	// 声明定位回调监听器
	public AMapLocationListener mLocationListener = new AMapLocationListener() {

		@Override
		public void onLocationChanged(AMapLocation amapLocation) {
			if (amapLocation.getErrorCode() == 0) {
				// 定位成功回调信息，设置相关消息
				amapLocation.getLocationType();// 获取当前定位结果来源，如网络定位结果，详见定位类型表
				mLatitude = amapLocation.getLatitude();// 获取纬度
				mLongitude = amapLocation.getLongitude();// 获取经度
				amapLocation.getAccuracy();// 获取精度信息
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date date = new Date(amapLocation.getTime());
				df.format(date);// 定位时间
				amapLocation.getAddress();// 地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
				amapLocation.getCountry();// 国家信息
				amapLocation.getProvince();// 省信息
				amapLocation.getCity();// 城市信息
				amapLocation.getDistrict();// 城区信息
				amapLocation.getStreet();// 街道信息
				amapLocation.getStreetNum();// 街道门牌号信息
				amapLocation.getCityCode();// 城市编码
				amapLocation.getAdCode();// 地区编码

				doSaveLocation(amapLocation);
			} else {
				// 显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
				Log.e("AmapError",
						"location Error, ErrCode:"
								+ amapLocation.getErrorCode() + ", errInfo:"
								+ amapLocation.getErrorInfo());
			}

			stopLocation();

		}

	};

	void doSaveLocation(final AMapLocation amapLocation) {
		BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0, "") {
			@Override
			public void execute() {
				try {

					EditSelfInfoPost data = new EditSelfInfoPost();
					// 签名[必填]
					data.sign = SpHelper.getDefault().getString(SpHelper.SIGN);
					// 教练id
					if (userInfo != null) {
						data.driverId = userInfo.getDriverId();
						data.driverLatitude = amapLocation.getLatitude() + "";
						data.driverLongitude = amapLocation.getLongitude() + "";
						ApiManager.getDefault().editSeltInfo(data, null);
					}
				} catch (ApiException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Throwable e) {
					Thread.getDefaultUncaughtExceptionHandler()
							.uncaughtException(Thread.currentThread(), e);
				}
			}
		});

	}

	public static boolean isConnected(Context context) {
		ConnectivityManager conn = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = conn.getActiveNetworkInfo();
		return (info != null && info.isConnected());
	}

}
