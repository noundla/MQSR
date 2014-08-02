package com.tgs.mitra.bean;

import java.io.Serializable;

public class MQTicketing implements Serializable{

	private String assignedOwner="";
	
	private String copyToEmail="";
	private String createdDate="";
	private String createdUser="";
	private String department="";
	private String details="";
	private String dueDate="";
	private String guidfield="99999999-9999-9999-9999-999999999999";// This is default .
	private String lastChange="";
	private String lastChangeUser="";
	
	private String priority="";
	private String replyId="";
	private String storeId="";
	private String ticketId="";
	private String ticketStatus="";
	private String title="";
	
	public String getAssignedOwner() {
		return assignedOwner;
	}
	public void setAssignedOwner(Object object) {
		if(object!=null)
		this.assignedOwner = object.toString();
	}
	public String getCopyToEmail() {
		return copyToEmail;
	}
	public void setCopyToEmail(Object object) {
		if(object!=null)
		this.copyToEmail = object.toString();
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Object object) {
		if(object!=null)
		this.createdDate = object.toString();
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(Object createdUser) {
		if(createdUser!=null)
		this.createdUser = createdUser.toString();
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(Object department) {
		if(department!=null)
		this.department = department.toString();
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(Object details) {
		if(details!=null)
		this.details = details.toString();
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(Object dueDate) {
		if(dueDate!=null)
		this.dueDate = dueDate.toString();
	}
	public String getGuidfield() {
		return guidfield;
	}
	public void setGuidfield(Object guidfield) {
		if(guidfield!=null)
		this.guidfield = guidfield.toString();
	}
	public String getLastChange() {
		return lastChange;
	}
	public void setLastChange(Object lastChange) {
		if(lastChange!=null)
		this.lastChange = lastChange.toString();
	}
	public String getLastChangeUser() {
		return lastChangeUser;
	}
	public void setLastChangeUser(Object lastChangeUser) {
		if(lastChangeUser!=null)
		this.lastChangeUser = lastChangeUser.toString();
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(Object priority) {
		if(priority!=null)
		this.priority = priority.toString();
	}
	public String getReplyId() {
		return replyId;
	}
	public void setReplyId(Object replyId) {
		if(replyId!=null)
		this.replyId = replyId.toString();
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(Object storeId) {
		if(storeId!=null)
		this.storeId = storeId.toString();
	}
	public String getTicketId() {
		return ticketId;
	}
	public void setTicketId(Object ticketId) {
		if(ticketId!=null)
		this.ticketId = ticketId.toString();
	}
	public String getTicketStatus() {
		return ticketStatus;
	}
	public void setTicketStatus(Object ticketStatus) {
		if(ticketStatus!=null)
		this.ticketStatus = ticketStatus.toString();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(Object title) {
		if(title!=null)
		this.title = title.toString();
	}
	
}
