package com.internet.act;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

import android.os.Bundle;

import com.internet.basic.BasicActivity;
import com.internet.turnright.b.R;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;

@EActivity(R.layout.act_launcher)
public class LauncherAct extends BasicActivity {
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		UmengUpdateAgent.setUpdateAutoPopup(false);
		UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
			@Override
			public void onUpdateReturned(int updateStatus,
					UpdateResponse updateInfo) {
				switch (updateStatus) {
				case UpdateStatus.Yes: // has update
					UmengUpdateAgent.showUpdateDialog(getApplicationContext(),
							updateInfo);
					break;
				case UpdateStatus.No: // has no update
				case UpdateStatus.NoneWifi: // none wifi
				case UpdateStatus.Timeout: // time out
					startWelcomeAct();
					break;
				}
			}
		});
		UmengUpdateAgent.update(this);
	}

	@UiThread(delay = 800)
	void startWelcomeAct() {
		WelcomeAct_.intent(this).start();
		finish();
	}
}
