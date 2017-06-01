package com.internet.http.data.vo;

import java.io.Serializable;

import com.internet.basic.AdapterData;

public class StudentVO implements AdapterData, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	long createTime;
	String driverId;
	String driverName;
	String driverStudentId;
	String remarks;
	int studentId; //这个值大于0，表示已激活
	String studentMobile;
	String studentName;
	int type;

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
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

	public String getDriverStudentId() {
		return driverStudentId;
	}

	public void setDriverStudentId(String driverStudentId) {
		this.driverStudentId = driverStudentId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentMobile() {
		return studentMobile;
	}

	public void setStudentMobile(String studentMobile) {
		this.studentMobile = studentMobile;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
