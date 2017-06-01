package com.internet.view;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.internet.basic.AdapterView;
import com.internet.entity.MessageEntity;
import com.internet.turnright.b.R;
import com.internet.util.DateTimeUtil;
import com.internet.util.DensityUtil;

@EViewGroup(value = R.layout.list_item_message)
public class ListItemMessageView extends LinearLayout implements
		AdapterView<MessageEntity> {
	@ViewById
	TextView mTitle, mContent, mDate;

	@ViewById
	ImageView mImage;

	public ListItemMessageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public ListItemMessageView(Context context, AttributeSet attrs) {
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
	public void bindData(int position, MessageEntity data) {
		// TODO Auto-generated method stub
		if (TextUtils.equals(data.getType(), "1")) {
			mImage.setImageResource(R.drawable.message_xitong);
		} else if (TextUtils.equals(data.getType(), "2")) {
			mImage.setImageResource(R.drawable.message_lianlu);
		} else if (TextUtils.equals(data.getType(), "3")) {
			mImage.setImageResource(R.drawable.message_pingjia);
		} else if (TextUtils.equals(data.getType(), "4")) {
			mImage.setImageResource(R.drawable.message_yuyue);
		}

		mTitle.setText(data.getTitle());
		mContent.setText(data.getContent());
		mDate.setText(DateTimeUtil.formatDateTime(Long.valueOf(data
				.getCreateTime())));
	}
}
