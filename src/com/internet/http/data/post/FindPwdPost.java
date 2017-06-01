package com.internet.http.data.post;

public class FindPwdPost  {
	public String mobileNo;// 手机号
	
	public String password;// 密码
	
	public String code; //  验证码
	
	

	
	public FindPwdPost(String mobileNo, String password, String code) {
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
