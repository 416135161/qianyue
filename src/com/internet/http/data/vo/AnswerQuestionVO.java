package com.internet.http.data.vo;

import java.io.Serializable;

import com.internet.basic.AdapterData;


public class AnswerQuestionVO  implements AdapterData, Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long aqId;

    private Long studentId;

    private Long messTypeId;

    private String messType;

    private String messContent;

    private String thinking;

    private String answer;

	public Long getAqId() {
		return aqId;
	}

	public void setAqId(Long aqId) {
		this.aqId = aqId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getMessTypeId() {
		return messTypeId;
	}

	public void setMessTypeId(Long messTypeId) {
		this.messTypeId = messTypeId;
	}

	public String getMessType() {
		return messType;
	}

	public void setMessType(String messType) {
		this.messType = messType;
	}

	public String getMessContent() {
		return messContent;
	}

	public void setMessContent(String messContent) {
		this.messContent = messContent;
	}

	public String getThinking() {
		return thinking;
	}

	public void setThinking(String thinking) {
		this.thinking = thinking;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
}