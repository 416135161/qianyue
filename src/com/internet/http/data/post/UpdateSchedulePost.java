package com.internet.http.data.post;

public class UpdateSchedulePost {
	
	public String sign;
	public String strScheduleIds;
	public String price;
	public UpdateSchedulePost(String sign, String strScheduleIds, String price) {
		super();
		this.sign = sign;
		this.strScheduleIds = strScheduleIds;
		this.price = price;
	}
	
	

}
