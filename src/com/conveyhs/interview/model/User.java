package com.conveyhs.interview.model;

import java.util.Date;

public class User {
	private int id; /** For id and userId good choice of using "long"
	 and Data is legacy, if we using Java8+ the better to use LocalData*/
	private String userName;
	private String email;
	private Date created;
	
	
	
	public User(int id, String userName, String email, Date created) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.created = created;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}

}
