/**  
 * @author ningsj@shishike.com
 * @Title: CommonResponse.java 
 * @Package com.internet.yousheng.http.data.response 
 * @Description: TODO
 * @date 2015-4-15 下午3:04:53 
 * @version V1.0  
 */
package com.internet.http.data.response;

/**
 * @Author: ningsj@shishike.com
 * @Date：2015-4-15 下午3:04:53
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All
 *                      rights reserved.
 */
public class CommonResponse {
	public static int CODE_SUCCESS = 1;
	
	int state; // 状态 1：发送成功
	
	String msg; // 状态对应文字描述
	
	int isHasResult; // 是否有数据 有:1、无0：
	
	public CommonResponse() {
		// TODO Auto-generated constructor stub
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getIsHasResult() {
		return isHasResult;
	}

	public void setIsHasResult(int isHasResult) {
		this.isHasResult = isHasResult;
	}
	
	
	
}
