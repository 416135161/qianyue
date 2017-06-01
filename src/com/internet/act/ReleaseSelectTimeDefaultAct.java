package com.internet.act;

import java.util.ArrayList;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.internet.action.ActionReleaseSuccess;
import com.internet.app.MainApplication;
import com.internet.basic.BasicActivity;
import com.internet.db.SpHelper;
import com.internet.http.api.ApiException;
import com.internet.http.api.ApiManager;
import com.internet.http.data.post.ReleaseCalenderPost;
import com.internet.http.data.vo.SiteVO;
import com.internet.http.data.vo.UserInfoVO;
import com.internet.turnright.b.R;
import com.internet.util.JsonUtil;
import com.internet.view.HeaderView;

import de.greenrobot.event.EventBus;

@EActivity(R.layout.act_release_select_time_default)
public class ReleaseSelectTimeDefaultAct extends BasicActivity {
	public static final String INTENT_FLAG_DATE = "DATE";
	public static final String INTENT_FLAG_SITE = "SITE";

	private SiteVO mSiteVO;

	private String mDate;

	@ViewById
	HeaderView view_header;

	@ViewById
	EditText edit_price;

	@ViewById
	CheckBox check1, check2, check3, check4, check5, check6;

	@ViewById
	TextView text1, text2, text3, text4, text5, text6;

	private String mPrice = "";
	private int mDayOfWeek;

	@AfterViews
	void init() {
		mSiteVO = (SiteVO) getIntent().getSerializableExtra(INTENT_FLAG_SITE);
		mDate = getIntent().getStringExtra(INTENT_FLAG_DATE);
		mDayOfWeek = getIntent().getIntExtra(ReleaseSelectTimeSelfAct.DAY_OF_WEEK,1);
		view_header.setTitle("推荐时段");
		text1.setText("08:00～09:00");
		text2.setText("10:00～11:00");
		text3.setText("11:00～12:00");
		text4.setText("14:00～15:00");
		text5.setText("15:00～16:00");
		text6.setText("17:00～18:00");

	}

	@Click(R.id.btn_save)
	void clickSave() {
		mPrice = edit_price.getEditableText().toString();
		if (TextUtils.isEmpty(mPrice)) {
			showToast("请输入预约金额！");
			return;
		}
		doRelease();
	}

	@Background
	void doRelease() {
		showLoading();
		ArrayList<ReleaseCalenderPost.DriverCalender> calenderList = new ArrayList<ReleaseCalenderPost.DriverCalender>();
		addCalender(calenderList, check1, text1);
		addCalender(calenderList, check2, text2);
		addCalender(calenderList, check3, text3);
		addCalender(calenderList, check4, text4);
		addCalender(calenderList, check5, text5);
		addCalender(calenderList, check6, text6);
		boolean response = false;
		try {
			ReleaseCalenderPost data = new ReleaseCalenderPost();

			data.setCalenderDate(mDate);
			data.setDiverCalenderstr(JsonUtil.objectToJson(calenderList));
			UserInfoVO userInfo = MainApplication.getInstance().getUserInfo();
			data.setDriverStr(JsonUtil
					.objectToJson(new ReleaseCalenderPost.Driver(userInfo
							.getDriverId(), userInfo.getDriverName())));
			data.setIsRepeat("0");
			data.setScheduleTypeStr(JsonUtil
					.objectToJson(new ReleaseCalenderPost.ScheduleType("1",
							"练场地")));
			data.setSign(SpHelper.getDefault().getString(SpHelper.SIGN));
			data.setSiteStr(JsonUtil.objectToJson(new ReleaseCalenderPost.Site(
					mSiteVO.getId(), mSiteVO.getSiteName())));

			response = ApiManager.getDefault().releaseCalender(data);

		} catch (ApiException e) {
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
		closeLoading();
		if (response) {
			showToast("发布成功！");
			
			ReleaseSiteAct_.IntentBuilder_ build = ReleaseSiteAct_.intent(this);
			build.get().putExtra(ReleaseSelectTimeSelfAct.DAY_OF_WEEK, mDayOfWeek);
			build.start();
			
			//post(new ActionReleaseSuccess(1));
			finish();
		}
	}

	private void addCalender(
			ArrayList<ReleaseCalenderPost.DriverCalender> calenderList,
			CheckBox checkBox, TextView textView) {
		if (checkBox.isChecked()) {
			String time_from = textView.getText().toString().substring(0, 5);
			String time_to = textView.getText().toString().substring(6);
			calenderList.add(new ReleaseCalenderPost.DriverCalender(null, mDate
					+ " " + time_from + ":00", mDate + " " + time_to + ":00",
					mPrice));
		}
	}

	@UiThread
	void exit() {
		//post(new ActionReleaseSuccess());
		finish();
	}

}
