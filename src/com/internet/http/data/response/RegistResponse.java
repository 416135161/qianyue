package com.internet.http.data.response;

import com.internet.http.data.response.LoginResponse.LoginResponseEntity;

public class RegistResponse extends CommonResponse {
	LoginResponseEntity result;

	public RegistResponse() {
		// TODO Auto-generated constructor stub
	}

	public LoginResponseEntity getResult() {
		return result;
	}

	public void setResult(LoginResponseEntity result) {
		this.result = result;
	}
}
