/**  
 * @author ningsj@shishike.com
 * @Title: TextSearchPost.java 
 * @Package com.internet.yousheng.http.data.post 
 * @Description: TODO
 * @date 2015-4-16 下午6:36:14 
 * @version V1.0  
 */
package com.internet.http.data.post;

/**
 * @Author: ningsj@shishike.com
 * @Date：2015-4-16 下午6:36:14
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All
 *                      rights reserved.
 */
public class TextSearchPost {
	public String mobileNo; // 手机号
	
	public String txt;// 搜索的文字

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}
	
	
}
