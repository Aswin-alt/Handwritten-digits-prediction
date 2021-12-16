package com.aswin.model;

public enum EnumError {
	
	INVALIDINPUT("Enter a valid INPUT!"),
	NEWCUSTOMER("Customer not available in database(New Customer!)"),
	SQLUSERALREADYEXIST("User already present! Provide differnet data"),
	NOCUSTOMERAVAILABLE("No customer to list!"),
	INPUTNOTENTERED("Enter the inuput to proceed!"),
	UNAUTHENTICATED("UNAUTHENTICATED user! Please enter a valid security code!"),
	DATEFORMAT("Enter proper date!");
	
	String message;
	
	private EnumError(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
}