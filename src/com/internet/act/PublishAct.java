package com.internet.act;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.internet.app.MainApplication;
import com.internet.basic.BasicActivity;
import com.internet.db.SpHelper;
import com.internet.dialog.DialogModifyPrice;
import com.internet.dialog.DialogModifyPrice.DialogClickListener;
import com.internet.http.api.ApiException;
import com.internet.http.api.ApiManager;
import com.internet.http.data.post.AddSchedulePost;
import com.internet.http.data.post.CopySchedulePost;
import com.internet.http.data.post.DelSchedulePost;
import com.internet.http.data.post.SchedulePost;
import com.internet.http.data.post.UpdateSchedulePost;
import com.internet.http.data.response.ScheduleResponse;
import com.internet.http.data.response.ScheduleResponse.ResultBean;
import com.internet.http.data.response.ScheduleResponse.ResultBean.PulishDatesBean;
import com.internet.http.data.response.ScheduleResponse.ResultBean.TimeOfDaysBean;
import com.internet.http.data.vo.TimeOfDayVOs;
import com.internet.http.data.vo.UserInfoVO;
import com.internet.turnright.b.R;
import com.internet.util.DateHelper;
import com.internet.view.CalendarCustomView;
import com.internet.view.CalendarCustomView.CalendarBean;
import com.internet.view.CalendarCustomView.OnItemCheckedChangeListener;
import com.internet.view.HeaderView;
import com.internet.view.NonScrollGridView;

@EActivity(R.layout.act_publish)
public class PublishAct extends BasicActivity {

	@ViewById
	HeaderView view_header;

	@ViewById
	CalendarCustomView ccv;

	@ViewById
	NonScrollGridView gridView;

	@ViewById
	ScrollView scrollView;

	@ViewById
	TextView tv_subjectName;
	@ViewById
	TextView tv_siteName;
	@ViewById
	LinearLayout ll_select_site;
	@ViewById
	CheckBox cb_all;

	@ViewById
	View view_operate;
	@ViewById
	TextView tv_publish;

	@ViewById
	TextView tv_modify;

	@ViewById
	TextView tv_cancel;

	PublishAdapter adapter;

	ResultBean result;

	DateHelper helper = DateHelper.getInstance();

	public static final int TPYE_PUBLISH = 1;
	public static final int TPYE_PUBLISH_NO = 2;
	public static final int TPYE_PUBLISH_OTHER = 3;

	int selectType;

	int selectNum;

	String scheduleTypeCode = "1";
	String siteId;

	String selectDate;

	List<TimeOfDaysBean> selectList = new ArrayList<ScheduleResponse.ResultBean.TimeOfDaysBean>();

	@AfterViews
	void init() {

		if (getIntent().hasExtra("scheduleTypeCode")) {
			scheduleTypeCode = getIntent().getStringExtra("scheduleTypeCode");
		}

		if ("1".endsWith(scheduleTypeCode)) {
			ll_select_site.setVisibility(View.VISIBLE);
		} else {
			ll_select_site.setVisibility(View.GONE);
		}

		setSelectType(0);

		registerEventBus();

		selectDate = helper.getDataStringByDateFormat(DateHelper.date_line_day,
				new Date());

		getListMessage(selectDate);

	}

	@Background
	void getListMessage(String date) {
		showLoading();
		try {
			SchedulePost data = new SchedulePost(date, scheduleTypeCode,
					MainApplication.getInstance().getUserInfo().getDriverId());

			ScheduleResponse response = ApiManager.getDefault().schedule(data);

			if (response != null && response.result != null) {
				freshListView(response.result);
			} else {
				hidescrollView();
			}

		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast(e.getErrorMessage());
			hidescrollView();
		}
		closeLoading();

	}

