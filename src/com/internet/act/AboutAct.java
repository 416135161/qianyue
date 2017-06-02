package com.internet.act;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.internet.basic.BasicActivity;
import com.internet.http.api.ApiException;
import com.internet.http.api.ApiManager;
import com.internet.http.data.response.GetYzwMessageResponse;
import com.internet.http.data.response.GetYzwMessageResponse.YzwMessage;
import com.internet.qianyue.R;
import com.internet.util.AndroidUtil;
import com.internet.view.HeaderView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

@EActivity(R.layout.act_about)
public class AboutAct extends BasicActivity {
	@ViewById
	HeaderView view_header;
	@ViewById
	TextView text_version, text_introduce, text_phone;
	@ViewById
	View view_share;

	@AfterViews
	void init() {
		view_header.setTitle("关于右转弯");
		text_version.setText("右转弯教练端 "
				+ AndroidUtil.getVersionName(getApplicationContext()));
		getMessage();
	}

	@Background
	void getMessage() {
		showLoading("数据获取中...");
		try {

			GetYzwMessageResponse response = ApiManager.getDefault()
					.getYzwMessage();
			if (response != null && response.getResult() != null) {
				if (response.getResult() != null)
					setTextView(response.getResult());
			}
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast(e.getErrorMessage());

		}
		closeLoading();
	}

	@UiThread
	void setTextView(YzwMessage result) {
		text_introduce.setText(result.introduce);
		text_phone.setText(result.phone);
	}

	@Click(R.id.view_phone)
	void clickPhone() {
		if (!TextUtils.isEmpty(text_phone.getText())) {
			Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
					+ text_phone.getText()));
			startActivity(intent);
		}
	}

	@Click(R.id.view_recommend)
	void clickRecommend() {
		view_share.setVisibility(View.VISIBLE);

	}

	@Click({ R.id.app_share_wx, R.id.app_share_wx_circle, R.id.text_qx })
	void clickShareItem(View view) {
		// Config.OpenEditor = true;

		switch (view.getId()) {

		case R.id.app_share_wx:

			new ShareAction(this)
					.setPlatform(SHARE_MEDIA.WEIXIN)
					.setCallback(umShareListener)
					.withText(
							"右转弯(www.youzhuanwan.cn),专注驾驶培训预约及考试预约.为您提供多样化的学车方案，让您随时学，随地学，随车学!")
					.share();
			break;
		case R.id.app_share_wx_circle:
			new ShareAction(this)
					.setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
					.setCallback(umShareListener)
					.withText(
							"右转弯(www.youzhuanwan.cn),专注驾驶培训预约及考试预约.为您提供多样化的学车方案，让您随时学，随地学，随车学!")
					.share();
			break;
		case R.id.text_qx:
			view_share.setVisibility(View.GONE);
			break;
		}
	}

	private UMShareListener umShareListener = new UMShareListener() {
		@Override
		public void onResult(SHARE_MEDIA platform) {
			Toast.makeText(AboutAct.this, platform + " 分享成功啦",
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onError(SHARE_MEDIA platform, Throwable t) {
			Toast.makeText(AboutAct.this, platform + " 分享失败啦",
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onCancel(SHARE_MEDIA platform) {
			Toast.makeText(AboutAct.this, platform + " 分享取消了",
					Toast.LENGTH_SHORT).show();
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		/** attention to this below ,must add this **/
		UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
	}
}
