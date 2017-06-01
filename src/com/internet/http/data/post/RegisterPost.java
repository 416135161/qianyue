/**  
 * @author ningsj@shishike.com
 * @Title: RegisterPost.java 
 * @Package com.internet.yousheng.http.data.post 
 * @Description: TODO
 * @date 2015-4-16 下午5:11:52 
 * @version V1.0  
 */
package com.internet.http.data.post;

/**
 * @Author: ningsj@shishike.com
 * @Date：2015-4-16 下午5:11:52
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All
 *                      rights reserved.
 */
public class RegisterPost {
	public String mobileNo;// 手机号
	
	public String password;// 密码
	
	public String code; //  验证码
	
	

	
	public RegisterPost(String mobileNo, String password, String code) {
		super();
		this.mobileNo = mobileNo;
		this.password = password;
		this.code = code;
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
