/**  
 * @author ningsj@shishike.com
 * @Title: ActionSelectBirthday.java 
 * @Package com.internet.yousheng.app.action 
 * @Description: TODO
 * @date 2015-4-21 下午4:22:12 
 * @version V1.0  
 */
package com.internet.action;

/**
 * @Author: ningsj@shishike.com
 * @Date：2015-4-21 下午4:22:12
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All
 *                      rights reserved.
 */
public class ActionSelectBirthday {
	
	long birthday;
	
	/**
	 * @Constructor
	 * @Description TODO
	 */
	public ActionSelectBirthday(long birthday) {
		// TODO Auto-generated constructor stub
		this.birthday = birthday;
	}
	
	public long getBirthday() {
		return birthday;
	}
	
	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}
	
}
