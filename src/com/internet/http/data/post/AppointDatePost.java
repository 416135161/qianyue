package com.internet.http.data.post;

public class AppointDatePost {
	public String sign;

	public String driverId;// 教练id

	public String startDate;// 日期 date=yyyy-MM-dd

	public String endDate;// 日期 date=yyyy-MM-dd

	public String scheduleTypeCode;// 日程类型:练场地、练路、陪练 scheduleTypeCode=1\2\3

	public AppointDatePost() {
		// TODO Auto-generated constructor stub
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getScheduleTypeCode() {
		return scheduleTypeCode;
	}

	public void setScheduleTypeCode(String scheduleTypeCode) {
		this.scheduleTypeCode = scheduleTypeCode;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
