package com.internet.dialog;

import java.util.Calendar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import com.internet.action.ActionSelectBirthday;
import com.internet.turnright.b.R;

import de.greenrobot.event.EventBus;

import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

@EFragment(R.layout.dialog_select_birthday)
public class SelectBirthdayDialog extends DialogFragment {
	@ViewById
	DatePicker datePicker;
	long mBirthday;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NORMAL, android.R.style.Theme_Translucent_NoTitleBar);

	}

	@AfterViews
	void initView() {
		Calendar calendar = Calendar.getInstance();
		refreshCalendar(calendar, mBirthday);
		int year = calendar.get(Calendar.YEAR);
		int monthOfYear = calendar.get(Calendar.MONTH);
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		datePicker.init(year, monthOfYear, dayOfMonth,
				new OnDateChangedListener() {

					public void onDateChanged(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {

						Calendar calendar = Calendar.getInstance();
						calendar.set(Calendar.YEAR, year);
						calendar.set(Calendar.MONTH, monthOfYear);
						calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
						mBirthday = calendar.getTimeInMillis();
					}

				});
	}

	@Click(R.id.view)
	void clickView() {
		this.dismiss();
	}

	@Click(R.id.text_qx)
	void onImage2Click() {
		this.dismiss();
	}

	@Click(R.id.text_qd)
	void onImage1Click() {
		EventBus.getDefault().post(new ActionSelectBirthday(mBirthday));
		this.dismiss();
	}

	private void refreshCalendar(Calendar c, long hint) {
		if (hint != 0) {
			c.setTimeInMillis(hint);
		}
	}

	public void initDate(long time) {
		mBirthday = time;
	}
}
