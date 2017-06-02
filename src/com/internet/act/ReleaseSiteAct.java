/**  
 * @author ningsj@shishike.com
 * @Title: ReleaseSite.java 
 * @Package com.internet.act 
 * @Description: TODO
 * @date_item 2015-11-25 上午10:56:41 
 * @version V1.0  
 */
package com.internet.act;

import java.util.ArrayList;
import java.util.Calendar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.internet.action.ActionReleaseSuccess;
import com.internet.action.ActionSelectSite;
import com.internet.app.MainApplication;
import com.internet.basic.BasicActivity;
import com.internet.basic.EsayAdapter;
import com.internet.db.SpHelper;
import com.internet.http.api.ApiException;
import com.internet.http.api.ApiManager;
import com.internet.http.data.post.AppointDatePost;
import com.internet.http.data.post.CloseOneKeyPost;
import com.internet.http.data.post.CopySchedulePost;
import com.internet.http.data.post.GetCalenderListPost;
import com.internet.http.data.response.AppointDateResponse;
import com.internet.http.data.response.GetCalenderListResponse;
import com.internet.http.data.response.GetCalenderListResponse.DriversCalender;
import com.internet.http.data.vo.SiteVO;
import com.internet.qianyue.R;
import com.internet.util.DateTimeUtil;
import com.internet.util.DensityUtil;
import com.internet.view.HeaderView;
import com.internet.view.ListItemCalenderView_;
import com.internet.view.ReleaseSiteDateItemView;

/**
 * @Author: ningsj@shishike.com
 * @date_item：2015-11-25 上午10:56:41
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All rights reserved.
 */
@EActivity(R.layout.act_release_site)
public class ReleaseSiteAct extends BasicActivity {

	@ViewById
	HeaderView view_header;

	@ViewById
	TextView text_repeat, text_site, text_copy;

	@ViewById
	CheckBox mSwitch;

	@ViewById
	ReleaseSiteDateItemView date_item1, date_item2, date_item3, date_item4,
			date_item5, date_item6, date_item7;
	@ViewById(R.id.listView)
	com.baoyz.swipemenulistview.SwipeMenuListView mListView;

	private EsayAdapter<DriversCalender, ListItemCalenderView_> mListAdapter;

	private ArrayList<DriversCalender> mListData;

	private ArrayList<String> listAppointDate;

	private SiteVO mSiteVO;

	@AfterViews
	void init() {
		registerEventBus();
		view_header.setTitle("场地训练-发布时间");
		mListAdapter = new EsayAdapter<GetCalenderListResponse.DriversCalender, ListItemCalenderView_>(
				this) {
		};
		mListData = new ArrayList<GetCalenderListResponse.DriversCalender>();
		mListAdapter.setList(mListData);
		mListView.setAdapter(mListAdapter);
		mListView.setMenuCreator(creator);
		mListView.setOnMenuItemClickListener(menuItemClickListener);
		initDate();
		setSelectDate(mPosition);
		getAppointDate();

	}

	@Click(R.id.image_add)
	void clickAddTime() {
		if (mSwitch.isChecked()) {
			showToast("预约已被关闭");
			return;
		}

		if (mSiteVO == null) {
			showToast("请选择教练场地");
			return;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, mPosition - 1);
		String date = DateTimeUtil.formatDate(calendar.getTimeInMillis());
		ReleaseSelectTimeSelfAct_.IntentBuilder_ build = ReleaseSelectTimeSelfAct_
				.intent(this);
		build.get().putExtra(ReleaseSelectTimeSelfAct.INTENT_FLAG_DATE, date);
		build.get()
				.putExtra(ReleaseSelectTimeSelfAct.INTENT_FLAG_SITE, mSiteVO);
		build.get().putExtra(ReleaseSelectTimeSelfAct.DAY_OF_WEEK, mPosition);
		build.startForResult(0);

	}

	@Click(R.id.view_select_site)
	void clickSelectSite() {
		ReleaseSelectSiteAct_.intent(this).start();
	}

	@Click(R.id.text_copy)
	void clickCopy() {
		doCopy();
	}

	@CheckedChange(R.id.mSwitch)
	void onSwitch(CompoundButton view, boolean isChecked) {
		oneKeyClose(isChecked);
	}

