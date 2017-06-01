/**  
 * @author ningsj@shishike.com
 * @Title: QuestionSubjectResponse.java 
 * @Package com.internet.yousheng.http.data.response 
 * @Description: TODO
 * @date 2015-4-19 下午4:32:35 
 * @version V1.0  
*/
package com.internet.http.data.response;

import java.util.ArrayList;

import com.internet.http.data.vo.QuestionSubjectVO;

/**
 * @Author: ningsj@shishike.com
 * @Date：2015-4-19 下午4:32:35
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All rights reserved.
 */
public class QuestionSubjectResponse extends CommonResponse{
	public ArrayList<QuestionSubjectVO> data;
	
	
	public QuestionSubjectResponse() {
		// TODO Auto-generated constructor stub
	}


	public ArrayList<QuestionSubjectVO> getData() {
		return data;
	}


	public void setData(ArrayList<QuestionSubjectVO> data) {
		this.data = data;
	}
	
	
}