	@Background
	void addschedule(List<TimeOfDayVOs> timeOfDayVOs) {

		try {

			UserInfoVO user = MainApplication.getInstance().getUserInfo();

			String strTimeOfDayVOs = new Gson().toJson(timeOfDayVOs);

			Log.e("strTimeOfDayVOs", strTimeOfDayVOs);

			AddSchedulePost data = new AddSchedulePost(SpHelper.getDefault()
					.getString(SpHelper.SIGN), selectDate, scheduleTypeCode,
					user.getDriverId(), siteId, "0", strTimeOfDayVOs);

			boolean isSuccess = ApiManager.getDefault().addschedule(data);

			if (isSuccess) {
				getListMessage(selectDate);
			} else {
				showToast("发布失败！");
			}

		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast("发布失败！");

		}

	}

	@Background
	void updateSchedule(String strScheduleIds, int price) {

		try {

			UpdateSchedulePost data = new UpdateSchedulePost(SpHelper
					.getDefault().getString(SpHelper.SIGN), strScheduleIds,
					price + "");

			boolean isSuccess = ApiManager.getDefault().updateSchedule(data);

			if (isSuccess) {
				getListMessage(selectDate);
			} else {
				showToast("修改失败！");
			}

		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast("修改失败！");

		}

	}

	@Background
	void delschedule(String strScheduleIds) {

		try {

			DelSchedulePost data = new DelSchedulePost(SpHelper.getDefault()
					.getString(SpHelper.SIGN), strScheduleIds);

			boolean isSuccess = ApiManager.getDefault().delschedule(data);

			if (isSuccess) {
				getListMessage(selectDate);
			} else {
				showToast("取消失败！");
			}

		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast("取消失败！");

		}

	}

	@Background
	void copySchedule() {

		try {

			CopySchedulePost data = new CopySchedulePost(SpHelper.getDefault()
					.getString(SpHelper.SIGN), MainApplication.getInstance()
					.getUserInfo().getDriverId(), selectDate, scheduleTypeCode);

			boolean isSuccess = ApiManager.getDefault().copySchedule(data);

			if (isSuccess) {
				getListMessage(selectDate);
			} else {
				showToast("复制失败！");
			}

		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast("复制失败！");

		}

	}

	@UiThread
	void hidescrollView() {
		scrollView.setVisibility(View.GONE);

	}

	@UiThread
	void freshListView(final ResultBean result) {
		this.result = result;
		selectType = 0;
		selectNum = 0;
		setSelectType(0);
		cb_all.setTag("no");
		cb_all.setChecked(false);

		if (TextUtils.isEmpty(result.monthOfYear)) {
			String dateMonth = helper.getDataStringByDateFormat(
					DateHelper.date_text_month, new Date());
			view_header.setTitle(dateMonth);
		} else {
			view_header.setTitle(result.monthOfYear);
		}

		tv_siteName.setText(result.siteName);
		tv_subjectName.setText(result.subjectName);

		if (result.siteId > 0) {
			siteId = result.siteId + "";

		} else {
			siteId = "";
		}

		List<CalendarBean> lists = new ArrayList<CalendarBean>();
		String[] weeks = new String[] { "日", "一", "二", "三", "四", "五", "六" };

		if (result.pulishDates != null && !result.pulishDates.isEmpty()) {
			int size = result.pulishDates.size();
			for (int i = 0; i < size; i++) {
				PulishDatesBean publish = result.pulishDates.get(i);
				Calendar firstDay = Calendar.getInstance();
				firstDay.setTimeInMillis(publish.date);

				// boolean isSelect = false;
				// if (firstDay.get(Calendar.DAY_OF_MONTH) ==
				// selectCalendar.get(Calendar.DAY_OF_MONTH)) {
				// isSelect = true;
				// }

				if (publish.toDay) {
					ccv.setTodayCalendar(firstDay);
				}
				lists.add(new CalendarBean(firstDay, weeks[i % weeks.length],
						publish.havePublish, publish.toDay));

			}

		} else {

			for (int i = 0; i < 14; i++) {
				Calendar firstDay = helper.getSundayOfLastWeek();
				firstDay.add(Calendar.DATE, i);

				boolean isToday = false;
				if (firstDay.get(Calendar.DAY_OF_MONTH) == Calendar
						.getInstance().get(Calendar.DAY_OF_MONTH)) {
					isToday = true;
				}
				lists.add(new CalendarBean(firstDay, weeks[i % weeks.length],
						false, isToday));

			}

		}

		ccv.setOnItemCheckedChangeListener(new OnItemCheckedChangeListener() {

			@Override
			public void onItemCheckedChanged(int index, CompoundButton cb,
					boolean flag) {

				if (flag && result != null && result.pulishDates != null
						&& result.pulishDates.size() > index) {

					PulishDatesBean bean = result.pulishDates.get(index);

					selectDate = bean.strDate;

					getListMessage(bean.strDate);

				}

			}
		});

		if (!lists.isEmpty() && ccv.getTag() == null) {
			ccv.setData(lists);
			ccv.setTag("");
		} else {
			ccv.refreshData(lists);
		}

		adapter = new PublishAdapter(this);

		gridView.setAdapter(adapter);

		selectType = getCurrentType(result.timeOfDays);

		adapter.setList(result.timeOfDays);

	}

