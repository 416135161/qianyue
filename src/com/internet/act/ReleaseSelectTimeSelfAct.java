package com.internet.act;

import java.util.ArrayList;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.internet.action.ActionReleaseSuccess;
import com.internet.action.ActionSelectTime;
import com.internet.app.MainApplication;
import com.internet.basic.BasicActivity;
import com.internet.db.SpHelper;
import com.internet.dialog.DialogSelectTime;
import com.internet.dialog.DialogSelectTime_;
import com.internet.http.api.ApiException;
import com.internet.http.api.ApiManager;
import com.internet.http.data.post.ReleaseCalenderPost;
import com.internet.http.data.vo.SiteVO;
import com.internet.http.data.vo.UserInfoVO;
import com.internet.turnright.b.R;
import com.internet.util.DateTimeUtil;
import com.internet.util.JsonUtil;
import com.internet.view.HeaderView;

@EActivity(R.layout.act_release_select_time_self)
public class ReleaseSelectTimeSelfAct extends BasicActivity {

	public static final String INTENT_FLAG_DATE = "DATE";
	public static final String INTENT_FLAG_SITE = "SITE";
	public static final String DAY_OF_WEEK = "dayOfWeek";  

	private SiteVO mSiteVO;

	private String mDate;
	
	private int mDayOfWeek;

	@ViewById
	HeaderView view_header;

	@ViewById
	Button btn_save;

	@ViewById
	TextView time_from, time_to;

	@ViewById
	EditText edit_price;

	final int from = 1, to = 2;
	int type = from;

	private String mPrice = "";

	@AfterViews
	void init() {
		mSiteVO = (SiteVO) getIntent().getSerializableExtra(INTENT_FLAG_SITE);
		mDate = getIntent().getStringExtra(INTENT_FLAG_DATE);
		mDayOfWeek = getIntent().getIntExtra(ReleaseSelectTimeSelfAct.DAY_OF_WEEK,1);
		view_header.setTitle("场地-时段发布");
		view_header.setRight("推荐时间");
		btn_save.setText("发布");
		registerEventBus();
	}

	@Click(R.id.text_header_right)
	void clickHeaderRight() {
		ReleaseSelectTimeDefaultAct_.IntentBuilder_ build = ReleaseSelectTimeDefaultAct_
				.intent(this);
		build.get().putExtra(ReleaseSelectTimeSelfAct.INTENT_FLAG_DATE, mDate);
		build.get()
				.putExtra(ReleaseSelectTimeSelfAct.INTENT_FLAG_SITE, mSiteVO);
		
		build.get().putExtra(ReleaseSelectTimeSelfAct.DAY_OF_WEEK, mDayOfWeek);
		build.start();
	}

	@Click(R.id.time_from)
	void clickTimeFrom() {
		type = from;
		String[] array = time_from.getText().toString().split(":");
		DialogSelectTime dialog = DialogSelectTime_.builder().build();
		dialog.getArguments().putInt(DialogSelectTime.HOUR_KEY,
				Integer.valueOf(array[0]));
		dialog.getArguments().putInt(DialogSelectTime.MINUTE_KEY,
				Integer.valueOf(array[1]));
		dialog.show(getFragmentManager(), "");
	}

	@Click(R.id.time_to)
	void clickTimeTo() {
		type = to;
		String[] array = time_to.getText().toString().split(":");
		DialogSelectTime dialog = DialogSelectTime_.builder().build();
		dialog.getArguments().putInt(DialogSelectTime.HOUR_KEY,
				Integer.valueOf(array[0]));
		dialog.getArguments().putInt(DialogSelectTime.MINUTE_KEY,
				Integer.valueOf(array[1]));
		dialog.show(getFragmentManager(), "");
	}

	@Click(R.id.btn_save)
	void clickRelease() {
		mPrice = edit_price.getEditableText().toString();
		if (TextUtils.isEmpty(mPrice)) {
			showToast("请输入预约金额！");
			return;
		}
		doRelease();
	}

	public void onEventMainThread(ActionSelectTime action) {
		String hour = action.getHour() < 10 ? "0" + action.getHour() : action
				.getHour() + "";
		String minute = action.getMinute() < 10 ? "0" + action.getMinute()
				: action.getMinute() + "";
		switch (type) {
		case from:
			time_from.setText(hour + ":" + minute);
			break;
		case to:
			time_to.setText(hour + ":" + minute);
			break;
		}

	}

	@Background
	void doRelease() {
		showLoading();
		ArrayList<ReleaseCalenderPost.DriverCalender> calenderList = new ArrayList<ReleaseCalenderPost.DriverCalender>();
		calenderList.add(new ReleaseCalenderPost.DriverCalender(null, mDate
				+ " " + time_from.getText() + ":00", mDate + " "
				+ time_to.getText() + ":00", mPrice));
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
			
			
//			ReleaseSiteAct_.IntentBuilder_ build = ReleaseSiteAct_.intent(this);
//			build.get().putExtra(ReleaseSelectTimeSelfAct.DAY_OF_WEEK, mDayOfWeek);
//			build.start();
			
			Intent mIntent = new Intent();  
		    mIntent.putExtra(ReleaseSelectTimeSelfAct.DAY_OF_WEEK, mDayOfWeek);  
		    this.setResult(1, mIntent); 
		    //post(new ActionReleaseSuccess(1));调用此方法，ReleaseSiteAct中onEventMainThread方法抛dialogLoading异常。
			finish();
			
		}
	}
	
	@UiThread
	void exit(){
		post(new ActionReleaseSuccess(1));
		finish();
	}
}
