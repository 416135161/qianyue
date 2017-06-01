package com.internet.http.data.response;

public class GetYzwMessageResponse extends CommonResponse {

	YzwMessage result;

	public YzwMessage getResult() {
		return result;
	}

	public void setResult(YzwMessage result) {
		this.result = result;
	}

	public class YzwMessage {
		public String phone; // 电话
		public String introduce; // 介绍
	}
}
