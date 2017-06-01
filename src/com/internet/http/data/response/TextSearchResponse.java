/**  
 * @author ningsj@shishike.com
 * @Title: TextSearchResponse.java 
 * @Package com.internet.yousheng.http.data.response 
 * @Description: TODO
 * @date 2015-4-16 下午6:18:51 
 * @version V1.0  
 */
package com.internet.http.data.response;

import java.util.ArrayList;

import com.internet.http.data.vo.QuestionsListVO;

/**
 * @Author: ningsj@shishike.com
 * @Date：2015-4-16 下午6:18:51
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All
 *                      rights reserved.
 */
public class TextSearchResponse extends CommonResponse {
	ArrayList<QuestionsListVO> data;
	
	public TextSearchResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<QuestionsListVO> getData() {
		return data;
	}
	
	public void setData(ArrayList<QuestionsListVO> data) {
		this.data = data;
	}
	
}
