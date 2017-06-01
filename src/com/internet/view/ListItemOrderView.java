package com.internet.view;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.internet.act.OrderDetailAct;
import com.internet.act.OrderDetailAct_;
import com.internet.basic.AdapterView;
import com.internet.entity.OrderEntity;
import com.internet.turnright.b.R;
import com.internet.util.DateTimeUtil;

@EViewGroup(R.layout.list_item_order)
public class ListItemOrderView extends LinearLayout implements
		AdapterView<OrderEntity> {
	@ViewById
	TextView text_no, text_date, text_state, text_total_time, text_money,
			text_address;

	public ListItemOrderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public ListItemOrderView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@AfterViews
	void init() {
		setOrientation(VERTICAL);
		setBackgroundColor(getResources().getColor(R.color.bg_white));

	}

	@Override
	public View getView() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void bindData(int position, final OrderEntity data) {
		// TODO Auto-generated method stub
		text_no.setText("订单编号：" + data.orderId);
		text_date.setText("创建时间："
				+ DateTimeUtil.formatDateTime(Long.valueOf(data.startTime)));
		text_state.setText(data.userStatusName);
		text_total_time.setText(data.timeTotal + "小时");
		text_money.setText("￥ " + data.totalMoney + "元");
		text_address.setText(data.siteName);

		this.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				OrderDetailAct_.IntentBuilder_ build = new OrderDetailAct_.IntentBuilder_(
						getContext());
				build.get().putExtra(OrderDetailAct.EXTRA_KEY, data);
				build.start();
			}
		});
	}

}
