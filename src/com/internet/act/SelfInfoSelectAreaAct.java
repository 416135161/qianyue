package com.internet.act;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import com.internet.basic.BasicActivity;
import com.internet.turnright.b.R;
import com.internet.view.HeaderView;

@EActivity(R.layout.act_self_info_select_area)
public class SelfInfoSelectAreaAct extends BasicActivity {
	@ViewById
	HeaderView view_header;

	@AfterViews
	void init() {
		view_header.setTitle("地区");
	}
}
