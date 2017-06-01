/**  
 * @author ningsj@shishike.com
 * @Title: ReleaseSiteDateItemView.java 
 * @Package com.internet.view 
 * @Description: TODO
 * @date 2015-11-25 上午11:19:04 
 * @version V1.0  
 */
package com.internet.view;

import java.util.Calendar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.internet.turnright.b.R;

/**
 * @Author: ningsj@shishike.com
 * @Date：2015-11-25 上午11:19:04
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All rights reserved.
 */
@EViewGroup(R.layout.view_release_site_data_item)
public class ReleaseSiteDateItemView extends LinearLayout {
	@ViewById
	TextView text_week, text_date;

	@ViewById
	View view_flag;

	@ViewById
	ImageView image_point;

	boolean isAppointed = false;
	int mPosition = 0;

	public ReleaseSiteDateItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public ReleaseSiteDateItemView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@AfterViews
	void init() {
		setOrientation(LinearLayout.VERTICAL);

	}

	public void setText(int position) {
		this.mPosition = position;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, position - 1);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		text_date.setText(dayOfMonth + "");
		String week = "";
		switch (dayOfWeek) {
		case 1:
			week = "周日";
			break;
		case 2:
			week = "周一";
			break;
		case 3:
			week = "周二";
			break;
		case 4:
			week = "周三";
			break;
		case 5:
			week = "周四";
			break;
		case 6:
			week = "周五";
			break;
		case 7:
			week = "周六";
			break;
		}
		text_week.setText(week);
	}

	public void setNormal() {
		text_date.setTextColor(getResources().getColor(R.color.text_black));
		text_date.setBackgroundColor(getResources().getColor(R.color.bg_empty));
		view_flag.setBackgroundColor(getResources().getColor(R.color.bg_empty));
	}

	public void setSelected() {

		text_date.setTextColor(getResources().getColor(R.color.text_white));
		text_date.setBackgroundResource(R.drawable.main_message_tip_bg);
		view_flag.setBackgroundColor(getResources().getColor(R.color.bg_red));
	}

	public void setAppointFlag() {
		image_point.setVisibility(View.VISIBLE);
		isAppointed = true;
	}

	public boolean isAppointed() {
		return isAppointed;
	}

	public int getPosition() {
		return this.mPosition;
	}
}
