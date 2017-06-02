package com.internet.view;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.app.Instrumentation;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.internet.qianyue.R;

@EViewGroup(R.layout.view_header)
public class HeaderView extends RelativeLayout {

	@ViewById
	TextView text_title;

	@ViewById
	ImageView image_back, image_header_right;

	@ViewById
	TextView text_header_right;

	public HeaderView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public HeaderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public HeaderView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@AfterViews
	void init() {

	}

	@Click(R.id.image_back)
	@Background
	void back() {
		Instrumentation inst = new Instrumentation();
		inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
	}

	public void setTitle(String title) {
		text_title.setText(title);
	}

	public void setRight(String title) {
		text_header_right.setText(title);
	}
	
	public void setRightImage(int resId){
		image_header_right.setVisibility(View.VISIBLE);
		image_header_right.setImageResource(resId);
	}

}
