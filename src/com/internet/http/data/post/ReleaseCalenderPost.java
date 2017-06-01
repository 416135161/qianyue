/**  
 * @author ningsj@shishike.com
 * @Title: ReleaseCalender.java 
 * @Package com.internet.http.data.post 
 * @Description: TODO
 * @date 2016-1-18 下午3:25:51 
 * @version V1.0  
 */
package com.internet.http.data.post;

/**
 * @Author: ningsj@shishike.com
 * @Date：2016-1-18 下午3:25:52
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All rights reserved.
 */
public class ReleaseCalenderPost {
	public String sign;// 签名[必填]

	public String diverCalenderstr;// 需要发布的教练日程数据 数据格式：

	// diverCalenderstr=[{scheduleId:xxx,scheduleStartTime:"yyyy-MM-dd hh:mm:ss",scheduleEndTime:"yyyy-MM-dd hh:mm:ss",
	// schedulePrice:xxx}...]

	public String driverStr;// 教练driverStr={driverId:xxx,driverName:xxx}

	public String calenderDate;// 日期 calenderDate=yyyy-MM-dd

	public String siteStr;// 场地
							// siteStr={siteId:xxx,siteName:xxx}

	public String isRepeat;// 是否重复 isRepeat=0/1 0否，1是

	public String scheduleTypeStr;// 日程类型

	// scheduleTypeStr={typeDictCode:xxx,typeDictName:xxx}
	// typeDictCode:1/2/3
	// typeDictName：练场地/练路/陪驾

	public static class DriverCalender {
		public String scheduleId;
		public String scheduleStartTime;
		public String scheduleEndTime;
		public String schedulePrice;

		public DriverCalender(String scheduleId, String scheduleStartTime,
				String scheduleEndTime, String schedulePrice) {
			super();
			this.scheduleId = scheduleId;
			this.scheduleStartTime = scheduleStartTime;
			this.scheduleEndTime = scheduleEndTime;
			this.schedulePrice = schedulePrice;
		}

	}

	public static class ScheduleType {
		public String typeDictCode;
		public String typeDictName;

		public ScheduleType(String typeDictCode, String typeDictName) {
			super();
			this.typeDictCode = typeDictCode;
			this.typeDictName = typeDictName;
		}

	}

	public static class Site {
		public String siteId;
		public String siteName;

		public Site(String siteId, String siteName) {
			super();
			this.siteId = siteId;
			this.siteName = siteName;
		}
	}

	public static class Driver {
		public String driverId;
		public String driverName;

		public Driver(String driverId, String driverName) {
			super();
			this.driverId = driverId;
			this.driverName = driverName;
		}
		
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getDiverCalenderstr() {
		return diverCalenderstr;
	}

	public void setDiverCalenderstr(String diverCalenderstr) {
		this.diverCalenderstr = diverCalenderstr;
	}

	public String getDriverStr() {
		return driverStr;
	}

	public void setDriverStr(String driverStr) {
		this.driverStr = driverStr;
	}

	public String getCalenderDate() {
		return calenderDate;
	}

	public void setCalenderDate(String calenderDate) {
		this.calenderDate = calenderDate;
	}

	public String getSiteStr() {
		return siteStr;
	}

	public void setSiteStr(String siteStr) {
		this.siteStr = siteStr;
	}

	public String getIsRepeat() {
		return isRepeat;
	}

	public void setIsRepeat(String isRepeat) {
		this.isRepeat = isRepeat;
	}

	public String getScheduleTypeStr() {
		return scheduleTypeStr;
	}

	public void setScheduleTypeStr(String scheduleTypeStr) {
		this.scheduleTypeStr = scheduleTypeStr;
	}

}
