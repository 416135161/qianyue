/**  
 * @author ningsj@shishike.com
 * @Title: DeepSearchPost.java 
 * @Package com.internet.kayou.http.data.post 
 * @Description: TODO
 * @date 2015-4-20 下午9:35:44 
 * @version V1.0  
 */
package com.internet.http.data.post;

/**
 * @Author: ningsj@shishike.com
 * @Date：2015-4-20 下午9:35:44
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All
 *                      rights reserved.
 */
public class DeepSearchPost {
	public String mobileNo;// 手机号
	
	public String gradeId;// 年级
	
	public String subjectId;// 学科
	
	public String mess_source;// 消息来源（Ios客户端或Android客户端）
	
	public String type;// 类型 （1,2,3分别对应图片、语言、文字）
	
	public String content;// 内容(type=2或type=3时必填，type=1时不 填)
	
	// CommonsMultipartFile picContent;
	// 图片(type=1时必填，type!=1时不填)
	
	/**
	 * @Constructor
	 * @Description TODO
	 */
	public DeepSearchPost() {
		// TODO Auto-generated constructor stub
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectid(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getMess_source() {
		return mess_source;
	}

	public void setMess_source(String mess_source) {
		this.mess_source = mess_source;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
}
