package com.internet.http.data.post;

public class EditStudentPost {
	public String sign;// 签名[必填]

	public String driverStudentId;

	public String studentName;// 学员姓名

	public String studentMobile;// 手机号

	public String type = "1";// 是否收费：1:是；2：否

	public String remark;

	public EditStudentPost(String sign, String studentName, String studentMobile) {
		super();
		this.sign = sign;
		this.studentName = studentName;
		this.studentMobile = studentMobile;
	}
}
