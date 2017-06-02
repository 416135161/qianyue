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
import com.internet.http.data.vo.StudentVO;
import com.internet.qianyue.R;
import com.internet.util.DensityUtil;

@EViewGroup(R.layout.list_item_student)
public class ListItemStudentView extends LinearLayout implements
		AdapterView<StudentVO> {
	@ViewById
	TextView text_name, text_phone, text_type;

	public ListItemStudentView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public ListItemStudentView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@AfterViews
	void init() {
		setOrientation(HORIZONTAL);
		setGravity(Gravity.CENTER_VERTICAL);

		setPadding(DensityUtil.dip2px(getContext(), 10),
				DensityUtil.dip2px(getContext(), 5),
				DensityUtil.dip2px(getContext(), 10),
				DensityUtil.dip2px(getContext(), 5));
		setBackgroundColor(getResources().getColor(R.color.bg_white));
	}

	@Override
	public View getView() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void bindData(int position, StudentVO data) {
		text_phone.setText(data.getStudentMobile());
		text_name.setText(data.getStudentName());
		if(data.getStudentId()> 0){
			text_type.setText("");
		}else{
			text_type.setText("未激活");
		}
	}

}
