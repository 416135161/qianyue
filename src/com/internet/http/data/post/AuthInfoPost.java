/**  
 * @author ningsj@shishike.com
 * @Title: AuthInfoPost.java 
 * @Package com.internet.http.data.post 
 * @Description: TODO
 * @date 2016-1-11 上午11:07:14 
 * @version V1.0  
 */
package com.internet.http.data.post;

/**
 * @Author: ningsj@shishike.com
 * @Date：2016-1-11 上午11:07:14
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All
 *                      rights reserved.
 */
public class AuthInfoPost {
	public String sign; // 签名[必填]
	
	public AuthInfoPost(String sign) {
		super();
		this.sign = sign;
	}
	
	public String getSign() {
		return sign;
	}
	
	public void setSign(String sign) {
		this.sign = sign;
	}
	
}
