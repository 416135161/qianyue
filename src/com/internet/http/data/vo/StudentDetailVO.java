package com.internet.http.data.vo;

import java.io.Serializable;
import java.util.ArrayList;

import com.internet.basic.AdapterData;

public class StudentDetailVO {

	String userId;// java.lang.Long、用户ID
	String userName;// java.lang.String、用户账号
	String userAlias;// java.lang.String、用户昵称
	String userMobile;// java.lang.Long、用户手机号
	String userAvatar;// java.lang.String、用户头像
	String signName;// java.lang.String、报名方式
	String licenseTypeName;// java.lang.String、驾照类型
	ArrayList<UserAppoint> userAppoints;// java.util.List、用户练车记录

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAlias() {
		return userAlias;
	}

	public void setUserAlias(String userAlias) {
		this.userAlias = userAlias;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public String getLicenseTypeName() {
		return licenseTypeName;
	}

	public void setLicenseTypeName(String licenseTypeName) {
		this.licenseTypeName = licenseTypeName;
	}

	public ArrayList<UserAppoint> getUserAppoints() {
		return userAppoints;
	}

	public void setUserAppoints(ArrayList<UserAppoint> userAppoints) {
		this.userAppoints = userAppoints;
	}

	public class UserAppoint implements AdapterData, Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public long userId;// 学员id
		public String userName;// 学员姓名
		public String driverId;// 教练id
		public String driverName;// 教练姓名
		public String siteId;// 场地id
		public String siteName;// 场地名称
		public String scheduleId;// 日程id
		public long scheduleStartTime;// 开始时间
		public long scheduleEndTime;// 结束时间
		public String subjectCode;// 科目Code
		public String subjectName;// 科目名称
		public long exerciseTimes;// 练习时长
		public String orderState;// 订单状态

		public long getUserId() {
			return userId;
		}

		public void setUserId(long userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getDriverId() {
			return driverId;
		}

		public void setDriverId(String driverId) {
			this.driverId = driverId;
		}

		public String getDriverName() {
			return driverName;
		}

		public void setDriverName(String driverName) {
			this.driverName = driverName;
		}

		public String getSiteId() {
			return siteId;
		}

		public void setSiteId(String siteId) {
			this.siteId = siteId;
		}

		public String getSiteName() {
			return siteName;
		}

		public void setSiteName(String siteName) {
			this.siteName = siteName;
		}

		public String getScheduleId() {
			return scheduleId;
		}

		public void setScheduleId(String scheduleId) {
			this.scheduleId = scheduleId;
		}

		public long getScheduleStartTime() {
			return scheduleStartTime;
		}

		public void setScheduleStartTime(long scheduleStartTime) {
			this.scheduleStartTime = scheduleStartTime;
		}

		public long getScheduleEndTime() {
			return scheduleEndTime;
		}

		public void setScheduleEndTime(long scheduleEndTime) {
			this.scheduleEndTime = scheduleEndTime;
		}

		public String getSubjectCode() {
			return subjectCode;
		}

		public void setSubjectCode(String subjectCode) {
			this.subjectCode = subjectCode;
		}

		public String getSubjectName() {
			return subjectName;
		}

		public void setSubjectName(String subjectName) {
			this.subjectName = subjectName;
		}

		public long getExerciseTimes() {
			return exerciseTimes;
		}

		public void setExerciseTimes(long exerciseTimes) {
			this.exerciseTimes = exerciseTimes;
		}

		public String getOrderState() {
			return orderState;
		}

		public void setOrderState(String orderState) {
			this.orderState = orderState;
		}

	}

}
