package com.tgs.mitra.util;

/**
 * This calls is for MyTickets.
 *
 */
public class MQTickets {

	private String DepartmentId="";
	
	private String DepartmentName="";
	private String LastModified="";
	private String LastModifiedBy="";
	private String Status="";
	private String TicketDescription="";
	private String TicketId="";
	private String TicketTitle="";
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
}
