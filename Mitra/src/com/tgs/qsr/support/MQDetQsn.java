package com.tgs.qsr.support;

import java.io.Serializable;

public class MQDetQsn implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	private String departmentId="";
	private String questionId="";
	private String questionTitle="";
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	 

}
