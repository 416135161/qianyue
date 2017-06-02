package com.internet.act;

import java.util.ArrayList;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.internet.action.ActionOrderDateClick;
import com.internet.action.ActionOrderTitle;
import com.internet.action.ActionToday;
import com.internet.adapter.ViewPagerAdapter;
import com.internet.app.MainApplication;
import com.internet.basic.BasicActivity;
import com.internet.basic.EsayAdapter;
import com.internet.db.SpHelper;
import com.internet.entity.OrderEntity;
import com.internet.http.api.ApiException;
import com.internet.http.api.ApiManager;
import com.internet.http.data.post.GetOrderCalendarPost;
import com.internet.http.data.post.OrderListPost;
import com.internet.http.data.response.GetOrderCalendarResponse;
import com.internet.http.data.response.GetOrderCalendarResponse.OrderCalendar;
import com.internet.http.data.response.OrderListResponse;
import com.internet.qianyue.R;
import com.internet.view.ListItemOrderView_;
import com.internet.view.OrderDateTitle;

@EActivity(R.layout.act_order_main)
public class OrderMainAct extends BasicActivity {
	@ViewById
	ListView mListView;

	@ViewById
	TextView text_no_data;

	private ArrayList<OrderEntity> mListData;

	private int mSubjectCode = 1;

	private ViewPager viewPager; // 对应的viewPager

	private ArrayList<View> viewList;// view数组
	private ArrayList<OrderDateTitle> orderDateTitleList;

	private ViewPagerAdapter pagerAdapter;
	private EsayAdapter<OrderEntity, ListItemOrderView_> mListAdapter;

	@AfterViews
	void init() {
		registerEventBus();
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
		orderDateTitleList = new ArrayList<OrderDateTitle>();
		for (int i = 0; i < 4; i++) {
			OrderDateTitle orderDateTitle = new OrderDateTitle();
			orderDateTitleList.add(orderDateTitle);
			viewList.add(orderDateTitle.getView(this));
		}
		pagerAdapter = new ViewPagerAdapter(viewList);
		viewPager.setAdapter(pagerAdapter);
		viewPager.setCurrentItem(2);

		mListAdapter = new EsayAdapter<OrderEntity, ListItemOrderView_>(this) {
		};
		mListData = new ArrayList<OrderEntity>();
		mListAdapter.setList(mListData);
		mListView.setAdapter(mListAdapter);

		getOrderCalendar();
	}

	@Background
	void doQueryOrderList(String date) {

		showLoading();
		OrderListResponse response = null;
		try {
			OrderListPost data = new OrderListPost();
			data.sign =  SpHelper.getDefault().getString(SpHelper.SIGN);
			data.driverId = MainApplication.getInstance().getUserInfo()
					.getDriverId();
			data.subjectCode = mSubjectCode + "";
			data.date = date;
			data.pageNo = "1";
			data.pageSize = "20";
			response = ApiManager.getDefault().queryOrderList(data);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
		closeLoading();
		if (response != null && response.getResult() != null
				&& response.getResult().getResult() != null) {
			showOrderList(response.getResult().getResult());
		} 
	}

	@Background
	void getOrderCalendar() {

		showLoading();
		GetOrderCalendarResponse response = null;
		try {
			GetOrderCalendarPost data = new GetOrderCalendarPost();
			data.sign = SpHelper.getDefault().getString(SpHelper.SIGN);
			data.driverId = MainApplication.getInstance().getUserInfo()
					.getDriverId();
			data.subjectCode = mSubjectCode + "";

			response = ApiManager.getDefault().getOrderCalendar(data);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
		closeLoading();
		if (response != null && response.getResult() != null) {
			initDateTitle(response.getResult());
		} else {
			showToast("未获取到订单！");
		}
	}

	@UiThread
	void initDateTitle(ArrayList<OrderCalendar> list) {
		for (int i = 0; i < list.size(); i++) {
			int page = i / 7;
			int position = i % 7;
			orderDateTitleList.get(page).setData(position, list.get(i));
		}
		post(new ActionToday());
	}

	@UiThread
	void showOrderList(ArrayList<OrderEntity> result) {
		mListData.clear();
		mListData.addAll(result);
		mListAdapter.notifyDataSetChanged();
		hideEmpoty();
	}

	@UiThread
	void hideEmpoty() {
		text_no_data.setVisibility(View.INVISIBLE);
	}
	

	@UiThread
	void showEmpoty() {
		text_no_data.setVisibility(View.VISIBLE);
	}

	public void onEventMainThread(ActionOrderTitle action) {
		mSubjectCode = action.subjectCode;
		getOrderCalendar();
	}

	public void onEventMainThread(ActionOrderDateClick action) {
		if (action.orderCalendar.getOrderNum() != 0) {
			doQueryOrderList(action.orderCalendar.getStrDate());
		} else {
			mListData.clear();
			mListAdapter.notifyDataSetChanged();
			showEmpoty();
		}

	}

}