	@Background
	void oneKeyClose(boolean isChecked) {
		showLoading();
		boolean response = false;
		try {
			CloseOneKeyPost data = new CloseOneKeyPost();
			data.setDriverId(MainApplication.getInstance().getUserInfo()
					.getDriverId());
			data.setSign(SpHelper.getDefault().getString(SpHelper.SIGN));
			data.setScheduleTypeCode("1");
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, mPosition - 1);
			data.setCalenderDate(DateTimeUtil.formatDate(calendar
					.getTimeInMillis()));
			data.setOptType(isChecked ? "1" : "0");
			response = ApiManager.getDefault().closeOneKey(data);

		} catch (ApiException e) {
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
		closeLoading();
		if (response) {
			showToast("一键操作成功");
		}

	}

	@Background
	void doCopy() {
		showLoading();
		boolean response = false;
		try {
			CopySchedulePost data = new CopySchedulePost();
			data.setDriverId(MainApplication.getInstance().getUserInfo()
					.getDriverId());
			data.setSign(SpHelper.getDefault().getString(SpHelper.SIGN));
			data.setScheduleTypeCode("1");
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, mPosition - 1);
			data.setCalenderDate(DateTimeUtil.formatDate(calendar
					.getTimeInMillis()));
			response = ApiManager.getDefault().copySchedule(data);

		} catch (ApiException e) {
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
		closeLoading();
		if (response) {
			showToast("复制成功！");
		}

	}

	@Background
	void getAppointDate() {
		showLoading();
		try {
			AppointDatePost data = new AppointDatePost();
			data.setDriverId(MainApplication.getInstance().getUserInfo()
					.getDriverId());
			data.setStartDate(DateTimeUtil.getCurrentDate());
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, 6);
			data.setEndDate(DateTimeUtil.formatDate(calendar.getTimeInMillis()));
			data.setScheduleTypeCode("1");
			data.setSign(SpHelper.getDefault().getString(SpHelper.SIGN));
			AppointDateResponse response = ApiManager.getDefault().appointDate(
					data);
			listAppointDate = response.getResult();

		} catch (ApiException e) {
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
		closeLoading();
		initDateItems();
	}

