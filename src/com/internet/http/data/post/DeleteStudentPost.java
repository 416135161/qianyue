package com.internet.http.data.post;

public class DeleteStudentPost {
	public String sign;// 签名 [必填]

	public String id;// 学员id [必填]

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
