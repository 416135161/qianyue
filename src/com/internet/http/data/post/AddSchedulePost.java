package com.internet.http.data.post;

public class AddSchedulePost {
	
	public String sign;
	public String date;
	public String scheduleTypeCode;
	public String driverId;
	public String siteId;
//	public String siteType;
	public String strTimeOfDayVOs;
	public AddSchedulePost(String sign, String date, String scheduleTypeCode,
			String driverId, String siteId, String siteType,
			String strTimeOfDayVOs) {
		super();
		this.sign = sign;
		this.date = date;
		this.scheduleTypeCode = scheduleTypeCode;
		this.driverId = driverId;
		this.siteId = siteId;
//		this.siteType = siteType;
		this.strTimeOfDayVOs = strTimeOfDayVOs;
	}
	
	
	
	

}
