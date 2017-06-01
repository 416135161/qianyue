/**  
 * @author ningsj@shishike.com
 * @Title: FindUserResponse.java 
 * @Package com.internet.yousheng.http.data.response 
 * @Description: TODO
 * @date 2015-4-16 下午4:35:19 
 * @version V1.0  
 */
package com.internet.http.data.response;

import com.internet.http.data.vo.UserInfoVO;

/**
 * @Author: ningsj@shishike.com
 * @Date：2015-4-16 下午4:35:19
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All
 *                      rights reserved.
 */
public class FindUserResponse extends CommonResponse {
	
	UserInfoVO result;
	
	public UserInfoVO getResult() {
		return result;
	}
	
	public void setResult(UserInfoVO result) {
		this.result = result;
	}
	
}
