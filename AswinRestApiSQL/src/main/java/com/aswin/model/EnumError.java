package com.aswin.model;

public enum EnumError {
	
	USERALREADYEXISTS(1308, "User Already Exists"),
	USERNAMENOTENTERED(1306,"Username not entered!"),
	ERRORURL(400, "Enter a valid URI(API not found)!"),
	ERRJSON(1600, "JSON Format not valid!"),
	USERNAMESAME(1616,"Username already same!"),
	SQLUSERNOTFOUND(404, "User does not Exist!"),
	ERRORSQL(1612, "SQL Exception"),
	ERROROFFSET(1620, "Enter proper offset value!");
	
	int errCode;
	String message;
	
	private EnumError(int errCode, String message) {
		this.errCode = errCode;
		this.message = message;
	}
	
	public int getErrCode() {
		return errCode;
	}
	
	public String getMessage() {
		return message;
	}
	
}
