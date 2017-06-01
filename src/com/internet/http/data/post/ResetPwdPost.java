/**  
 * @author ningsj@shishike.com
 * @Title: ResetPwdPost.java 
 * @Package com.internet.yousheng.http.data.post 
 * @Description: TODO
 * @date 2015-4-26 下午1:16:07 
 * @version V1.0  
 */
package com.internet.http.data.post;

/**
 * @Author: ningsj@shishike.com
 * @Date：2015-4-26 下午1:16:07
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All
 *                      rights reserved.
 */
public class ResetPwdPost {
	public String mobileNo; // 手机号
	
	public String password;// 密码(必填)
	
	public String code;// 验证码(必填)
	
	/**
	 * @Constructor
	 * @Description TODO
	 */
	public ResetPwdPost() {
		// TODO Auto-generated constructor stub
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
