/**  
 * @author ningsj@shishike.com
 * @Title: CityResponse.java 
 * @Package com.internet.yousheng.http.data.response 
 * @Description: TODO
 * @date 2015-4-26 上午9:02:35 
 * @version V1.0  
*/
package com.internet.http.data.response;

import java.util.ArrayList;

import com.internet.http.data.vo.CityVO;

/**
 * @Author: ningsj@shishike.com
 * @Date：2015-4-26 上午9:02:35
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All rights reserved.
 */
public class CityResponse extends CommonResponse{
	
	ArrayList<CityVO> data;
	
	/**
	 * @Constructor
	 * @Description TODO
	 */
	public CityResponse() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<CityVO> getData() {
		return data;
	}

	public void setData(ArrayList<CityVO> data) {
		this.data = data;
	}
	
}