	/**
	 * 若全部相同，返回一个类型
	 * 
	 */
	private int getCurrentType(List<TimeOfDaysBean> list) {

		if (list == null || list.isEmpty()) {

			return 0;
		}
		int type = 0;

		for (TimeOfDaysBean bean : list) {

			int tempType = bean.scheduleId > 0 ? TPYE_PUBLISH : TPYE_PUBLISH_NO;

			if (bean.scheduleId > 0
					&& !TextUtils.isEmpty(bean.scheduleTypeCode)) {

				if (bean.scheduleTypeCode.equals(scheduleTypeCode)) {
					tempType = TPYE_PUBLISH;
				} else {
					tempType = TPYE_PUBLISH_OTHER;

				}

			} else {
				tempType = TPYE_PUBLISH_NO;
			}
			bean.type = tempType;

		}

		for (int i = 0; i < list.size(); i++) {

			TimeOfDaysBean bean = list.get(i);
			if (i == 0) {
				type = bean.type;
			} else {

				if (type != bean.type) {
					return 0;
				}
			}

		}

		return type;

	}

	String strScheduleIds;

	@Click({ R.id.tv_cancel, R.id.tv_modify, R.id.ll_select_site,
			R.id.tv_publish, R.id.tv_copy })
	void click(final View view) {
		switch (view.getId()) {

		case R.id.ll_select_site:

			if (adapter != null && adapter.isHavePublish())
				showToast("已有发布信息，不能选择场地");
			else
				SelectSiteAct_.intent(this).start();

			break;
		case R.id.tv_cancel:

			strScheduleIds = "[";
			if (result != null && result.timeOfDays != null
					&& !result.timeOfDays.isEmpty()) {

				for (TimeOfDaysBean bean : result.timeOfDays) {

					if (bean.isSelect) {

						if (strScheduleIds.endsWith("[")) {
							strScheduleIds += bean.scheduleId;
						} else {
							strScheduleIds += "," + bean.scheduleId;
						}

					}

				}

				strScheduleIds += "]";

				delschedule(strScheduleIds);

			}

			break;

		case R.id.tv_copy:
			copySchedule();
			break;
		case R.id.tv_publish:
		case R.id.tv_modify:
			if ("1".endsWith(scheduleTypeCode)
					&& view.getId() == R.id.tv_publish
					&& TextUtils.isEmpty(siteId)) {
				closeLoading();
				showToast("请先选择场地！");
				return;
			}

			final List<TimeOfDayVOs> timeOfDayVOs = new ArrayList<TimeOfDayVOs>();
			List<TimeOfDaysBean> selectTime = new ArrayList<TimeOfDaysBean>();
			strScheduleIds = "[";
			if (result != null && result.timeOfDays != null
					&& !result.timeOfDays.isEmpty()) {

				for (TimeOfDaysBean bean : result.timeOfDays) {

					if (bean.isSelect) {
						timeOfDayVOs.add(new TimeOfDayVOs(bean.timeOfDayId,
								bean.price));
						selectTime.add(bean);
						if (strScheduleIds.endsWith("[")) {
							strScheduleIds += bean.scheduleId;
						} else {
							strScheduleIds += "," + bean.scheduleId;
						}

					}

				}

				strScheduleIds += "]";

			}

			DialogModifyPrice dialog = new DialogModifyPrice(this);

			dialog.setDialogListener(new DialogClickListener() {

				@Override
				public void onChlidViewClick(View v, int price) {
					switch (v.getId()) {
					case R.id.tv_ok:

						if (view.getId() == R.id.tv_modify) {

							updateSchedule(strScheduleIds, price);
						} else {
							for (TimeOfDayVOs vos : timeOfDayVOs) {
								vos.price = price;
							}

							addschedule(timeOfDayVOs);

						}

						break;

					default:
						break;
					}

				}
			});

			dialog.show();

			dialog.setTimeList(selectTime);
			break;

		default:
			break;
		}

	}

