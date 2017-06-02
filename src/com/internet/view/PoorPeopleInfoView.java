package com.internet.view;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.internet.qianyue.R;

@EViewGroup(R.layout.view_poor_people_info)
public class PoorPeopleInfoView extends LinearLayout {

	@ViewById
	TextView text_content, text_title;

	public PoorPeopleInfoView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public PoorPeopleInfoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public PoorPeopleInfoView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public void setText(String text) {
		text_content.setText(text);
	}

	public void setTitle(String text) {
		text_title.setText(text);
	}

}
