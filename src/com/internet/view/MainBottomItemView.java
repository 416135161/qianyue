package com.internet.view;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.internet.qianyue.R;

@EViewGroup(R.layout.view_main_bottom_item)
public class MainBottomItemView extends LinearLayout {
	@ViewById
	ImageView image;
	
	@ViewById
	TextView text;
	
	public MainBottomItemView(Context context) {
		super(context);
		
	}
	
	public MainBottomItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}
	
	@AfterViews
	void init() {
		setOrientation(VERTICAL);
	}
	
	public void setText(String title) {
		text.setText(title);
	}
	
	public void setSelected(boolean isSelect, int resId) {
		image.setImageResource(resId);
		if (isSelect) {
			text.setTextColor(getResources().getColor(R.color.text_red));
			
		} else {
			text.setTextColor(getResources().getColor(R.color.text_gray));
		}
		
	}
}
