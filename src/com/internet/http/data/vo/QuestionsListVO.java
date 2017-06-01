package com.internet.http.data.vo;

import java.io.Serializable;

import com.internet.basic.AdapterData;


public class QuestionsListVO implements AdapterData, Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long questionsPkid;

    private String questionsGuid;

    private String questionContent;

	public Long getQuestionsPkid() {
		return questionsPkid;
	}

	public void setQuestionsPkid(Long questionsPkid) {
		this.questionsPkid = questionsPkid;
	}

	public String getQuestionsGuid() {
		return questionsGuid;
	}

	public void setQuestionsGuid(String questionsGuid) {
		this.questionsGuid = questionsGuid;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
    
}