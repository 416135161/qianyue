/**  
 * @author ningsj@shishike.com
 * @Title: EditSelfInfoPost.java 
 * @Package com.internet.http.data.post 
 * @Description: TODO
 * @date 2016-1-7 下午2:47:24 
 * @version V1.0  
 */
package com.internet.http.data.post;

import java.io.File;

/**
 * @Author: ningsj@shishike.com
 * @Date：2016-1-7 下午2:47:24
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All rights reserved.
 */
public class EditSelfInfoPost {
	// 签名[必填]
	public String sign;

	// 教练id
	public String driverId;

	// 姓名
	public String driverName;

	// 性别

	public String driverSex;

	// 头像
	public File file;

	// 生日
	public String driverBirthday;

	// 教龄
	public String driverTeachAge;

	// 省Code
	public String driverProvinceCode;

	// 省名称
	public String driverProvinceName;

	// 市Code
	public String driverCityCode;

	// 市名称
	public String driverCityName;

	// 区Code
	public String driverAreaCode;

	// 区名称
	public String driverAreaName;

	// 地址
	public String driverAddr;

	// 所属驾校
	public String trainingSchoolName;

	public String driverLatitude;

	public String driverLongitude;

	public String lastLoginDevice = "1";// 登录设备1:android,2:ios,3:all

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
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

	public String getDriverSex() {
		return driverSex;
	}

	public void setDriverSex(String driverSex) {
		this.driverSex = driverSex;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getDriverBirthday() {
		return driverBirthday;
	}

	public void setDriverBirthday(String driverBirthday) {
		this.driverBirthday = driverBirthday;
	}

	public String getDriverTeachAge() {
		return driverTeachAge;
	}

	public void setDriverTeachAge(String driverTeachAge) {
		this.driverTeachAge = driverTeachAge;
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

	public String getTrainingSchoolName() {
		return trainingSchoolName;
	}

	public void setTrainingSchoolName(String trainingSchoolName) {
		this.trainingSchoolName = trainingSchoolName;
	}

}
