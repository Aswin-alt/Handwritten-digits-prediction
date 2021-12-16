package com.aswin.model;

public enum EnumSuccess {
	
	SUCCESSFULLYADDED("User Added Successfully!"),
	SUCCESSCOUNTINCREMENTED("Visit Count Incremented"),
	SUCCESSFULLYUPDATED("Username Updated Successfully!"),
	SUCCESSFULLYDELETED("User Deleted Successfully"),
	SUCCESSCOUNTDECREMENTED("Visit Count Decremented!"),
	SUCCESSFULLYFETCHED("Successfully Fetched!");
	
	String message;
	
	private EnumSuccess(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
}