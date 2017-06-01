package com.internet.view;

import java.util.Calendar;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.LinearLayout;

import com.internet.action.ActionSelectBirthday;
import com.internet.turnright.b.R;

import de.greenrobot.event.EventBus;

@EViewGroup(R.layout.view_select_birthday)
public class SelectBirthdayView extends LinearLayout {


	public SelectBirthdayView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public SelectBirthdayView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}





}
