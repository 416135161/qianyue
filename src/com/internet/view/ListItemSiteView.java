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
import com.internet.http.data.vo.SiteVO;
import com.internet.qianyue.R;
import com.internet.util.DensityUtil;

@EViewGroup(R.layout.view_list_item_site)
public class ListItemSiteView extends LinearLayout implements
		AdapterView<SiteVO> {
	@ViewById
	TextView mTitle;

	public ListItemSiteView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public ListItemSiteView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@AfterViews
	void init() {
		setOrientation(HORIZONTAL);
		setGravity(Gravity.CENTER_VERTICAL);
		setBackgroundColor(getResources().getColor(R.color.bg_white));
	}

	@Override
	public View getView() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void bindData(int position, SiteVO data) {
		// TODO Auto-generated method stub
		mTitle.setText(data.getSiteName());
	}

}
