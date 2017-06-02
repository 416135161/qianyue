package com.internet.dialog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TimePicker;

import com.internet.action.ActionSelectTime;
import com.internet.qianyue.R;

import de.greenrobot.event.EventBus;

@EFragment(R.layout.dialog_select_time)
public class DialogSelectTime extends DialogFragment {
	public static String HOUR_KEY = "hour_key";
	public static String MINUTE_KEY = "minute_key";

	@ViewById
	TimePicker mTimePiker;

	@ViewById
	Button btn_cancel, btn_ensure;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NORMAL, android.R.style.Theme_Translucent_NoTitleBar);

	}

	@AfterViews
	void init() {
		int hour = getArguments().getInt(HOUR_KEY);
		int minute = getArguments().getInt(MINUTE_KEY);
		mTimePiker.setCurrentHour(hour);
		mTimePiker.setCurrentMinute(minute);
	}

	@Click(R.id.btn_cancel)
	void cancel() {
		dismiss();
	}

	@Click(R.id.btn_ensure)
	void ensure() {

		int hour = mTimePiker.getCurrentHour();
		int minute = mTimePiker.getCurrentMinute();
		EventBus.getDefault().post(new ActionSelectTime(hour, minute));
		dismiss();
	}
}
