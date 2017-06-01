package com.internet.http.data.response;

import java.util.ArrayList;

public class GetOrderCalendarResponse extends CommonResponse {

	ArrayList<OrderCalendar> result;

	public ArrayList<OrderCalendar> getResult() {
		return result;
	}

	public void setResult(ArrayList<OrderCalendar> result) {
		this.result = result;
	}

	public class OrderCalendar {
		String date;// Tue Feb 23 01:16:50 CST 2016、FieldType=class
					// java.util.Date、日期
		String strDate;// 2016-02-23、FieldType=class
						// java.lang.String、日期yyyy-MM-dd
		int dayOfMonth;// 23、FieldType=int、号数
		boolean toDay;// true、FieldType=boolean、是否今天
		boolean havePublish;// false、FieldType=boolean、是否有发布
		int orderNum;// null、FieldType=class java.lang.Integer、订单数量
		

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getStrDate() {
			return strDate;
		}

		public void setStrDate(String strDate) {
			this.strDate = strDate;
		}

		public int getDayOfMonth() {
			return dayOfMonth;
		}

		public void setDayOfMonth(int dayOfMonth) {
			this.dayOfMonth = dayOfMonth;
		}

		public boolean isToDay() {
			return toDay;
		}

		public void setToDay(boolean toDay) {
			this.toDay = toDay;
		}

		public boolean isHavePublish() {
			return havePublish;
		}

		public void setHavePublish(boolean havePublish) {
			this.havePublish = havePublish;
		}

		public int getOrderNum() {
			return orderNum;
		}

		public void setOrderNum(int orderNum) {
			this.orderNum = orderNum;
		}

	}

}
