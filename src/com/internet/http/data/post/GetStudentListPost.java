package com.internet.http.data.post;

public class GetStudentListPost {
	public String sign;   //签名[必填]
	
	public String driverId;// 教练id

	public String type;// 类型：1:线下学员；2：线上学员[选填]

	public String keyword;// 姓名、手机号[选填]

	public String pageNo = "1";// 页码，默认1

	public String pageSize = "1000";// 页码大小，默认10

	public GetStudentListPost() {
		// TODO Auto-generated constructor stub
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
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
