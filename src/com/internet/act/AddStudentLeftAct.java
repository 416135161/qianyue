package com.internet.act;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import com.internet.basic.BasicActivity;
import com.internet.qianyue.R;
import com.internet.view.HeaderView;

@EActivity(R.layout.act_add_student_left)
public class AddStudentLeftAct extends BasicActivity {
	@ViewById
	HeaderView view_header;
	
	@AfterViews
	void init() {
		
		view_header.setTitle("添加学员");
		
	}
}
