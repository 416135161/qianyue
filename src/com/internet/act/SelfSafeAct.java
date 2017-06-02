package com.internet.act;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.text.TextUtils;
import android.widget.EditText;

import com.internet.basic.BasicActivity;
import com.internet.http.api.ApiException;
import com.internet.http.api.ApiManager;
import com.internet.http.data.post.RegisterPost;
import com.internet.http.data.post.SendCodePost;
import com.internet.qianyue.R;
import com.internet.view.HeaderView;


@EActivity(R.layout.act_self_safe)
public class SelfSafeAct extends BasicActivity {

	@ViewById
	HeaderView view_header;
	
	@ViewById
	EditText mEdit_mobile, mEdit_code;
	
	private String mMobileNo;

	private String mCode;
	
	@AfterViews
	void init() {
		view_header.setTitle("安全设置");
		view_header.setRight("保存");
	}
	
	@Click(R.id.btn_acquire)
	void acquire() {
		mMobileNo = mEdit_mobile.getEditableText().toString();
		if (!TextUtils.isEmpty(mMobileNo)) {
			sendCode();
		} else
			showToast("请填写手机号码！");

	}

	@Click(R.id.text_header_right)
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

		doSave();

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
			// isSendding = true;
			// goToVerifyModel();
			// reSendCode();
		}
		closeLoading();
	}

	@Background
	void doSave() {
		showLoading();
		boolean state = false;
		try {

			state = ApiManager.getDefault().register(
					new RegisterPost(mMobileNo, null, mCode));
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
		closeLoading();
		if (state) {
			showToast("注册成功");
			// goToRegistDspAct();
			
		}

	}
}
