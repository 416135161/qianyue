package com.internet.http.data.post;

public class OrderListPost {
	public String sign;

	public String subjectCode;// 科目类型:练场地、连路、陪练 默认值: 1、2、3

	public String driverId;// 教练id

	public String date;// 日期，格式：2016-02-10

	public String pageNo;// 需要查看的页码

	public String pageSize;

	public OrderListPost() {
		// TODO Auto-generated constructor stub
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
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

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
	

}
