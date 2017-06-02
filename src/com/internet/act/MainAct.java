/**  
 * @author ningsj@shishike.com
 * @Title: MainAct.java 
 * @Package com.internet.act 
 * @Description: TODO
 * @date 2015-11-20 下午4:20:08 
 * @version V1.0  
 */
package com.internet.act;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.jpush.android.api.JPushInterface;

import com.internet.action.ActionExit;
import com.internet.action.ActionLoginOK;
import com.internet.adapter.ViewPagerAdapter;
import com.internet.app.MainApplication;
import com.internet.basic.BasicActivity;
import com.internet.db.SpHelper;
import com.internet.http.api.ApiException;
import com.internet.http.api.ApiManager;
import com.internet.http.data.post.FindUserPost;
import com.internet.http.data.post.GetBannerPost;
import com.internet.http.data.post.GetMessageQuntityPost;
import com.internet.http.data.response.GetBannerResponse;
import com.internet.http.data.response.GetBannerResponse.Banner;
import com.internet.http.data.vo.UserInfoVO;
import com.internet.qianyue.R;
import com.internet.util.FileUtils;
import com.internet.util.PicUtil;
import com.internet.view.MainBottomItemView;

/**
 * @Author: ningsj@shishike.com
 * @Date：2015-11-20 下午4:20:08
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All rights reserved.
 */

@EActivity(R.layout.act_main)
public class MainAct extends BasicActivity {
	@ViewById
	MainBottomItemView text_bottom_home, text_bottom_order, text_bottom_exam,
			text_bottom_income;
	@ViewById
	com.internet.view.CircleImageView image_header;
	@ViewById
	TextView text_tip;

	UserInfoVO userInfo;

	private ViewPager viewPager; // 对应的viewPager

	private ArrayList<View> viewList;// view数组

	private ViewPagerAdapter pagerAdapter;

	@AfterViews
	void init() {
		registerEventBus();
		initBanner();
		text_bottom_home.setText("首页");
		text_bottom_order.setText("我的订单");
		text_bottom_exam.setText("预约考试");
		text_bottom_income.setText("收入");
		text_bottom_home
				.setSelected(true, R.drawable.main_bottom_1_selected_bg);
		text_bottom_order
				.setSelected(false, R.drawable.main_bottom_2_normal_bg);
		text_bottom_exam.setSelected(false, R.drawable.main_bottom_3_normal_bg);
		text_bottom_income.setSelected(false,
				R.drawable.main_bottom_4_normal_bg);

		getBanner();
		post(new ActionLoginOK());
		MainApplication.getInstance().initLocation();
		MainApplication.getInstance().startLocation();
		MainApplication.getInstance().setPushTag();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		JPushInterface.onResume(getApplicationContext());
		findUser();
		getMessageQuntity();

	}

	private void initBanner() {
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
		pagerAdapter = new ViewPagerAdapter(viewList);
		viewPager.setAdapter(pagerAdapter);
	}

	@UiThread
	void inflatePageView(ArrayList<Banner> result) {

		if (result != null && result.size() > 0) {
			for (int i = 0; i < result.size(); i++) {
				Banner banner = result.get(i);
				LayoutInflater inflater = getLayoutInflater();
				View pageItem = inflater.inflate(R.layout.page_item, null);
				ImageView imageView = (ImageView) pageItem
						.findViewById(R.id.image_item);
				viewList.add(pageItem);
				getBitmap(banner.getImg(), imageView);
			}
			pagerAdapter.notifyDataSetChanged();
		}

	}

	@Background
	void getBitmap(String url, ImageView imageView) {

		String namePath = getFileName(FileUtils.getLocalFileName(url));
		Bitmap bitmap;
		if (!FileUtils.checkFileExist(namePath)) {
			bitmap = PicUtil.getbitmap(url);
			if (bitmap != null) {
				saveMyBitmap(namePath, bitmap, 100);
				showPageItem(imageView, bitmap);
			}
		} else {
			bitmap = BitmapFactory.decodeFile(namePath);
			if (bitmap != null) {
				showPageItem(imageView, bitmap);
			}
		}
	}

	@UiThread
	void showPageItem(ImageView imageView, Bitmap bitmap) {
		imageView.setImageBitmap(bitmap);
	}

	@Click(R.id.view_message)
	void clickTip() {
		MyMessageAct_.intent(this).start();
	}

	@Click({ R.id.text_make_order, R.id.text_bottom_order })
	void makeOrder() {
		OrderMainAct_.intent(this).start();
	}

	@Click({ R.id.text_site, R.id.text_road, R.id.text_escort })
	void realseInfor(View v) {
		String typeCode = "1";
		switch (v.getId()) {
		case R.id.text_site:
			typeCode = "1";
			break;
		case R.id.text_road:
			typeCode = "2";
			break;
		case R.id.text_escort:
			typeCode = "3";
			break;
		}
		UserInfoVO userInfo = MainApplication.getInstance().getUserInfo();
		if (userInfo != null && userInfo.getAuthStatus() == 3) {
			Intent intent = new Intent(this, PublishAct_.class);
			intent.putExtra("scheduleTypeCode", typeCode);
			startActivity(intent);
		} else {
			findUser2(typeCode);
		}

	}

