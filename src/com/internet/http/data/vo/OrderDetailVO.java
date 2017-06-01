package com.internet.http.data.vo;

public class OrderDetailVO {
	public ScheduleVO scheduleVO;
	public BaseUserVO baseUserVO;
	public BaseEvaluateInfoVO evaluateInfo;

	public String myAppointmentId;//、FieldValue=null、FieldType=class java.lang.Long、用户预约ID
	public String orderId;//、FieldValue=null、FieldType=class java.lang.Long、订单id
	public String orderSn;//、FieldValue=null、FieldType=class java.lang.Long、订单编号
	public String status;//、FieldValue=null、FieldType=class java.lang.Integer、教练预约状态:未开始,进行中,已完成

	public String userId;//、FieldValue=null、FieldType=class java.lang.String、用户ID
	public String driverId;//、FieldValue=null、FieldType=class java.lang.Long、教练id
	public String driverName;//、FieldValue=null、FieldType=class java.lang.String、教练姓名
	public String driverTeachAge;//、FieldValue=null、FieldType=class java.lang.Integer、教龄
	public String driverIntroduction;//、FieldValue=null、FieldType=class java.lang.String、简介
	public String driverAvatar;//、FieldValue=null、FieldType=class java.lang.String、头像
	public String driverSex;//、FieldValue=null、FieldType=class java.lang.Integer、性别
	public String driverCarName;//、FieldValue=null、FieldType=class java.lang.String、车辆名称
	public String dirverCarNumber;//、FieldValue=null、FieldType=class java.lang.String、车牌号码
	public String authStatus;//、FieldValue=null、FieldType=class java.lang.Integer、教练认证状态
	public String driverScore;//、FieldValue=null、FieldType=class java.lang.Integer、评分
	public String subjectCode;//、FieldValue=null、FieldType=class java.lang.String、科目Code
	public String subjectName;//、FieldValue=null、FieldType=class java.lang.String、科目名称
	public String subjectTotalTime;//、FieldValue=null、FieldType=class java.lang.Integer、订单总时长
	public String incomeMoney;//、FieldValue=null、FieldType=class java.math.BigDecimal、实际收入
	public String serviceMoney;//、FieldValue=null、FieldType=class java.lang.Integer、平台服务费金额
	public String awardMoney;//、FieldValue=null、FieldType=class java.lang.Integer、平台奖励

	public String siteId;//、FieldValue=null、FieldType=class java.lang.Long、场地id
	public String siteName;//、FieldValue=null、FieldType=class java.lang.String、场地名称
	public String siteLongitude;//、FieldValue=null、FieldType=class java.lang.Double、场地经度
	public String siteLatitude;//、FieldValue=null、FieldType=class java.lang.Double、场地纬度
	public String statusName;//、FieldValue=null、FieldType=class java.lang.String、教练预约状态中文

	public class ScheduleVO {
		public String calenderDate;// ": 1465833600000,
		public String endTime;// ": "09:00",
		public String isVouch;// ": 0,
		public String scheduleCurrentNum;// ": 0,
		public String scheduleEndTime;// ": 1465866000000,
		public String scheduleId;// ": 0,
		public String scheduleMinNum;// ": 0,
		public String schedulePrice;// ": 10,
		public String scheduleStartTime;// ": 1465862400000,
		public String startTime;// ": "08:00",
		public String strCalenderDate;// ": "06月14日",
		public String weekDay;// ": "周二"
	}

	public class BaseUserVO {
		public String authStatus;// ": 0,
		public String userAge;// ": 0,
		public String userAlias;// ": "今天",
		public String userAvatar;// ": "http://7xsask.com1.z0.glb.clouddn.com////////////////////1464844756938.jpeg",
		public String userBirthday;// ": null,
		public String userId;// ": 101,
		public String userLevel;// ": 0,
		public String userMobile;// ": 15680737627,
		public String userName;// ": "15680737627",
		public String userScore;// ": 0,
		public String userSex;// ": 1,
		public String userStatus;// ": "2"
	}
	
	public class BaseEvaluateInfoVO {
		public String evaluateId;//FieldValue=null、FieldType=class java.lang.Long、评论id
		public String userId;//、FieldValue=null、FieldType=class java.lang.String、评论人id
		public String userName;//、FieldValue=null、FieldType=class java.lang.String、评论人名称
		public String userType;//、FieldValue=0、FieldType=int、评论人类型
		public String userAvatar;//、FieldValue=null、FieldType=class java.lang.String、评论人头像
		public String zhunshi;//、FieldValue=null、FieldType=class java.lang.String、准时
		public String fuwu;//、FieldValue=null、FieldType=class java.lang.String、服务
		public String taidu;//、FieldValue=null、FieldType=class java.lang.String、态度
	}
}