	public void setSelectType(int selectType) {
		this.selectType = selectType;

		switch (selectType) {
		case TPYE_PUBLISH:
			tv_publish.setVisibility(View.GONE);
			tv_cancel.setVisibility(View.VISIBLE);
			tv_modify.setVisibility(View.VISIBLE);
			view_operate.setVisibility(View.VISIBLE);
			gridView.setPadding(dp2Px(15), 0, dp2Px(15), dp2Px(95));
			break;

		case TPYE_PUBLISH_NO:
			tv_publish.setVisibility(View.VISIBLE);
			tv_cancel.setVisibility(View.GONE);
			tv_modify.setVisibility(View.GONE);
			view_operate.setVisibility(View.VISIBLE);
			gridView.setPadding(dp2Px(15), 0, dp2Px(15), dp2Px(55));
			break;
		case TPYE_PUBLISH_OTHER:
			tv_publish.setVisibility(View.GONE);
			tv_cancel.setVisibility(View.VISIBLE);
			tv_modify.setVisibility(View.GONE);
			view_operate.setVisibility(View.VISIBLE);
			gridView.setPadding(dp2Px(15), 0, dp2Px(15), dp2Px(55));
			break;

		default:
			tv_publish.setVisibility(View.GONE);
			tv_cancel.setVisibility(View.GONE);
			tv_modify.setVisibility(View.GONE);
			view_operate.setVisibility(View.GONE);
			gridView.setPadding(dp2Px(15), 0, dp2Px(15), dp2Px(15));
			break;
		}

	}

