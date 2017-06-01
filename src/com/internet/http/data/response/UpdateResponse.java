/**  
 * @author ningsj@shishike.com
 * @Title: UpdateResponse.java 
 * @Package com.internet.yousheng.http.data.response 
 * @Description: TODO
 * @date 2015-4-19 下午10:10:44 
 * @version V1.0  
*/
package com.internet.http.data.response;

import com.internet.http.data.vo.UserInfoVO;

/**
 * @Author: ningsj@shishike.com
 * @Date：2015-4-19 下午10:10:44
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All rights reserved.
 */
public class UpdateResponse extends CommonResponse{
	
	UserInfoVO data;

	public UserInfoVO getData() {
		return data;
	}

	public void setData(UserInfoVO data) {
		this.data = data;
	}
	
}
