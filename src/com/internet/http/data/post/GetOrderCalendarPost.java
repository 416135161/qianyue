package com.internet.http.data.post;

public class GetOrderCalendarPost {
	public String sign; // 签名[必填]

	public String subjectCode;// 科目类型:练场地、连路、陪练 默认值: 1、2、3

	public String driverId;// 教练id

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
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

}
