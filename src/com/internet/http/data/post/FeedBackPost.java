package com.internet.http.data.post;

public class FeedBackPost {
	public String sign;// 签名[必填]

	public String content;// 反馈内容[必填]

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
