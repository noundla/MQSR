package com.tgs.mitra.util;

import java.io.Serializable;

/**
 * This calls is for MyTickets.
 */
public class MQTickets implements Serializable{

	private String DepartmentId="";
	
	private String DepartmentName="";
	private String LastModified="";
	private String LastModifiedBy="";
	private String Status="";
	private String TicketDescription="";
	private String TicketId="";
	private String TicketTitle="";
	private boolean hasReplay=false;
	
	private int replayCount=0;
	
	public String getDepartmentId() {
		return DepartmentId;
	}
	public void setDepartmentId(String departmentId) {
		DepartmentId = departmentId;
	}
	public String getDepartmentName() {
		return DepartmentName;
	}
	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}
	public String getLastModified() {
		return LastModified;
	}
	public void setLastModified(String lastModified) {
		LastModified = lastModified;
	}
	public String getLastModifiedBy() {
		return LastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		LastModifiedBy = lastModifiedBy;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getTicketDescription() {
		return TicketDescription;
	}
	public void setTicketDescription(String ticketDescription) {
		TicketDescription = ticketDescription;
	}
	public String getTicketId() {
		return TicketId;
	}
	public void setTicketId(String ticketId) {
		TicketId = ticketId;
	}
	public String getTicketTitle() {
		return TicketTitle;
	}
	public void setTicketTitle(String ticketTitle) {
		TicketTitle = ticketTitle;
	}
	public boolean isHasReplay() {
		return hasReplay;
	}
	public void setHasReplay(boolean hasReplay) {
		this.hasReplay = hasReplay;
	}
	public int getReplayCount() {
		return replayCount;
	}
	public void setReplayCount(int replayCount) {
		this.replayCount = replayCount;
	}
}
