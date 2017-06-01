/**  
 * @author ningsj@shishike.com
 * @Title: AreaResponse.java 
 * @Package com.internet.http.data.response 
 * @Description: TODO
 * @date 2016-1-8 下午5:54:42 
 * @version V1.0  
 */
package com.internet.http.data.response;

import java.util.ArrayList;

import com.internet.http.data.vo.AreaVO;

/**
 * @Author: ningsj@shishike.com
 * @Date：2016-1-8 下午5:54:42
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All rights reserved.
 */
public class AreaResponse extends CommonResponse {
	ArrayList<AreaVO> result;

	public AreaResponse() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<AreaVO> getResult() {
		return result;
	}

	public void setResult(ArrayList<AreaVO> result) {
		this.result = result;
	}

}
