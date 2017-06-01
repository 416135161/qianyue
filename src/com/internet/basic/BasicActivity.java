package com.internet.basic;

import java.util.List;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Instrumentation;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import cn.jpush.android.api.JPushInterface;

import com.internet.act.LoginAct_;
import com.internet.action.ActionHidenInput;
import com.internet.action.ActionToLogin;
import com.internet.dialog.DialogLoading;
import com.internet.dialog.DialogLoading_;
import com.internet.http.api.ApiException;
import com.internet.util.ToastUtil;

import de.greenrobot.event.EventBus;

@EActivity
public class BasicActivity extends Activity {

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		registerEventBus();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		closeInputKeyboard();
		JPushInterface.onResume(this);

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		JPushInterface.onPause(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterEventBus();
	}

	/**
	 * 用EventBus发送一个事件
	 * 
	 * @param obj
	 */
	public void post(Object obj) {
		EventBus.getDefault().post(obj);
	}

	/**
	 * 用EventBus发送一个Sticky事件
	 * 
	 * @param obj
	 */
	public void postSticky(Object obj) {
		EventBus.getDefault().postSticky(obj);
	}

	/**
	 * 统一处理接口异常,
	 * 
	 * @param ex
	 */
	@UiThread
	public void onApiException(ApiException ex) {
		ToastUtil.showShortToast(this, ex.getErrorMessage());
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (deelInputKeyboard()) {
			post(new ActionHidenInput());
			return true;
		}
		return super.dispatchTouchEvent(ev);
	}

	@UiThread(delay = 200)
	public void closeInputKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(
				getWindow().getDecorView().getWindowToken(), 0);
	}

	private boolean deelInputKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		return imm.hideSoftInputFromWindow(getWindow().getDecorView()
				.getWindowToken(), 0);
	}

	@UiThread
	public void showToast(String content) {
		ToastUtil.showShortToast(getApplicationContext(), content);
	}

	private DialogLoading dialogLoading;

	@UiThread
	public void showLoading() {
		showLoading("");
	}

	@UiThread
	public void showLoading(String tip) {
		if (dialogLoading == null)
			dialogLoading = DialogLoading_.builder().build();
		if (TextUtils.isEmpty(tip))
			tip = "数据获取中...";
		dialogLoading.setTip(tip);
		dialogLoading.show(getFragmentManager(), "");
	}

	@UiThread
	public void closeLoading() {
		if (dialogLoading != null) {
			try {
				dialogLoading.dismiss();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Background
	protected void doBack() {
		Instrumentation inst = new Instrumentation();
		inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
	}

	@UiThread
	public void fullScreen(boolean enable) {
		if (enable) {
			WindowManager.LayoutParams lp = getWindow().getAttributes();
			lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
			getWindow().setAttributes(lp);
			getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		} else {
			WindowManager.LayoutParams attr = getWindow().getAttributes();
			attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
			getWindow().setAttributes(attr);
			getWindow().clearFlags(
					WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		}
	}

	protected void registerEventBus() {
		if (!EventBus.getDefault().isRegistered(this)) {
			EventBus.getDefault().register(this);
		}
	}

	protected void unregisterEventBus() {
		if (EventBus.getDefault().isRegistered(this)) {
			EventBus.getDefault().unregister(this);
		}
	}

	public void onEventMainThread(ActionToLogin action) {
		// if(this.getClass().getName())
		ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

		List<ActivityManager.RunningTaskInfo> infos = manager
				.getRunningTasks(1);
		for (ActivityManager.RunningTaskInfo runningTaskInfo : infos) {
			String name = runningTaskInfo.baseActivity.getClassName();
			if (name.equals("com.internet.act.LoginAct_")) {
				finish();
				return;
			}
		}

		if (isTaskRoot()) {
			LoginAct_.intent(this).start();
		}
		finish();
	}
}
