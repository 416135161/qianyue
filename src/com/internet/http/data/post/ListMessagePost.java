package com.internet.http.data.post;

public class ListMessagePost {
	public String sign;// 签名 [必填]

	public String type;// 消息类型 1:系统消息；2：练路消息；3：评价消息；4：预约消息

	public String subType;// 消息子类型 待定

	public String status;// 消息状态 1：未读；2：已读；3：删除

	public String pageNo;// 页数，默认1（非必填）

	public String pageSize; // 页数大小,默认10（非必填）

	public ListMessagePost() {
		// TODO Auto-generated constructor stub
	}

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

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

}