	/**
	 * dp转px
	 * 
	 * 
	 * @param dpValue
	 * @return
	 */
	public int dp2Px(float dpValue) {
		final float scale = getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * px转dp
	 */
	public int px2Dp(float pxValue) {
		final float scale = getResources().getDisplayMetrics().density;

		return (int) (pxValue / scale + 0.5f);
	}

	@CheckedChange(R.id.cb_all)
	void check(CompoundButton cb, boolean flag) {

		if (flag) {

			if (selectType <= 0) {

				if (result != null && result.timeOfDays != null
						&& !result.timeOfDays.isEmpty()) {

					int type = getCurrentType(result.timeOfDays);
					if (type > 0) {
						selectType = type;
					}

				}

			}

			if (selectType <= 0) {

				showToast("请先选择一种类型的时间段！");

				cb.setChecked(false);

				return;
			}

		}

		if (result != null && result.timeOfDays != null
				&& !result.timeOfDays.isEmpty()) {
			selectNum = 0;
			if (flag) {

				setSelectType(selectType);

				for (TimeOfDaysBean bean : result.timeOfDays) {

					if (bean.currentScheduleNum <= 0) {

						if (selectType == TPYE_PUBLISH) {

							if (bean.scheduleTypeCode.equals(scheduleTypeCode)) {
								bean.isSelect = bean.scheduleId > 0 ? true
										: false;
							}

						} else if (selectType == TPYE_PUBLISH_NO) {
							if (TextUtils.isEmpty(bean.scheduleTypeCode)) {
								bean.isSelect = bean.scheduleId > 0 ? false
										: true;
							}

						} else if (selectType == TPYE_PUBLISH_OTHER) {

							if (!TextUtils.isEmpty(bean.scheduleTypeCode)) {
								bean.isSelect = bean.scheduleTypeCode
										.equals(scheduleTypeCode) ? false
										: true;
							}

						}

						if (bean.isSelect) {
							selectNum++;
						}

					}

				}

			} else {
				for (TimeOfDaysBean bean : result.timeOfDays) {
					bean.isSelect = false;
				}

				setSelectType(0);

			}

			if (adapter != null) {

				adapter.notifyDataSetChanged();
				scrollView.postDelayed(new Runnable() {

					@Override
					public void run() {
						scrollView.smoothScrollTo(0, 0);

					}
				}, 100);

			}
		}

	}

	public void onEventMainThread(Intent intent) {
		if (intent != null) {

			if (intent.hasExtra("siteName")) {

				String siteName = intent.getStringExtra("siteName");
				siteId = intent.getStringExtra("siteId");

				tv_siteName.setText(siteName);

			}
		}

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (view_operate.getVisibility() == View.VISIBLE) {
			view_operate.setVisibility(View.GONE);
			check(null, false);
			return;
		}
		super.onBackPressed();
	}

	class PublishAdapter extends BaseAdapter {

		private List<TimeOfDaysBean> list;
		private Context context;

		public PublishAdapter(Context context) {
			super();
			this.list = new ArrayList<TimeOfDaysBean>();
			this.context = context;
		}

		@Override
		public int getCount() {
			if (list == null || list.isEmpty()) {
				return 0;
			}
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		public void setList(List<TimeOfDaysBean> list) {

			this.list = list;
			notifyDataSetChanged();
			scrollView.postDelayed(new Runnable() {

				@Override
				public void run() {
					scrollView.smoothScrollTo(0, 0);

				}
			}, 100);

		}

		public void addItem(TimeOfDaysBean bean) {

			if (list == null) {
				list = new ArrayList<TimeOfDaysBean>();
			}

			list.add(bean);
		}

		public boolean isHavePublish() {
			if (list == null || list.isEmpty()) {
				return false;
			} else {
				for (TimeOfDaysBean item : list) {
					if (TextUtils.equals(item.scheduleTypeCode, "1")) {
						return true;
					}
				}
				return false;
			}
		}

		@Override
		public View getView(int position, View view, ViewGroup arg2) {

			if (view == null) {
				view = LayoutInflater.from(context).inflate(
						R.layout.list_item_publish, null);
			}

			TextView tv_start_time = (TextView) view
					.findViewById(R.id.tv_start_time);
			TextView tv_end_time = (TextView) view
					.findViewById(R.id.tv_end_time);
			TextView tv_price = (TextView) view.findViewById(R.id.tv_price);
			TextView tv_scheduleTypeName = (TextView) view
					.findViewById(R.id.tv_scheduleTypeName);

			TextView tv_rmb = (TextView) view.findViewById(R.id.tv_rmb);
			TextView tv_yuan = (TextView) view.findViewById(R.id.tv_yuan);
			TextView tv_point_1 = (TextView) view.findViewById(R.id.tv_point_1);
			TextView tv_point_2 = (TextView) view.findViewById(R.id.tv_point_2);
			TextView tv_ = (TextView) view.findViewById(R.id.tv_);
			final View fl_select = view.findViewById(R.id.fl_select);
			final View ll_price = view.findViewById(R.id.ll_price);

			final TimeOfDaysBean bean = list.get(position);

			tv_start_time.setText(bean.strCurrTime);
			tv_end_time.setText(bean.strNextTime);
			tv_price.setText(bean.price + "");
			tv_scheduleTypeName.setText(bean.scheduleTypeName);
			view.setBackgroundResource(R.drawable.check_enable);

			if (bean.isSelect) {
				fl_select.setVisibility(View.VISIBLE);
			} else {
				fl_select.setVisibility(View.GONE);
			}

			if (bean.currentScheduleNum > 0) {
				view.setEnabled(false);
				fl_select.setVisibility(View.GONE);
				ll_price.setVisibility(View.VISIBLE);
				view.setBackgroundResource(R.drawable.check_yellow);
				tv_rmb.setTextColor(getResources().getColor(R.color.text_white));
				tv_yuan.setTextColor(getResources()
						.getColor(R.color.text_white));
				tv_point_1.setTextColor(getResources().getColor(
						R.color.text_white));
				tv_point_2.setTextColor(getResources().getColor(
						R.color.text_white));
				tv_.setTextColor(getResources().getColor(R.color.text_white));

				tv_start_time.setTextColor(getResources().getColor(
						R.color.text_white));
				tv_end_time.setTextColor(getResources().getColor(
						R.color.text_white));
				tv_price.setTextColor(getResources().getColor(
						R.color.text_white));
				tv_scheduleTypeName.setTextColor(getResources().getColor(
						R.color.text_white));

			} else {

				view.setEnabled(true);
			}

			if (bean.scheduleId > 0) {
				view.setBackgroundResource(R.drawable.check_no);
				ll_price.setVisibility(View.VISIBLE);
				tv_rmb.setTextColor(getResources().getColor(R.color.bg_header));
				tv_yuan.setTextColor(getResources().getColor(R.color.bg_header));
				tv_point_1.setTextColor(getResources().getColor(
						R.color.text_black));
				tv_point_2.setTextColor(getResources().getColor(
						R.color.text_black));
				tv_.setTextColor(getResources().getColor(R.color.text_black));

				tv_start_time.setTextColor(getResources().getColor(
						R.color.text_black));
				tv_end_time.setTextColor(getResources().getColor(
						R.color.text_black));
				tv_price.setTextColor(getResources()
						.getColor(R.color.bg_header));
				tv_scheduleTypeName.setTextColor(getResources().getColor(
						R.color.text_black));
			} else {

				view.setBackgroundResource(R.drawable.check_enable);
				ll_price.setVisibility(View.INVISIBLE);
				tv_rmb.setTextColor(getResources().getColor(R.color.text_gray));
				tv_yuan.setTextColor(getResources().getColor(R.color.text_gray));
				tv_point_1.setTextColor(getResources().getColor(
						R.color.text_black));
				tv_point_2.setTextColor(getResources().getColor(
						R.color.text_black));
				tv_.setTextColor(getResources().getColor(R.color.text_black));

				tv_start_time.setTextColor(getResources().getColor(
						R.color.text_black));
				tv_end_time.setTextColor(getResources().getColor(
						R.color.text_black));
				tv_price.setTextColor(getResources()
						.getColor(R.color.text_gray));
				tv_scheduleTypeName.setTextColor(getResources().getColor(
						R.color.text_gray));
			}

			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {

					if (bean.isSelect) {

						bean.isSelect = false;
						selectNum--;
						if (selectNum <= 0) {

							setSelectType(0);
						}
						fl_select.setVisibility(View.GONE);
					} else {

						int tempType = bean.scheduleId > 0 ? TPYE_PUBLISH
								: TPYE_PUBLISH_NO;

						if (bean.scheduleId > 0
								&& !TextUtils.isEmpty(bean.scheduleTypeCode)) {

							if (bean.scheduleTypeCode.equals(scheduleTypeCode)) {
								tempType = TPYE_PUBLISH;
							} else {
								tempType = TPYE_PUBLISH_OTHER;

							}

						} else {
							tempType = TPYE_PUBLISH_NO;
							if (("1".endsWith(scheduleTypeCode))
									&& TextUtils.isEmpty(siteId)) {
								showToast("请先选择场地！");
								return;
							} else {

							}
						}

						if (selectType <= 0) {

							setSelectType(tempType);
						} else {

							if (selectType != tempType) {

								return;

							}
							setSelectType(tempType);

						}
						bean.isSelect = true;
						selectNum++;
						fl_select.setVisibility(View.VISIBLE);
					}

				}
			});

			if (bean.expire) {
				view.setOnClickListener(null);
				view.setBackgroundResource(R.drawable.check_no);
			} else {

			}

			return view;
		}

	}

}
