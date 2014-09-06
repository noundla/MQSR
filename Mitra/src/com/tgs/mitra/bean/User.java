package com.tgs.mitra.bean;

import java.util.ArrayList;

public class User {

	private String user="";
	private String password="";
	
	private String storeName="";
	
	static User userObj=null;
	private ArrayList<String> storeList=null; 
	
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

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public ArrayList<String> getStoreList() {
		return storeList;
	}

	public void setStoreList(ArrayList<String> storeList) {
		this.storeList = storeList;
	}
	
}