	@Background
	void findUser2(String typeCode) {
		showLoading();
		try {
			FindUserPost data = new FindUserPost();
			data.setDriverId(SpHelper.getDefault()
					.getString(SpHelper.DRIVER_ID));
			userInfo = ApiManager.getDefault().findUser(data);
			MainApplication.getInstance().setUserInfo(userInfo);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(e.getErrorCode() == 888888){
				showToast("用户登录过期!");
				goToLogin();
				return;
			}
		}
		closeLoading();
		goPublic(typeCode);
	}

	@UiThread
	void goPublic(String typeCode) {
		UserInfoVO userInfo = MainApplication.getInstance().getUserInfo();
		if (userInfo != null && userInfo.getAuthStatus() == 3) {
			Intent intent = new Intent(this, PublishAct_.class);
			intent.putExtra("scheduleTypeCode", typeCode);
			startActivity(intent);
		} else {
			showToast("请先认证教练信息！");
		}
	}

	@Click(R.id.text_my_student)
	void myStudent() {
		MyStudentAct_.intent(this).start();
	}

	@Click({ R.id.text_my_income, R.id.text_bottom_income })
	void myIncome() {
		showToast("此功能暂不开放");
	}

	@Click(R.id.text_bottom_home)
	void home() {

	}

	@Click(R.id.text_bottom_exam)
	void makeExam() {

	}

	@Click(R.id.image_header)
	void imagePhoto() {
		SelfCenterAct_.intent(this).start();
	}

	@UiThread
	void showHead(boolean isShow, Bitmap bitmap) {
		if (isShow) {
			image_header.setImageBitmap(bitmap);
			image_header.setVisibility(View.VISIBLE);
		} else {
			image_header.setImageResource(R.drawable.user_tip);
		}
	}

	@Background
	void findUser() {
		// showLoading();
		try {
			FindUserPost data = new FindUserPost();
			data.setDriverId(SpHelper.getDefault()
					.getString(SpHelper.DRIVER_ID));
			userInfo = ApiManager.getDefault().findUser(data);
			MainApplication.getInstance().setUserInfo(userInfo);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(e.getErrorCode() == 888888){
				showToast("用户登录过期!");
				goToLogin();
				return;
			}
		}
		// closeLoading();
		showHead();
	}
	@UiThread
	void goToLogin(){
		if (!JPushInterface.isPushStopped(getApplication()))
			JPushInterface.stopPush(getApplicationContext());
		SpHelper.getDefault().removeKey(SpHelper.DRIVER_ID)
				.removeKey(SpHelper.SIGN);
		post(new ActionExit());
		WelcomeAct_.intent(this).start();
		finish();
	}

	@Background
	void showHead() {
		if (MainApplication.getInstance().getUserInfo() != null
				&& !TextUtils.isEmpty(MainApplication.getInstance()
						.getUserInfo().getDriverAvatar())) {
			String url = MainApplication.getInstance().getUserInfo()
					.getDriverAvatar();
			String namePath = getFileName(FileUtils.getLocalFileName(url));
			System.out.println(this.getClass().getName() + namePath);
			if (!FileUtils.checkFileExist(namePath)) {
				System.out.println(this.getClass().getName() + "hhhh");
				Bitmap bitmap = PicUtil.getbitmap(url);
				if (bitmap != null) {
					showHead(true, bitmap);
					saveMyBitmap(namePath, bitmap, 100);
				}
			} else {
				Bitmap bitmap = BitmapFactory.decodeFile(namePath);
				System.out.println(this.getClass().getName() + "vvvvv");
				if (bitmap != null) {
					showHead(true, bitmap);
					System.out.println(this.getClass().getName() + "jjjjjj");
				}
			}
		} else {
			showHead(false, null);
		}

	}

	public void saveMyBitmap(String fileName, Bitmap mBitmap, int quality) {
		File f = new File(fileName);
		try {
			f.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		mBitmap.compress(Bitmap.CompressFormat.JPEG, quality, fOut);
		try {
			fOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getFileName(String photoName) {
		String saveDir = Environment.getExternalStorageDirectory() + "/myPic";
		File dir = new File(saveDir);
		if (!dir.exists()) {
			dir.mkdir(); // 创建文件夹
		}
		// 用日期作为文件名，确保唯一性
		if (photoName == null) {
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd_HH-mm-ss");
			photoName = formatter.format(date) + ".PNG";
		}

		String fileName = saveDir + "/" + photoName;
		return fileName;
	}

	@Background
	void getMessageQuntity() {
		try {
			GetMessageQuntityPost data = new GetMessageQuntityPost();
			data.setSign(SpHelper.getDefault().getString(SpHelper.SIGN));
			data.setStatus("1");
			int quntity = ApiManager.getDefault().getMessageQuntity(data);
			showMessageQuntity(quntity);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
	}

	@UiThread
	void showMessageQuntity(int quntity) {
		text_tip.setText(quntity + "");
		if (quntity > 0)
			text_tip.setVisibility(View.VISIBLE);
		else
			text_tip.setVisibility(View.INVISIBLE);
	}

	@Background
	void getBanner() {
		GetBannerResponse response = null;
		try {
			GetBannerPost data = new GetBannerPost();
			response = ApiManager.getDefault().getBannerList(data);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}

		if (response != null && response.getResult() != null) {
			inflatePageView(response.getResult());
		}
	}

	public void onEventMainThread(ActionExit action) {
		finish();
	}

}
