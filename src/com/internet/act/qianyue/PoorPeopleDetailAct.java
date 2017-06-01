package com.internet.act.qianyue;

import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.TextView;

import com.internet.adapter.FragmentAdapter;
import com.internet.fragment.Fragment1_;
import com.internet.fragment.Fragment2_;
import com.internet.fragment.Fragment3_;
import com.internet.fragment.Fragment4_;

import com.internet.turnright.b.R;
import com.internet.view.HeaderView;

@EActivity(R.layout.act_poor_people_detail)
public class PoorPeopleDetailAct extends FragmentActivity {

	@ViewById(R.id.id_page_vp)
	ViewPager mPageVp;

	@ViewById
	TextView id_text1, id_text2, id_text3, id_text4;
	@ViewById
	HeaderView view_header;

	@AfterViews
	void init() {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		view_header.setTitle("帮扶对象");
		List<Fragment> fragmentList = new ArrayList<Fragment>();
		FragmentAdapter fragmentAdapter;
		fragmentList.add(Fragment1_.builder().build());
		fragmentList.add(Fragment2_.builder().build());
		fragmentList.add(Fragment3_.builder().build());
		fragmentList.add(Fragment4_.builder().build());

		fragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(),
				fragmentList);
		mPageVp.setAdapter(fragmentAdapter);
		mPageVp.setCurrentItem(0);

		mPageVp.setOnPageChangeListener(new OnPageChangeListener() {

			/**
			 * state滑动中的状态 有三种状态（0，1，2） 1：正在滑动 2：滑动完毕 0：什么都没做。
			 */
			@Override
			public void onPageScrollStateChanged(int state) {

			}

			/**
			 * position :当前页面，及你点击滑动的页面 offset:当前页面偏移的百分比
			 * offsetPixels:当前页面偏移的像素位置
			 */
			@Override
			public void onPageScrolled(int position, float offset,
					int offsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
				resetTextView();
				switch (position) {
				case 0:
					id_text1.setBackgroundColor(Color.BLUE);
					break;
				case 1:
					id_text2.setBackgroundColor(Color.BLUE);
					break;
				case 2:
					id_text3.setBackgroundColor(Color.BLUE);
					break;
				case 3:
					id_text4.setBackgroundColor(Color.BLUE);
				}

			}
		});

	}

	@Click(R.id.id_text1)
	void clickText1() {
		mPageVp.setCurrentItem(0);
	}

	@Click(R.id.id_text2)
	void clickText2() {
		mPageVp.setCurrentItem(1);
	}

	@Click(R.id.id_text3)
	void clickText3() {
		mPageVp.setCurrentItem(2);
	}

	@Click(R.id.id_text4)
	void clickText4() {
		mPageVp.setCurrentItem(3);
	}

	/**
	 * 重置颜色
	 */
	private void resetTextView() {
		id_text1.setBackgroundColor(Color.WHITE);
		id_text2.setBackgroundColor(Color.WHITE);
		id_text3.setBackgroundColor(Color.WHITE);
		id_text4.setBackgroundColor(Color.WHITE);
	}

}
