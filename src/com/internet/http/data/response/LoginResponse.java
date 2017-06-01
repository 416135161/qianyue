package com.internet.http.data.response;

public class LoginResponse extends CommonResponse {
	LoginResponseEntity result;

	public LoginResponse() {
		// TODO Auto-generated constructor stub
	}

	public LoginResponseEntity getResult() {
		return result;
	}

	public void setResult(LoginResponseEntity result) {
		this.result = result;
	}

	public static class LoginResponseEntity {
		// 序列
		String jsessionId;
		// 用户唯一标识
		String userId;
		// 类型
		int userType;
		// 第三方登录类型
		int tuType;
		// 名称
		String userName;
		// 别名
		String userAlias;
		// 头像
		String avatar;
		// 用户唯一标识
		String token;
		// 最后登录时间
		long loginTime;
		// 签名
		String sign;

		public LoginResponseEntity() {
			super();
			// TODO Auto-generated constructor stub
		}

		public String getJsessionId() {
			return jsessionId;
		}

		public void setJsessionId(String jsessionId) {
			this.jsessionId = jsessionId;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public int getUserType() {
			return userType;
		}

		public void setUserType(int userType) {
			this.userType = userType;
		}

		public int getTuType() {
			return tuType;
		}

		public void setTuType(int tuType) {
			this.tuType = tuType;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getUserAlias() {
			return userAlias;
		}

		public void setUserAlias(String userAlias) {
			this.userAlias = userAlias;
		}

		public String getAvatar() {
			return avatar;
		}

		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public long getLoginTime() {
			return loginTime;
		}

		public void setLoginTime(long loginTime) {
			this.loginTime = loginTime;
		}

		public String getSign() {
			return sign;
		}

		public void setSign(String sign) {
			this.sign = sign;
		}

	}
}
