package com.internet.view;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.app.Instrumentation;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.internet.action.ActionOrderTitle;
import com.internet.turnright.b.R;

import de.greenrobot.event.EventBus;

@EViewGroup(R.layout.view_header_order_main)
public class HeaderViewMainOrder extends RelativeLayout {

	@ViewById
	TextView text_title_1, text_title_2, text_title_3;

	@ViewById
	View view_bottom_1, view_bottom_2, view_bottom_3;

	@ViewById
	ImageView image_back;

	public HeaderViewMainOrder(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public HeaderViewMainOrder(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public HeaderViewMainOrder(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@AfterViews
	void init() {
		view_bottom_1.setVisibility(View.VISIBLE);
		view_bottom_2.setVisibility(View.INVISIBLE);
		view_bottom_3.setVisibility(View.INVISIBLE);
	}

	@Click(R.id.image_back)
	@Background
	void back() {
		Instrumentation inst = new Instrumentation();
		inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
	}

	@Click(R.id.title1)
	void click1() {
		view_bottom_1.setVisibility(View.VISIBLE);
		view_bottom_2.setVisibility(View.INVISIBLE);
		view_bottom_3.setVisibility(View.INVISIBLE);
		EventBus.getDefault().post(new ActionOrderTitle(1));
	}

	@Click(R.id.title2)
	void click2() {
		view_bottom_1.setVisibility(View.INVISIBLE);
		view_bottom_2.setVisibility(View.VISIBLE);
		view_bottom_3.setVisibility(View.INVISIBLE);
		EventBus.getDefault().post(new ActionOrderTitle(2));
	}

	@Click(R.id.title3)
	void click3() {
		view_bottom_1.setVisibility(View.INVISIBLE);
		view_bottom_2.setVisibility(View.INVISIBLE);
		view_bottom_3.setVisibility(View.VISIBLE);
		EventBus.getDefault().post(new ActionOrderTitle(3));
	}

}