	@UiThread
	void initDateItems() {
		if (listAppointDate != null && listAppointDate.size() > 0) {

			for (int position = 1; position <= 7; position++) {
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DAY_OF_MONTH, position - 1);
				String date = DateTimeUtil.formatDate(calendar
						.getTimeInMillis());
				for (int i = 0; i < listAppointDate.size(); i++) {
					String item = listAppointDate.get(i);
					if (TextUtils.equals(item.substring(0, 10), date)) {
						setAppointFlag(position);
						break;
					}

				}
			}

		}
	}

	@Click({ R.id.date_item1, R.id.date_item2, R.id.date_item3,
			R.id.date_item4, R.id.date_item5, R.id.date_item6, R.id.date_item7 })
	void clickDateItem(View view) {
		switch (view.getId()) {
		case R.id.date_item1:
			setSelectDate(1);
			break;
		case R.id.date_item2:
			setSelectDate(2);
			break;
		case R.id.date_item3:
			setSelectDate(3);
			break;
		case R.id.date_item4:
			setSelectDate(4);
			break;
		case R.id.date_item5:
			setSelectDate(5);
			break;
		case R.id.date_item6:
			setSelectDate(6);
			break;
		case R.id.date_item7:
			setSelectDate(7);
			break;
		}
		ReleaseSiteDateItemView dateItemView = (ReleaseSiteDateItemView) view;
		if (dateItemView.isAppointed()) {
			getCalenderList(dateItemView.getPosition());
		} else
			refreshListView(null);

	}

	@Background
	void getCalenderList(int position) {
		showLoading();
		GetCalenderListResponse response = null;
		try {
			GetCalenderListPost data = new GetCalenderListPost();

			data.setDriverId(MainApplication.getInstance().getUserInfo()
					.getDriverId());
			data.setScheduleTypeCode("1");
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, position - 1);
			data.setDate(DateTimeUtil.formatDate(calendar.getTimeInMillis()));

			response = ApiManager.getDefault().getCalenderList(data);

		} catch (ApiException e) {
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
		closeLoading();
		if (response != null)
			refreshListView(response.getResult());
		else
			refreshListView(null);
	}

	@UiThread
	void refreshListView(ArrayList<DriversCalender> result) {
		mListData.clear();
		if (result != null && !result.isEmpty()) {
			mListData.addAll(result);
		}
		mListAdapter.notifyDataSetChanged();

		if (result != null && !result.isEmpty()) {
			mSwitch.setChecked(result.get(0).status == "1" ? true : false);
			mSwitch.setClickable(true);
			text_copy.setClickable(true);
			text_site.setText(result.get(0).siteName);
			mSiteVO = new SiteVO();
			mSiteVO.setId(result.get(0).siteId);
			mSiteVO.setSiteName(result.get(0).siteName);
		} else {
			mSwitch.setClickable(false);
			text_copy.setClickable(false);
			text_site.setText("");
			mSiteVO = null;
		}
	}

	void initDate() {
		date_item1.setNormal();
		date_item2.setNormal();
		date_item3.setNormal();
		date_item4.setNormal();
		date_item5.setNormal();
		date_item6.setNormal();
		date_item7.setNormal();
		date_item1.setText(1);
		date_item2.setText(2);
		date_item3.setText(3);
		date_item4.setText(4);
		date_item5.setText(5);
		date_item6.setText(6);
		date_item7.setText(7);
	}

	private int mPosition = 1;

	void setSelectDate(int position) {
		this.mPosition = position;
		date_item1.setNormal();
		date_item2.setNormal();
		date_item3.setNormal();
		date_item4.setNormal();
		date_item5.setNormal();
		date_item6.setNormal();
		date_item7.setNormal();
		switch (position) {
		case 1:
			date_item1.setSelected();
			break;
		case 2:
			date_item2.setSelected();
			break;
		case 3:
			date_item3.setSelected();
			break;
		case 4:
			date_item4.setSelected();
			break;
		case 5:
			date_item5.setSelected();
			break;
		case 6:
			date_item6.setSelected();
			break;
		case 7:
			date_item7.setSelected();
			break;
		}
	}

	void setAppointFlag(int position) {

		switch (position) {
		case 1:
			date_item1.setAppointFlag();
			break;
		case 2:
			date_item2.setAppointFlag();
			break;
		case 3:
			date_item3.setAppointFlag();
			break;
		case 4:
			date_item4.setAppointFlag();
			break;
		case 5:
			date_item5.setAppointFlag();
			break;
		case 6:
			date_item6.setAppointFlag();
			break;
		case 7:
			date_item7.setAppointFlag();
			break;
		}
		initListView(position);
	}

	private boolean mIsFirstCome = true;

	private void initListView(int position) {
		if (mIsFirstCome) {
			mIsFirstCome = false;
			setSelectDate(position);
			getCalenderList(position);
		}
	}

	public void onEventMainThread(ActionSelectSite action) {
		if (action.siteVO != null) {
			this.mSiteVO = action.siteVO;
			text_site.setText(action.siteVO.getSiteName());
		}

	}

	public void onEventMainThread(ActionReleaseSuccess action) {
		getCalenderList(mPosition);
	}

	OnMenuItemClickListener menuItemClickListener = new OnMenuItemClickListener() {
		@Override
		public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
			switch (index) {
			case 0:
				mListData.remove(position);
				mListAdapter.notifyDataSetChanged();
				break;
			}
			return false;
		}

	};

	SwipeMenuCreator creator = new SwipeMenuCreator() {
		@Override
		public void create(SwipeMenu menu) {

			// create "delete" item
			SwipeMenuItem deleteItem = new SwipeMenuItem(
					getApplicationContext());
			// set item background
			deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F,
					0x25)));
			// set item width
			deleteItem
					.setWidth(DensityUtil.dip2px(getApplicationContext(), 90));
			// set a icon
			// deleteItem.setIcon(R.drawable.ic_delete);
			deleteItem.setTitle("删除");
			deleteItem.setTitleColor(getResources()
					.getColor(R.color.text_white));
			deleteItem.setTitleSize(DensityUtil.sp2px(getApplicationContext(),
					8));
			// add to menu
			menu.addMenuItem(deleteItem);
		}

	};

	@Override
	protected void onPause() {
		super.onPause();
		mIsFirstCome = false;
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data != null) {
			int dayOfweek = data.getIntExtra(
					ReleaseSelectTimeSelfAct.DAY_OF_WEEK, 1);
			Log.d("xxxxxx", dayOfweek + "");
			getCalenderList(dayOfweek);
			setAppointFlag(dayOfweek);
		}
	}

	// @Background
	// void deleteCalendar(int position){
	// showLoading();
	// boolean response = false;
	// try {
	// GetCalenderListPost data = new GetCalenderListPost();
	//
	// data.setDriverId(MainApplication.getInstance().getUserInfo()
	// .getDriverId());
	// data.setScheduleTypeCode("1");
	// Calendar calendar = Calendar.getInstance();
	// calendar.add(Calendar.DAY_OF_MONTH, position - 1);
	// data.setDate(DateTimeUtil.formatDate(calendar.getTimeInMillis()));
	//
	// response = ApiManager.getDefault().deleteCalendarById(null);
	//
	// } catch (ApiException e) {
	// e.printStackTrace();
	// showToast(e.getErrorMessage());
	// }
	// closeLoading();
	//
	// }
}
