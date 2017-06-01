package com.internet.view;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.internet.turnright.b.R;

@EViewGroup(R.layout.view_search_student)
public class SearchSiteView extends LinearLayout {
	@ViewById
	TextView text_search, text_cancel;
	
	@ViewById
	EditText edit_search;

	
	public SearchSiteView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@AfterViews
	void init() {

	}
	
	@AfterTextChange(R.id.edit_search)
	void afterTextChange(){
		if(TextUtils.isEmpty(edit_search.getEditableText().toString())){
			text_search.setVisibility(View.GONE);
			text_cancel.setVisibility(View.VISIBLE);
		}else{
			text_search.setVisibility(View.VISIBLE);
			text_cancel.setVisibility(View.GONE);
		}
	}

}
