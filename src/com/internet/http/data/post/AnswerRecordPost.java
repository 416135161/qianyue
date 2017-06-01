/**  
 * @author ningsj@shishike.com
 * @Title: AnswerRecordPost.java 
 * @Package com.internet.kayou.http.data.post 
 * @Description: TODO
 * @date 2015-4-26 上午10:38:25 
 * @version V1.0  
 */
package com.internet.http.data.post;

/**
 * @Author: ningsj@shishike.com
 * @Date：2015-4-26 上午10:38:25
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All
 *                      rights reserved.
 */
public class AnswerRecordPost {
	public String mobileNo;// 手机号
	
	public String mess_source; // 消息来源（Ios客户端或Android客户端）
	
	public String questionsPkid; // 试题id
	
	/**
	 * @Constructor
	 * @Description TODO
	 */
	public AnswerRecordPost() {
		// TODO Auto-generated constructor stub
	}
	
	public String getMobileNo() {
		return mobileNo;
	}
	
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public String getMess_source() {
		return mess_source;
	}
	
	public void setMess_source(String mess_source) {
		this.mess_source = mess_source;
	}
	
	public String getQuestionsPkid() {
		return questionsPkid;
	}
	
	public void setQuestionsPkid(String questionsPkid) {
		this.questionsPkid = questionsPkid;
	}
	
}
