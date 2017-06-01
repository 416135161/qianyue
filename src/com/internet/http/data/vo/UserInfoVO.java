package com.internet.http.data.vo;

import java.io.Serializable;

public class UserInfoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	String driverId;// 、教练id

	String driverName;// 、名称

	String driverPwd;// 、密码

	String driverMobile;// 、手机号

	int driverTeachAge;// 、教龄

	String driverIntroduction;// 、简介

	String driverAvatar;// 、头像

	int driverSex;// 、性别

	long driverBirthday;// 、生日

	int driverAge;// 、年龄

	int driverScore;// 、积分

	int driverLevel;// 、等级

	int driverSginCount;// 、签到次数

	double driverLatitude;// 、经度

	double driverLongitude;// 、纬度

	long driverCreateTime;// 、创建时间

	long driverLastTime;// 、最后登录时间

	String driverStatus;// 、状态

	String driverProvinceCode;// 、省代码

	String driverProvinceName;// 、省代码名称

	String driverCityCode;// 、市代码

	String driverCityName;// 、市代码名称

	String driverAreaCode;// 、区代码

	String driverAreaName;// 、区代码名称

	String driverAddr;// 、地址

	int updatedCount;// 、修改次数

	long trainingSchoolId;// 、所属驾校id

	String trainingSchoolName;// 、所属驾校名称

	long diverCarId;// 、车辆id

	String driverCarName;// 、车辆名称

	String dirverCarNumber;// 、车牌号码

	int authStatus;// 审核状态：0:未审核  1：审核中；2:审核不通过；3：审核通过

	/**
	 * @Constructor
	 * @Description TODO
	 */
	public UserInfoVO() {
		// TODO Auto-generated constructor stub
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

	public String getDriverPwd() {
		return driverPwd;
	}

	public void setDriverPwd(String driverPwd) {
		this.driverPwd = driverPwd;
	}

	public String getDriverMobile() {
		return driverMobile;
	}

	public void setDriverMobile(String driverMobile) {
		this.driverMobile = driverMobile;
	}

	public int getDriverTeachAge() {
		return driverTeachAge;
	}

	public void setDriverTeachAge(int driverTeachAge) {
		this.driverTeachAge = driverTeachAge;
	}

	public String getDriverIntroduction() {
		return driverIntroduction;
	}

	public void setDriverIntroduction(String driverIntroduction) {
		this.driverIntroduction = driverIntroduction;
	}

	public String getDriverAvatar() {
		return driverAvatar;
	}

	public void setDriverAvatar(String driverAvatar) {
		this.driverAvatar = driverAvatar;
	}

	public int getDriverSex() {
		return driverSex;
	}

	public void setDriverSex(int driverSex) {
		this.driverSex = driverSex;
	}

	public long getDriverBirthday() {
		return driverBirthday;
	}

	public void setDriverBirthday(long driverBirthday) {
		this.driverBirthday = driverBirthday;
	}

	public int getDriverAge() {
		return driverAge;
	}

	public void setDriverAge(int driverAge) {
		this.driverAge = driverAge;
	}

	public int getDriverScore() {
		return driverScore;
	}

	public void setDriverScore(int driverScore) {
		this.driverScore = driverScore;
	}

	public int getDriverLevel() {
		return driverLevel;
	}

	public void setDriverLevel(int driverLevel) {
		this.driverLevel = driverLevel;
	}

	public int getDriverSginCount() {
		return driverSginCount;
	}

	public void setDriverSginCount(int driverSginCount) {
		this.driverSginCount = driverSginCount;
	}

	public double getDriverLatitude() {
		return driverLatitude;
	}

	public void setDriverLatitude(double driverLatitude) {
		this.driverLatitude = driverLatitude;
	}

	public double getDriverLongitude() {
		return driverLongitude;
	}

	public void setDriverLongitude(double driverLongitude) {
		this.driverLongitude = driverLongitude;
	}

	public long getDriverCreateTime() {
		return driverCreateTime;
	}

	public void setDriverCreateTime(long driverCreateTime) {
		this.driverCreateTime = driverCreateTime;
	}

	public long getDriverLastTime() {
		return driverLastTime;
	}

	public void setDriverLastTime(long driverLastTime) {
		this.driverLastTime = driverLastTime;
	}

	public String getDriverStatus() {
		return driverStatus;
	}

	public void setDriverStatus(String driverStatus) {
		this.driverStatus = driverStatus;
	}

	public String getDriverProvinceCode() {
		return driverProvinceCode;
	}

	public void setDriverProvinceCode(String driverProvinceCode) {
		this.driverProvinceCode = driverProvinceCode;
	}

	public String getDriverProvinceName() {
		return driverProvinceName;
	}

	public void setDriverProvinceName(String driverProvinceName) {
		this.driverProvinceName = driverProvinceName;
	}

	public String getDriverCityCode() {
		return driverCityCode;
	}

	public void setDriverCityCode(String driverCityCode) {
		this.driverCityCode = driverCityCode;
	}

	public String getDriverCityName() {
		return driverCityName;
	}

	public void setDriverCityName(String driverCityName) {
		this.driverCityName = driverCityName;
	}

	public String getDriverAreaCode() {
		return driverAreaCode;
	}

	public void setDriverAreaCode(String driverAreaCode) {
		this.driverAreaCode = driverAreaCode;
	}

	public String getDriverAreaName() {
		return driverAreaName;
	}

	public void setDriverAreaName(String driverAreaName) {
		this.driverAreaName = driverAreaName;
	}

	public String getDriverAddr() {
		return driverAddr;
	}

	public void setDriverAddr(String driverAddr) {
		this.driverAddr = driverAddr;
	}

	public int getUpdatedCount() {
		return updatedCount;
	}

	public void setUpdatedCount(int updatedCount) {
		this.updatedCount = updatedCount;
	}

	public long getTrainingSchoolId() {
		return trainingSchoolId;
	}

	public void setTrainingSchoolId(long trainingSchoolId) {
		this.trainingSchoolId = trainingSchoolId;
	}

	public String getTrainingSchoolName() {
		return trainingSchoolName;
	}

	public void setTrainingSchoolName(String trainingSchoolName) {
		this.trainingSchoolName = trainingSchoolName;
	}

	public long getDiverCarId() {
		return diverCarId;
	}

	public void setDiverCarId(long diverCarId) {
		this.diverCarId = diverCarId;
	}

	public String getDriverCarName() {
		return driverCarName;
	}

	public void setDriverCarName(String driverCarName) {
		this.driverCarName = driverCarName;
	}

	public String getDirverCarNumber() {
		return dirverCarNumber;
	}

	public void setDirverCarNumber(String dirverCarNumber) {
		this.dirverCarNumber = dirverCarNumber;
	}

	public int getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(int authStatus) {
		this.authStatus = authStatus;
	}
	
}
