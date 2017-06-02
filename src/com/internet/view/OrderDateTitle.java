package com.internet.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.internet.http.data.response.GetOrderCalendarResponse.OrderCalendar;
import com.internet.qianyue.R;

public class OrderDateTitle{
	View mView;
	MainOrderDateItemView date_item1, date_item2, date_item3, date_item4,
			date_item5, date_item6, date_item7;

	public View getView(Context context) {

		mView = LayoutInflater.from(context).inflate(R.layout.order_date_title,
				null);

		date_item1 = (MainOrderDateItemView) mView
				.findViewById(R.id.date_item1);
		date_item2 = (MainOrderDateItemView) mView
				.findViewById(R.id.date_item2);
		date_item3 = (MainOrderDateItemView) mView
				.findViewById(R.id.date_item3);
		date_item4 = (MainOrderDateItemView) mView
				.findViewById(R.id.date_item4);
		date_item5 = (MainOrderDateItemView) mView
				.findViewById(R.id.date_item5);
		date_item6 = (MainOrderDateItemView) mView
				.findViewById(R.id.date_item6);
		date_item7 = (MainOrderDateItemView) mView
				.findViewById(R.id.date_item7);
		return mView;

	}

	public void setData(int position, OrderCalendar orderCalendar) {
		position += 1;
		switch (position) {
		case 1:
			date_item1.setData(orderCalendar);
			break;
		case 2:
			date_item2.setData(orderCalendar);
			break;
		case 3:
			date_item3.setData(orderCalendar);
			break;
		case 4:
			date_item4.setData(orderCalendar);
			break;
		case 5:
			date_item5.setData(orderCalendar);
			break;
		case 6:
			date_item6.setData(orderCalendar);
			break;
		case 7:
			date_item7.setData(orderCalendar);
			break;

		}
	}

}
