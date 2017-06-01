package com.internet.http.data.post;

public class GetMessageQuntityPost {
	public String sign;// 签名 [必填]

	public String type;// 消息类型 1:系统消息；2：练路消息；3：评价消息；4：预约消息

	public String subType;// 消息子类型 待定

	public String status;// 消息状态 1：未读；2：已读；3：删除

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
