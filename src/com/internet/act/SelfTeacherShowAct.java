package com.internet.act;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.widget.EditText;
import android.widget.TextView;

import com.internet.app.MainApplication;
import com.internet.basic.BasicActivity;
import com.internet.db.SpHelper;
import com.internet.http.api.ApiException;
import com.internet.http.api.ApiManager;
import com.internet.http.data.post.AuthInfoPost;
import com.internet.http.data.response.AuthInfoResponse;
import com.internet.http.data.vo.AuthInfoVO;
import com.internet.http.data.vo.UserInfoVO;
import com.internet.turnright.b.R;
import com.internet.view.HeaderView;

@EActivity(R.layout.act_self_teacher_show)
public class SelfTeacherShowAct extends BasicActivity {
	@ViewById
	HeaderView view_header;
	@ViewById
	TextView text_name, text_no, text_carNo;
	@ViewById
	TextView mCarType, text_state;

	AuthInfoVO mAuthInfo;

	@AfterViews
	void init() {
		view_header.setTitle("教练信息");
		UserInfoVO userInfo = MainApplication.getInstance().getUserInfo();
		int authStatus = userInfo.getAuthStatus();
		if (authStatus == 1) {
			text_state.setText("审核中");
		} else if (authStatus == 3) {
			text_state.setText("已认证");
		}
		getAuthInfo();
	}

	@UiThread
	void initView() {
		if (mAuthInfo != null) {
			text_name.setText(mAuthInfo.getDriverIdentityName());
			text_no.setText(mAuthInfo.getDriverIdentityNo());
			text_carNo.setText(mAuthInfo.getDriverCarPlate());
			mCarType.setText(mAuthInfo.getDriverCarName());
		}

	}

	@Background
	void getAuthInfo() {
		showLoading(null);
		try {
			AuthInfoResponse response = ApiManager.getDefault().getAuthInfo(
					new AuthInfoPost(SpHelper.getDefault().getString(
							SpHelper.SIGN)));
			if (response.getResult() != null)
				mAuthInfo = response.getResult();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
		closeLoading();
		initView();
	}

}
