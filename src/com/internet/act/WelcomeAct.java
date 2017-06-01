/**  
 * @author ningsj@shishike.com
 * @Title: WelcomeAct.java 
 * @Package com.internet.act 
 * @Description: TODO
 * @date 2015-10-30 上午11:18:37 
 * @version V1.0  
 */
package com.internet.act;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import com.internet.action.ActionLoginOK;
import com.internet.basic.BasicActivity;
import com.internet.db.SpHelper;
import com.internet.turnright.b.R;

/**
 * @Author: ningsj@shishike.com
 * @Date：2015-10-30 上午11:18:37
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All rights reserved.
 */
@EActivity(R.layout.act_welcome)
public class WelcomeAct extends BasicActivity {

	@AfterViews
	void init() {
		registerEventBus();
		if (SpHelper.getDefault().getString(SpHelper.DRIVER_ID) != null)
			MainAct_.intent(WelcomeAct.this).start();
	}

	@Click(R.id.btn_login)
	void login() {
		LoginAct_.intent(this).start();

	}

	@Click(R.id.btn_regist)
	void regist() {
		RegistAct_.intent(this).start();

	}

	public void onEventMainThread(ActionLoginOK action) {
		finish();
	}

}
