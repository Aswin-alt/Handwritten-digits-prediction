package com.aswin.model;

public class Customer {

	private int custId;
	private String custName;
	private String custEmail;
	private String custPhone;
	private double totalAmount;
	
	public Customer() {
		
	}
	
	public Customer(String custName, String custEmail, String custPhone, double totalAmount) {

		this.custName = custName;
		this.custEmail = custEmail;
		this.custPhone = custPhone;
		this.totalAmount = totalAmount;
	}
	
	public Customer(int custId, String custName, String custEmail, String custPhone) {

		this.custId = custId;
		this.custName = custName;
		this.custEmail = custEmail;
		this.custPhone = custPhone;
	}
	
	public Customer(int custId, String custName, String custEmail, String custPhone, double totalAmount) {

		this.custId = custId;
		this.custName = custName;
		this.custEmail = custEmail;
		this.custPhone = custPhone;
		this.totalAmount = totalAmount;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getCustPhone() {
		return custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	
	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
