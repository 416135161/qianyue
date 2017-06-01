package com.internet.view;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.internet.action.ActionOrderDateClick;
import com.internet.action.ActionToday;
import com.internet.http.data.response.GetOrderCalendarResponse.OrderCalendar;
import com.internet.turnright.b.R;

import de.greenrobot.event.EventBus;

@EViewGroup(R.layout.view_order_main_date_item)
public class MainOrderDateItemView extends LinearLayout {

	@ViewById
	TextView text_today, order_quantity, text_date;

	OrderCalendar mOrderCalendar;

	public MainOrderDateItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MainOrderDateItemView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@AfterViews
	void init() {
		setOrientation(LinearLayout.VERTICAL);
	}

	public void setData(OrderCalendar orderCalendar) {
		mOrderCalendar = orderCalendar;
		text_date.setText(orderCalendar.getDayOfMonth() + "");
		if (orderCalendar.getOrderNum() > 0)
			order_quantity.setText(orderCalendar.getOrderNum() + "单");
		else
			order_quantity.setText("");
	
		if (mOrderCalendar.isToDay()) {
			text_date.setTextColor(getContext().getResources().getColor(
					R.color.text_red));
			text_today.setText("今天");
		}

		this.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EventBus.getDefault().post(
						new ActionOrderDateClick(mOrderCalendar));
			}
		});
	}

	public void setNormal() {

		text_date.setTextColor(getResources().getColor(R.color.text_black));

	}

	public void setSelected() {

		text_date.setTextColor(getResources().getColor(R.color.text_red));
	}

	@Override
	protected void onAttachedToWindow() {
		// TODO Auto-generated method stub
		super.onAttachedToWindow();
		EventBus.getDefault().register(this);
	}

	@Override
	protected void onDetachedFromWindow() {
		// TODO Auto-generated method stub
		super.onDetachedFromWindow();
		EventBus.getDefault().unregister(this);
	}

	public void onEventMainThread(ActionOrderDateClick action) {
		if (TextUtils.equals(action.orderCalendar.getDate(),
				mOrderCalendar.getDate())) {
			text_date.setTextColor(getContext().getResources().getColor(
					R.color.text_white));
			text_date.setBackgroundResource(R.drawable.point_bg_header);
		} else {
			text_date.setTextColor(getContext().getResources().getColor(
					R.color.text_black));
			text_date.setBackgroundResource(R.color.bg_empty);
			if (mOrderCalendar.isToDay()) {
				text_date.setTextColor(getContext().getResources().getColor(
						R.color.text_red));
				text_today.setText("今天");
			}
		}

	}

	public void onEventMainThread(ActionToday action) {
		if (mOrderCalendar.isToDay()) {
			EventBus.getDefault()
					.post(new ActionOrderDateClick(mOrderCalendar));
		}
	}

}
