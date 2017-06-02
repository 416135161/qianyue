/**  
 * @author ningsj@shishike.com
 * @Title: SelfInfoEdit.java 
 * @Package com.internet.act 
 * @Description: TODO
 * @date 2015-11-26 下午5:10:34 
 * @version V1.0  
 */
package com.internet.act;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Touch;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.method.MovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.internet.action.ActionRefresh;
import com.internet.action.ActionSelectArea;
import com.internet.action.ActionSelectBirthday;
import com.internet.action.ActionSelectPhoto;
import com.internet.app.MainApplication;
import com.internet.basic.BasicActivity;
import com.internet.db.SpHelper;
import com.internet.dialog.DialogLoading_;
import com.internet.dialog.SelectBirthdayDialog;
import com.internet.dialog.SelectBirthdayDialog_;
import com.internet.dialog.SelectPhotoDialog_;
import com.internet.http.api.ApiException;
import com.internet.http.api.ApiManager;
import com.internet.http.data.post.EditSelfInfoPost;
import com.internet.http.data.post.FindUserPost;
import com.internet.http.data.vo.UserInfoVO;
import com.internet.qianyue.R;
import com.internet.util.DateTimeUtil;
import com.internet.util.FileUtils;
import com.internet.util.PicUtil;
import com.internet.view.HeaderView;
import com.internet.view.SelectBirthdayView;
import com.internet.view.SelectPhotoView;

/**
 * @Author: ningsj@shishike.com
 * @Date：2015-11-26 下午5:10:34
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All rights reserved.
 */
@EActivity(R.layout.act_self_info_edit)
public class SelfInfoEditAct extends BasicActivity {
	public static final int MAIL = 0;
	public static final int FEMAIL = 1;

	@ViewById
	HeaderView view_header;

	@ViewById
	com.internet.view.CircleImageView image_header;

	@ViewById
	TextView text_sex, text_birthday, text_address;

	@ViewById
	EditText text_age, text_school, text_home;

	UserInfoVO userInfo;

	@AfterViews
	void init() {
		registerEventBus();
		initView();
		
	}

	@UiThread
	void initView() {
		userInfo = MainApplication.getInstance().getUserInfo();
		showHead();
		view_header.setTitle("个人信息");
		view_header.setRight("保存");
		setSex(userInfo.getDriverSex());
		long birthday = userInfo.getDriverBirthday() > 0 ? userInfo
				.getDriverBirthday() : Calendar.getInstance().getTimeInMillis();
		text_age.setText(userInfo.getDriverTeachAge() + "");

		if (!TextUtils.isEmpty(userInfo.getDriverProvinceName())) {
			String address = userInfo.getDriverProvinceName() + "-"
					+ userInfo.getDriverCityName() + "-"
					+ userInfo.getDriverAreaName();
			text_address.setText(address);
		}
		if (!TextUtils.isEmpty(userInfo.getDriverAddr())) {
			text_home.setText(userInfo.getDriverAddr());
		}
		if (!TextUtils.isEmpty(userInfo.getTrainingSchoolName())) {
			text_school.setText(userInfo.getTrainingSchoolName());
		}
		onEventMainThread(new ActionSelectBirthday(birthday));
	}

	@Click(R.id.view_address)
	void clickAddress() {
		SelfInfoSelectCityAct_.intent(this).start();
	}

	@Click(R.id.text_header_right)
	void clickSave() {
		doSave();
	}

	@Click(R.id.view_image_header)
	void clickViewImageHeader() {

		SelectPhotoDialog_.builder().build().show(getFragmentManager(), null);
	}
	SelectBirthdayDialog dlg;
	@Click(R.id.text_birthday)
	void clickBirthday() {
		if(dlg == null){
			dlg = SelectBirthdayDialog_.builder().build();
			long birthday = userInfo.getDriverBirthday() > 0 ? userInfo
					.getDriverBirthday() : Calendar.getInstance().getTimeInMillis();
			dlg.initDate(birthday);
		}
		dlg.show(getFragmentManager(), null);
	}

	@Click(R.id.text_sex)
	void clickSex() {
		if (text_sex.getTag() != null) {
			int sex = (Integer) text_sex.getTag();
			if (sex == MAIL) {
				setSex(FEMAIL);
			} else {
				setSex(MAIL);
			}
		}
	}

