/**  
 * @author ningsj@shishike.com
 * @Title: AuthInfoResponse.java 
 * @Package com.internet.http.data.response 
 * @Description: TODO
 * @date 2016-1-11 上午11:08:34 
 * @version V1.0  
 */
package com.internet.http.data.response;

import com.internet.http.data.vo.AuthInfoVO;

/**
 * @Author: ningsj@shishike.com
 * @Date：2016-1-11 上午11:08:34
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All
 *                      rights reserved.
 */
public class AuthInfoResponse extends CommonResponse {
	AuthInfoVO result;
	
	public AuthInfoVO getResult() {
		return result;
	}
	
	public void setResult(AuthInfoVO result) {
		this.result = result;
	}
	
}
