package com.internet.view;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.internet.basic.AdapterView;
import com.internet.http.data.vo.StudentDetailVO.UserAppoint;
import com.internet.qianyue.R;
import com.internet.util.DateTimeUtil;

@EViewGroup(R.layout.list_item_student_dsp)
public class ListItemUserAppointView extends LinearLayout implements
		AdapterView<UserAppoint> {
	@ViewById
	TextView text_1, text_2, text_3;

	public ListItemUserAppointView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public ListItemUserAppointView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void bindData(int position, UserAppoint data) {
		// TODO Auto-generated method stub
		text_1.setText(data.getSubjectName());
		text_2.setText(String.format("预约%1s场地,%2s小时练习", data.getSiteName(),
				data.getExerciseTimes() + ""));
		text_3.setText(DateTimeUtil.formatDateTime(data.getScheduleStartTime())
				+ "~"
				+ DateTimeUtil.formatDateTime(data.getScheduleEndTime())
						.substring(11));

	}

}
