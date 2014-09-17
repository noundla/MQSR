package com.tgs.mitra.bean;

import java.io.Serializable;

/**
 * Department object data.
 * @author TGS
 *
 */
public class Department implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean allowTicketing=false;
	
	private String department="";
	
	private String guidfield="";
	
	private String lastChange="";
	
	private String lastChangeUser="";
	
	private String mobileImage="";

	public boolean isAllowTicketing() {
		return allowTicketing;
	}

	public void setAllowTicketing(boolean allowTicketing) {
		this.allowTicketing = allowTicketing;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getGuidfield() {
		return guidfield;
	}

	public void setGuidfield(String guidfield) {
		this.guidfield = guidfield;
	}

	public String getLastChange() {
		return lastChange;
	}

	public void setLastChange(String lastChange) {
		this.lastChange = lastChange;
	}

	public String getLastChangeUser() {
		return lastChangeUser;
	}

	public void setLastChangeUser(String lastChangeUser) {
		this.lastChangeUser = lastChangeUser;
	}

	public String getMobileImage() {
		return mobileImage;
	}

	public void setMobileImage(String mobileImage) {
		this.mobileImage = mobileImage;
	}
	 

}
