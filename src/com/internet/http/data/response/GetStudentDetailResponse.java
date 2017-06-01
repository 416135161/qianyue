package com.internet.http.data.response;

import com.internet.http.data.vo.StudentDetailVO;

public class GetStudentDetailResponse extends CommonResponse {
	StudentDetailVO result;

	public StudentDetailVO getResult() {
		return result;
	}

	public void setResult(StudentDetailVO result) {
		this.result = result;
	}

}
