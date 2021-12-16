package com.aswin.model;

public class User {

	private String username;
	private int visit = 1;
	private int userId;


	// private static AtomicInteger ID_Gen = new AtomicInteger(1000);

	public User(String username) {
		this.username = username;
	}
	
	public User(int userId, String username, int visit) {
		this.username = username;
		this.userId = userId;
		this.visit = visit;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUserId() {
		return userId;
	}

	public int getVisit() {
		return visit;
	}
	public void setVisit(int visit) {
		this.visit = visit;
	}
	
	public static String getBaseURL() {
		String URL = "/AswinRestApiSQL/usercontroller/v1/users";
		
		return URL;
	}

}
