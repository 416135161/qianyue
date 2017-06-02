package com.internet.dialog;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.app.DialogFragment;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.internet.qianyue.R;

/**
 * Created by Administrator on 2014-10-17.
 */
@EFragment(R.layout.dialog_loading)
public class DialogLoading extends DialogFragment {
	@ViewById
	ImageView mLoading;
	@ViewById
	TextView text_tip;
	String tip;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NORMAL, android.R.style.Theme_Translucent_NoTitleBar);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onResume()
	 */
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		text_tip.setText(tip);
		((AnimationDrawable) mLoading.getDrawable()).start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onPause()
	 */
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		((AnimationDrawable) mLoading.getDrawable()).stop();
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

}