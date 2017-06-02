/**  
 * @author ningsj@shishike.com
 * @Title: ReleaseMainAct.java 
 * @Package com.internet.act 
 * @Description: TODO
 * @date 2015-11-24 下午3:07:58 
 * @version V1.0  
 */
package com.internet.act;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import com.internet.basic.BasicActivity;
import com.internet.qianyue.R;
import com.internet.view.HeaderView;

/**
 * @Author: ningsj@shishike.com
 * @Date：2015-11-24 下午3:07:58
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All
 *                      rights reserved.
 */

@EActivity(R.layout.act_release_main)
public class ReleaseMainAct extends BasicActivity {
	
	@ViewById
	HeaderView view_header;
	
	@AfterViews
	void init() {
		view_header.setTitle("信息发布");
	}
	
	@Click(R.id.btn_site)
	void site() {
		ReleaseSiteAct_.intent(this).start();
	}
	
	@Click(R.id.btn_road)
	void road() {
		
	}
	
	@Click(R.id.btn_escort)
	void escort() {
		
	}
	
}
