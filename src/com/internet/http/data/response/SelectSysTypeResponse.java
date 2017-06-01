/**  
 * @author ningsj@shishike.com
 * @Title: SelectSysTypeResponse.java 
 * @Package com.internet.http.data.response 
 * @Description: TODO
 * @date 2016-1-11 上午10:51:47 
 * @version V1.0  
 */
package com.internet.http.data.response;

import java.util.ArrayList;

import com.internet.http.data.vo.SelectSysTypeVO;

/**
 * @Author: ningsj@shishike.com
 * @Date：2016-1-11 上午10:51:47
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All
 *                      rights reserved.
 */
public class SelectSysTypeResponse extends CommonResponse {
	ArrayList<SelectSysTypeVO> result;
	
	public ArrayList<SelectSysTypeVO> getResult() {
		return result;
	}
	
	public void setResult(ArrayList<SelectSysTypeVO> result) {
		this.result = result;
	}
	
}
