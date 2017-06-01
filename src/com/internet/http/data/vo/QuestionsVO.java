package com.internet.http.data.vo;

import java.io.Serializable;
import java.util.List;

import com.internet.http.data.response.QuestionChoice;

public class QuestionsVO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long questionsPkid;

    private String questionsGuid;
    
    private int typeId;
    
    private String typeName;

    private String questionContent; //题目

    private String questionAnalyse;  //思路

    private String answerDetails;  //解析

    private List<QuestionChoice> questionChoices; //选项列表
    private Integer answerSource; //来源

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

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public String getQuestionAnalyse() {
		return questionAnalyse;
	}

	public void setQuestionAnalyse(String questionAnalyse) {
		this.questionAnalyse = questionAnalyse;
	}

	public String getAnswerDetails() {
		return answerDetails;
	}

	public void setAnswerDetails(String answerDetails) {
		this.answerDetails = answerDetails;
	}

	public List<QuestionChoice> getQuestionChoices() {
		return questionChoices;
	}

	public void setQuestionChoices(List<QuestionChoice> questionChoices) {
		this.questionChoices = questionChoices;
	}
    
	public Integer getAnswerSource() {
		return answerSource;
	}

	public void setAnswerSource(Integer answerSource) {
		this.answerSource = answerSource;
	}

 
}