/**  
 * @author ningsj@shishike.com
 * @Title: ProvinceResponse.java 
 * @Package com.internet.yousheng.http.data.response 
 * @Description: TODO
 * @date 2015-4-26 上午9:01:03 
 * @version V1.0  
*/
package com.internet.http.data.response;

import java.util.ArrayList;

import com.internet.http.data.vo.ProvinceVO;

/**
 * @Author: ningsj@shishike.com
 * @Date：2015-4-26 上午9:01:03
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All rights reserved.
 */
public class ProvinceResponse extends CommonResponse{
	
	ArrayList<ProvinceVO> data;
	
	/**
	 * @Constructor
	 * @Description TODO
	 */
	public ProvinceResponse() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<ProvinceVO> getData() {
		return data;
	}

	public void setData(ArrayList<ProvinceVO> data) {
		this.data = data;
	}
	
	
}
