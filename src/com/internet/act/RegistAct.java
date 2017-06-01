/**  
 * @author ningsj@shishike.com
 * @Title: RegistAct.java 
 * @Package com.internet.act 
 * @Description: TODO
 * @date 2015-11-20 上午11:09:17 
 * @version V1.0  
 */
package com.internet.act;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.internet.basic.BasicActivity;
import com.internet.http.api.ApiException;
import com.internet.http.api.ApiManager;
import com.internet.http.data.post.RegisterPost;
import com.internet.http.data.post.SendCodePost;
import com.internet.turnright.b.R;

/**
 * @Author: ningsj@shishike.com
 * @Date：2015-11-20 上午11:09:17
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All rights reserved.
 */
@EActivity(R.layout.act_regist)
public class RegistAct extends BasicActivity {

	@ViewById
	EditText mEdit_mobile, mEdit_code, mEdit_pwd;
	@ViewById
	CheckBox checkbox;
	@ViewById
	Button btn_next;
	@ViewById
	Button btn_acquire;

	private String mMobileNo;

	private String mCode;

	private String mPwd;

	@CheckedChange(R.id.checkbox)
	void checkedChange(CompoundButton view) {
		if (view.isChecked()) {
			btn_next.setClickable(true);
			btn_next.setBackgroundResource(R.drawable.btn_red);
		} else {
			btn_next.setClickable(false);
			btn_next.setBackgroundResource(R.drawable.btn_grey);
		}
	}

	@Click(R.id.text_cancel)
	void cancel() {
		doBack();
	}

	@Click(R.id.btn_acquire)
	void acquire() {
		mMobileNo = mEdit_mobile.getEditableText().toString();
		if (!TextUtils.isEmpty(mMobileNo)) {
			sendCode();
		} else
			showToast("请填写手机号码！");

	}

	@Click(R.id.text_protocol)
	void clickProtocol() {
		TeacherProtocolAct_.intent(this).start();
	}

	@Click(R.id.btn_next)
	void next() {
		mMobileNo = mEdit_mobile.getEditableText().toString();
		if (TextUtils.isEmpty(mMobileNo)) {
			showToast("请填写手机号码！");
			return;
		}
		mCode = mEdit_code.getEditableText().toString();
		if (TextUtils.isEmpty(mCode)) {
			showToast("请输入验证码！");
			return;
		}

		mPwd = mEdit_pwd.getEditableText().toString();
		if (TextUtils.isEmpty(mPwd)) {
			showToast("请输入密码！");
			return;
		}
		regist();

	}

	@Background
	void sendCode() {
		showLoading();
		boolean state = false;
		try {
			state = ApiManager.getDefault().sendCode(
					new SendCodePost(mMobileNo));
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
		if (state) {
			showToast("验证码已经发送");
			sendCodeInvalid();
		}
		closeLoading();
	}
	
	@UiThread
	void sendCodeInvalid() {
		btn_acquire.setText("120s后可重发");
		btn_acquire.setClickable(false);
		sendCodeValid();
	}

	private int i = 120;

	@UiThread(delay = 1 * 1000)
	void sendCodeValid() {
		if (i-- > 0) {
			btn_acquire.setText(i + "s后可重发");
			sendCodeValid();
		} else {
			i = 120;
			btn_acquire.setText("发送验证码");
			btn_acquire.setClickable(true);
		}
	}

	@Background
	void regist() {
		showLoading();
		boolean state = false;
		try {

			state = ApiManager.getDefault().register(
					new RegisterPost(mMobileNo, mPwd, mCode));
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
		closeLoading();
		if (state) {
			showToast("注册成功");
			// goToRegistDspAct();
			goToMainAct();
		}

	}

	@UiThread
	void goToRegistDspAct() {
		RegistDspAct_.intent(this).start();
		finish();
	}

	@UiThread
	void goToMainAct() {
		MainAct_.intent(this).start();
		finish();
	}
}
