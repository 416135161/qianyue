package com.internet.http.data.response;

import java.util.ArrayList;

import com.internet.http.data.vo.StudentVO;

public class SearchStudentResponse extends CommonResponse{
	Result result;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public static final class Result {
		ArrayList<StudentVO> result;

		public ArrayList<StudentVO> getResult() {
			return result;
		}

		public void setResult(ArrayList<StudentVO> result) {
			this.result = result;
		}

	}
}
