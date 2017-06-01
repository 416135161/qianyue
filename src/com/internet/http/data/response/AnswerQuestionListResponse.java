/**  
 * @author ningsj@shishike.com
 * @Title: QuestionListResponse.java 
 * @Package com.internet.yousheng.http.data.response 
 * @Description: TODO
 * @date 2015-4-20 下午8:34:37 
 * @version V1.0  
*/
package com.internet.http.data.response;

import java.util.ArrayList;

import com.internet.http.data.vo.AnswerQuestionVO;

/**
 * @Author: ningsj@shishike.com
 * @Date：2015-4-20 下午8:34:37
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All rights reserved.
 */
public class AnswerQuestionListResponse  extends CommonResponse{
	ArrayList<AnswerQuestionVO> data;
	
	/**
	 * @Constructor
	 * @Description TODO
	 */
	public AnswerQuestionListResponse() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<AnswerQuestionVO> getData() {
		return data;
	}

	public void setData(ArrayList<AnswerQuestionVO> data) {
		this.data = data;
	}
	
	
}
