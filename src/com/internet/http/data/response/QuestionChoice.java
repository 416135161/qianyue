package com.internet.http.data.response;

import java.util.Date;

public class QuestionChoice {
    private Long answerPkid;

    private Long questionsPkid;

    private String mark;

    private String img;

    private String content;

    private Boolean isCorrect;

    private Date createTime;

    private Date updateTime;

    public Long getAnswerPkid() {
        return answerPkid;
    }

    public void setAnswerPkid(Long answerPkid) {
        this.answerPkid = answerPkid;
    }

    public Long getQuestionsPkid() {
        return questionsPkid;
    }

    public void setQuestionsPkid(Long questionsPkid) {
        this.questionsPkid = questionsPkid;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}