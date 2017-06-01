package com.internet.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import com.internet.turnright.b.R;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CalendarCustomView extends FrameLayout {

	private List<ViewGroup> viewList;
	private List<CheckBox> cbList;

	private OnItemCheckedChangeListener changeListener;

	private Calendar todayCalendar = Calendar.getInstance();

	public Calendar getTodayCalendar() {
		return todayCalendar;
	}

	public void setTodayCalendar(Calendar todayCalendar) {
		this.todayCalendar = todayCalendar;
	}

	public CalendarCustomView(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	public CalendarCustomView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	public CalendarCustomView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);

	}

	public void setData(List<CalendarBean> lists) {

		removeAllViews();
		if (lists == null || lists.size() < 14) {
			return;
		}
		ViewPager viewPager = new ViewPager(getContext());
		viewPager.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));
		addView(viewPager);

		prepareData(lists);

		ViewPagerAdapter adapter = new ViewPagerAdapter();
		viewPager.setAdapter(adapter);

		viewPager.setCurrentItem(0);

	}

	public void refreshData(List<CalendarBean> lists) {

		if (lists == null || lists.size() < 14) {
			return;
		}

		if (viewList == null || viewList.isEmpty()) {
			return;
		}

		for (int i = 0; i < viewList.size(); i++) {

			ViewGroup v = viewList.get(i);
			int count = v.getChildCount();

			for (int j = 0; j < count; j++) {
				View view = v.getChildAt(j);
				View point = view.findViewById(R.id.point);

				final int index = (i == 0 ? 0 : count) + j;

				CalendarBean bean = lists.get(index);

				if (bean.isHaveData) {
					point.setVisibility(View.VISIBLE);
				} else {
					point.setVisibility(View.INVISIBLE);
				}

			}

		}

	}

	public void setOnItemCheckedChangeListener(
			OnItemCheckedChangeListener changeListener) {
		this.changeListener = changeListener;
	}

	private void prepareData(List<CalendarBean> lists) {

		viewList = new ArrayList<ViewGroup>();
		cbList = new ArrayList<CheckBox>();

		for (int i = 0; i < 2; i++) {

			LinearLayout ll = new LinearLayout(getContext());

			ll.setOrientation(LinearLayout.HORIZONTAL);
			ll.setLayoutParams(new ViewGroup.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT));
			for (int j = 0; j < 7; j++) {

				View v = LayoutInflater.from(getContext()).inflate(
						R.layout.view_custom_calendar, null);

				TextView tv_week = (TextView) v.findViewById(R.id.tv_week);
				final CheckBox cb_day = (CheckBox) v.findViewById(R.id.cb_day);
				TextView tv_today = (TextView) v.findViewById(R.id.tv_today);
				View point = v.findViewById(R.id.point);

				final int index = (i == 0 ? 0 : 7) + j;

				CalendarBean bean = lists.get(index);

				cb_day.setText(bean.day.get(Calendar.DAY_OF_MONTH) + "");
				if (bean.day.get(Calendar.YEAR) <= todayCalendar
						.get(Calendar.YEAR)
						&& bean.day.get(Calendar.MONTH) <= todayCalendar
								.get(Calendar.MONTH)
						&& bean.day.get(Calendar.DAY_OF_MONTH) < todayCalendar
								.get(Calendar.DAY_OF_MONTH)) {

					cb_day.setAlpha(0.5f);
					tv_week.setAlpha(0.5f);
					point.setBackgroundResource(R.drawable.point_gary);
					cb_day.setClickable(false);
				} else if (bean.day.get(Calendar.YEAR) <= todayCalendar
						.get(Calendar.YEAR)
						&& bean.day.get(Calendar.MONTH) < todayCalendar
								.get(Calendar.MONTH)) {
					cb_day.setAlpha(0.5f);
					tv_week.setAlpha(0.5f);
					point.setBackgroundResource(R.drawable.point_gary);
					cb_day.setClickable(false);
				}
				tv_week.setText(bean.week);
				if (bean.isHaveData) {
					point.setVisibility(View.VISIBLE);
				} else {
					point.setVisibility(View.INVISIBLE);
				}

				if (bean.isToday) {
					tv_today.setVisibility(View.VISIBLE);
					cb_day.setChecked(true);
				} else {
					tv_today.setVisibility(View.INVISIBLE);
					cb_day.setChecked(false);
				}

				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.WRAP_CONTENT,
						LinearLayout.LayoutParams.WRAP_CONTENT);

				params.weight = 1;

				v.setLayoutParams(params);
				cb_day.setTag(index + "");
				cbList.add(cb_day);

				cb_day.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton cb, boolean flag) {

						if (flag) {
							for (CheckBox box : cbList) {

								if (!box.getTag().equals(cb.getTag())) {
									box.setChecked(false);
								}
							}

							if (changeListener != null) {
								changeListener.onItemCheckedChanged(index, cb,
										flag);
							}
						}

					}
				});

				ll.addView(v);

			}

			viewList.add(ll);

		}
	}

	class ViewPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return viewList.size();
		}

		/**
		 * 判断出去的view是否等于进来的view 如果为true直接复用
		 */
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		/**
		 * 销毁预加载以外的view对象, 会把需要销毁的对象的索引位置传进来就是position
		 */
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(viewList.get(position % viewList.size()));
		}

		/**
		 * 创建一个view
		 */
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(viewList.get(position % viewList.size()));
			return viewList.get(position % viewList.size());
		}
	}

	public interface OnItemCheckedChangeListener {

		public void onItemCheckedChanged(int index, CompoundButton cb,
				boolean flag);

	}

	public static class CalendarBean {

		public Calendar day;

		public String week;
		public boolean isHaveData;
		public boolean isToday;

		public CalendarBean(Calendar day, String week, boolean isHaveData,
				boolean isToday) {
			super();
			this.day = day;

			this.week = week;
			this.isHaveData = isHaveData;
			this.isToday = isToday;

		}

		public CalendarBean() {
			super();
			// TODO Auto-generated constructor stub
		}

	}

}
