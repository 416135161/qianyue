package com.internet.act;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.widget.EditText;
import android.widget.TextView;

import com.internet.basic.BasicActivity;
import com.internet.turnright.b.R;
import com.internet.view.HeaderView;
import com.internet.view.ImageTextView;

@EActivity(R.layout.act_regist_dsp)
public class RegistDspAct extends BasicActivity {

	@ViewById
	HeaderView view_header;

	@ViewById
	EditText edit_name, edit_old, edit_address, edit_no, edit_school;

	@ViewById
	TextView edit_area, edit_model;

	@ViewById
	ImageTextView image_text_1, image_text_2, image_text_3, image_text_4,
			image_text_5, image_text_6;

	@AfterViews
	void init() {
		view_header.setTitle("教练信息");
		image_text_1.setText("身份证");
		image_text_2.setText("教练证");
		image_text_3.setText("行驶证");
		image_text_4.setText("驾驶证");
		image_text_5.setText("道路运输证");
		image_text_6.setText("");
	}

	@Click(R.id.btn_save)
	void save() {
		MainAct_.intent(this).start();
	}
}
