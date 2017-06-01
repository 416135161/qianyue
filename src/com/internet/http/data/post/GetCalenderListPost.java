package com.internet.http.data.post;

public class GetCalenderListPost {
	public String date;// 日期 date=yyyy-MM-dd

	public String scheduleTypeCode;// 日程类型:练场地、练路、陪练 scheduleTypeCode=1\2\3

	public String driverId;// 教练id driverId=xx

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getScheduleTypeCode() {
		return scheduleTypeCode;
	}

	public void setScheduleTypeCode(String scheduleTypeCode) {
		this.scheduleTypeCode = scheduleTypeCode;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	
	
}