	private void setSex(int sex) {
		if (sex == MAIL) {
			text_sex.setText("男");
			text_sex.setTag(MAIL);
		} else if (sex == FEMAIL) {
			text_sex.setText("女");
			text_sex.setTag(FEMAIL);
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// 完成照相后回调用此方法
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case 1:
			switch (resultCode) {
			case Activity.RESULT_OK:// 照相完成点击确定
				Bundle bundle = data.getExtras();
				Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
				saveMyBitmap(getFileName(null), bitmap, 80);
				showHead(true, bitmap);
				break;
			case Activity.RESULT_CANCELED:// 取消
				break;
			}
			break;
		case 2:
			switch (resultCode) {
			case Activity.RESULT_OK:
				Uri uri = data.getData();
				Cursor cursor = this.getContentResolver().query(uri, null,
						null, null, null);
				cursor.moveToFirst();
				String imgPath = cursor.getString(1); // 图片文件路径
				cursor.close();
				Options options = new BitmapFactory.Options();
				options.inJustDecodeBounds = false;
				options.inSampleSize = 5;
				Bitmap bitmap = BitmapFactory.decodeFile(imgPath, options);
				saveMyBitmap(getFileName(null), bitmap, 60);
				showHead(true, bitmap);
				break;
			case Activity.RESULT_CANCELED:// 取消
				break;
			}
			break;
		}
	}

	public void onEventMainThread(ActionSelectPhoto action) {
		switch (action.type) {
		case ActionSelectPhoto.PZ:
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 调用android自带的照相机
			startActivityForResult(intent, 1);
			break;
		case ActionSelectPhoto.XC:
			Intent i = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);// 调用android的图库
			startActivityForResult(i, 2);
			break;
		}
	}

	private ActionSelectBirthday mActionSeletBirthday;

	public void onEventMainThread(ActionSelectBirthday action) {
		mActionSeletBirthday = action;
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(action.getBirthday());
		int year = calendar.get(Calendar.YEAR);
		int monthOfYear = calendar.get(Calendar.MONTH);
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		text_birthday.setText(year + "年" + (monthOfYear + 1) + "月" + dayOfMonth
				+ "日");
	}

	private ActionSelectArea mActionSelectArea;

	public void onEventMainThread(ActionSelectArea action) {
		String address = action.getProvince().getName() + "-"
				+ action.getCity().getName() + "-" + action.getArea().getName();
		text_address.setText(address);
		mActionSelectArea = action;
	}

	@UiThread
	void showHead(boolean isShow, Bitmap bitmap) {
		if (isShow) {
			image_header.setImageBitmap(bitmap);
		}
	}

	@Background
	void showHead() {
		if (userInfo != null && !TextUtils.isEmpty(userInfo.getDriverAvatar())) {
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
		}
	}

	@Background
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

	String mNamePath = null;

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
		mNamePath = fileName;
		return fileName;
	}

	@Background
	void doSave() {
		showLoading();
		boolean state = false;
		try {
			EditSelfInfoPost data = new EditSelfInfoPost();
			// 签名[必填]
			data.sign = SpHelper.getDefault().getString(SpHelper.SIGN);
			// 教练id
			data.driverId = userInfo.getDriverId();
			// 姓名
			data.driverName = userInfo.getDriverName();
			// 性别
			data.driverSex = (Integer) text_sex.getTag() + "";
			long birthday = mActionSeletBirthday != null ? mActionSeletBirthday
					.getBirthday() : userInfo.getDriverBirthday();
			data.driverBirthday = DateTimeUtil.formatDate(birthday);
			// 教龄
			data.driverTeachAge = text_age.getText().toString();
			if (mActionSelectArea != null) {
				// 省Code
				data.driverProvinceCode = mActionSelectArea.getProvince()
						.getRegionId();
				// 省名称
				data.driverProvinceName = mActionSelectArea.getProvince()
						.getName();
				// 市Code
				data.driverCityCode = mActionSelectArea.getCity().getRegionId();
				// 市名称
				data.driverCityName = mActionSelectArea.getCity().getName();
				// 区Code
				data.driverAreaCode = mActionSelectArea.getArea().getRegionId();
				// 区名称
				data.driverAreaName = mActionSelectArea.getArea().getName();
			} else {
				// 省Code
				data.driverProvinceCode = userInfo.getDriverProvinceCode();
				// 省名称
				data.driverProvinceName = userInfo.getDriverProvinceName();
				// 市Code
				data.driverCityCode = userInfo.getDriverCityCode();
				// 市名称
				data.driverCityName = userInfo.getDriverCityName();
				// 区Code
				data.driverAreaCode = userInfo.getDriverAreaCode();
				// 区名称
				data.driverAreaName = userInfo.getDriverAreaName();
			}
			// 地址
			data.driverAddr = text_home.getText().toString();
			// 所属驾校
			data.trainingSchoolName = text_school.getText().toString();
			state = ApiManager.getDefault().editSeltInfo(data, mNamePath);
			if (state) {
				doOk();
			}

		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
		closeLoading();
	}

	@UiThread
	void doOk() {
		post(new ActionRefresh());
		finish();
	}

}
