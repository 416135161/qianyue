/**  
 * @author ningsj@shishike.com
 * @Title: SelfCenterAct.java 
 * @Package com.internet.act 
 * @Description: TODO
 * @date 2015-11-26 上午10:10:02 
 * @version V1.0  
 */
package com.internet.act;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import cn.jpush.android.api.JPushInterface;

import com.internet.action.ActionExit;
import com.internet.action.ActionRefresh;
import com.internet.app.MainApplication;
import com.internet.basic.BasicActivity;
import com.internet.db.SpHelper;
import com.internet.http.api.ApiException;
import com.internet.http.api.ApiManager;
import com.internet.http.data.post.FindUserPost;
import com.internet.http.data.vo.UserInfoVO;
import com.internet.turnright.b.R;
import com.internet.util.FileUtils;
import com.internet.util.PicUtil;
import com.internet.view.HeaderView;

/**
 * @Author: ningsj@shishike.com
 * @Date：2015-11-26 上午10:10:02
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All rights reserved.
 */

@EActivity(R.layout.act_self_center)
public class SelfCenterAct extends BasicActivity {

	@ViewById
	HeaderView view_header;

	@ViewById
	TextView text_name, text_phone, text_teacher_state;

	@ViewById
	com.internet.view.CircleImageView image_header;

	UserInfoVO userInfo;

	@AfterViews
	void init() {
		registerEventBus();
		view_header.setTitle("我的信息");
		userInfo = MainApplication.getInstance().getUserInfo();
		if (userInfo != null)
			initUserView();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		showHead();
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
			showToast(e.getErrorMessage());
		}
		// closeLoading();
		showHead();
		initUserView();
	}

	@Click(R.id.view_self)
	void clickSelf() {
		if (userInfo != null) {
			SelfInfoEditAct_.intent(this).start();
		} else
			showToast("我的信息没有获取到！");

	}

	@Click(R.id.view_teacher)
	void clickTeacher() {

		if (userInfo != null) {
			int authStatus = userInfo.getAuthStatus();
			if (authStatus == 0 || authStatus == 2) {
				SelfTeacherEditAct_.intent(this).start();
			} else if (authStatus == 3) {
				SelfTeacherShowAct_.intent(this).start();
			}else if(authStatus == 1 ){
				findUser2();
			}
		} else
			showToast("教练信息没有获取到！");

	}
	
	@Background
	void findUser2() {
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
			showToast(e.getErrorMessage());
		}
		 closeLoading();
		showHead();
		initUserView();
		SelfTeacherShowAct_.intent(this).start();
	}

	@Click(R.id.view_safe)
	void clickSafe() {
		// SelfSafeAct_.intent(this).start();
	}

	@Click(R.id.view_help)
	void clickHelp() {
		// SelfSafeAct_.intent(this).start();
		FeekBackAct_.intent(this).start();
	}

	@Click(R.id.view_about)
	void clickAbout() {
		AboutAct_.intent(this).start();
	}

	@Click(R.id.btn_exit)
	void clickExit() {
//		if (!JPushInterface.isPushStopped(getApplication()))
//			JPushInterface.stopPush(getApplicationContext());
		SpHelper.getDefault().removeKey(SpHelper.DRIVER_ID)
				.removeKey(SpHelper.SIGN);
		post(new ActionExit());
		WelcomeAct_.intent(this).start();
		finish();
	}

	@UiThread
	void initUserView() {
		text_name.setText(userInfo.getDriverName());
		text_phone.setText(userInfo.getDriverMobile() + "");
		int authStatus = userInfo.getAuthStatus();
		// 0:未审核 1：审核中；2:审核不通过；3：审核通过
		if (authStatus == 0) {
			text_teacher_state.setText("未认证");
		} else if (authStatus == 1) {
			text_teacher_state.setText("审核中");
		} else if (authStatus == 2) {
			text_teacher_state.setText("审核失败");
		} else if (authStatus == 3) {
			text_teacher_state.setText("已认证");
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

	@UiThread
	void showHead(boolean isShow, Bitmap bitmap) {
		if (isShow) {
			image_header.setImageBitmap(bitmap);
			image_header.setVisibility(View.VISIBLE);
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

	public void onEventMainThread(ActionRefresh action) {
		findUser();
	}

}
