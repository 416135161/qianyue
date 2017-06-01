package com.internet.act;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.text.TextUtils;
import android.widget.EditText;

import com.internet.basic.BasicActivity;
import com.internet.db.SpHelper;
import com.internet.http.api.ApiException;
import com.internet.http.api.ApiManager;
import com.internet.http.data.post.FeedBackPost;
import com.internet.turnright.b.R;
import com.internet.view.HeaderView;

@EActivity(R.layout.act_feed_back)
public class FeekBackAct extends BasicActivity {
	@ViewById
	HeaderView view_header;

	@ViewById
	EditText mEdit;

	@AfterViews
	void init() {
		view_header.setTitle("意见反馈");
	}

	@Click(R.id.btn_save)
	void clickExit() {
		String info = mEdit.getEditableText().toString();
		if (!TextUtils.isEmpty(info)) {
			doSave(info);
		} else
			showToast("请输入反馈信息");
	}

	@Background
	void doSave(String info) {
		showLoading();
		boolean isOk = false;
		try {
			FeedBackPost data = new FeedBackPost();
			data.setSign(SpHelper.getDefault().getString(SpHelper.SIGN));
			data.setContent(info);
			isOk = ApiManager.getDefault().feedBack(data);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
		closeLoading();
		if (isOk) {
			doOK();
		}
	}

	@UiThread
	void doOK() {
		finish();
	}

}
