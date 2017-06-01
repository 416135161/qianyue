package com.internet.http.data.vo;

import java.io.Serializable;

public class QuestionSubjectVO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long subjectPkid;

    private Long pid;

    private String name;

   
    public Long getSubjectPkid() {
		return subjectPkid;
	}


	public void setSubjectPkid(Long subjectPkid) {
		this.subjectPkid = subjectPkid;
	}


	public Long getPid() {
		return pid;
	}


	public void setPid(Long pid) {
		this.pid = pid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


}