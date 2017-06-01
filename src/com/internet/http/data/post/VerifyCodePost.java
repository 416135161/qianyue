/**  
 * @author ningsj@shishike.com
 * @Title: VerifyCodePost.java 
 * @Package com.internet.yousheng.http.data.post 
 * @Description: TODO
 * @date 2015-4-16 下午2:14:23 
 * @version V1.0  
 */
package com.internet.http.data.post;

/**
 * @Author: ningsj@shishike.com
 * @Date：2015-4-16 下午2:14:23
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All
 *                      rights reserved.
 */
public class VerifyCodePost {
	public String mobileNo; // 手机号
	
	public String code;// 验证码
	
	/**
	 * @Constructor
	 * @Description TODO
	 */
	public VerifyCodePost() {
		// TODO Auto-generated constructor stub
	}
	
	public String getMobileNo() {
		return mobileNo;
	}
	
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
}
