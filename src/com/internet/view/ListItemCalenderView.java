package com.internet.view;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.internet.basic.AdapterView;
import com.internet.http.data.response.GetCalenderListResponse.DriversCalender;
import com.internet.qianyue.R;
import com.internet.util.DateTimeUtil;

@EViewGroup(value = R.layout.list_item_calender)
public class ListItemCalenderView extends LinearLayout implements
		AdapterView<DriversCalender> {
	@ViewById
	TextView mText1, mText2, mText3;

	public ListItemCalenderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public ListItemCalenderView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@AfterViews
	void init() {
		setOrientation(HORIZONTAL);
		setPadding(15, 40, 15, 40);
		setGravity(Gravity.CENTER_VERTICAL);
		setBackgroundColor(getResources().getColor(R.color.bg_white));
	}

	@Override
	public View getView() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void bindData(int position, DriversCalender data) {
		// TODO Auto-generated method stub
		mText1.setText(DateTimeUtil.getHHmm(Long
				.valueOf(data.scheduleStartTime))
				+ "~"
				+ DateTimeUtil.getHHmm(Long.valueOf(data.scheduleEndTime)));
		mText3.setText("￥" + data.schedulePrice);
	}
}
