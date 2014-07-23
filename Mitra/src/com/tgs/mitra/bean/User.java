package com.tgs.mitra.bean;

public class User {

	private String user="";
	private String password="";
	
	static User userObj=null;
	public static User getInstance()
	{
		if(userObj==null)
		{
			userObj=new User();
		}
		return userObj;
		
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
