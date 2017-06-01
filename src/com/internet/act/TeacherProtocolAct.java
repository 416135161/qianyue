package com.internet.act;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.webkit.WebView;

import com.internet.basic.BasicActivity;
import com.internet.turnright.b.R;
import com.internet.view.HeaderView;

@EActivity(R.layout.act_teacher_protocol)
public class TeacherProtocolAct extends BasicActivity {

	@ViewById
	HeaderView view_header;

	@ViewById
	WebView mWebView;

	@AfterViews
	void init() {
		view_header.setTitle("教练协议");
		mWebView.setBackgroundColor(0); // 设置背景色
		mWebView.getBackground().setAlpha(0); // 设置填充透明度
												// 范围：0-255
		mWebView.loadUrl("http://www.youzhuanwan.cn/regprotocol.html");
	}
}
