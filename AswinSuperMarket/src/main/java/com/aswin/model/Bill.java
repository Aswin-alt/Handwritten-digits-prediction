package com.aswin.model;

public class Bill {

	private int billId;
	private String dateOfPurchase;
	private int custId;
	private double totalAmount;
	private int paymentModeId;
	private int repId;
	private double discount;
	private double netTotalAmount;
	private String repName;
	
	
	public Bill(int billId, String dateOfPurchase, int custId, double totalAmount, int paymentModeId, int repId,
			double discount, double netTotalAmount) {
		this.billId = billId;
		this.dateOfPurchase = dateOfPurchase;
		this.custId = custId;
		this.totalAmount = totalAmount;
		this.paymentModeId = paymentModeId;
		this.repId = repId;
		this.discount = discount;
		this.netTotalAmount = netTotalAmount;
	}
	
	public Bill(String dateOfPurchase, int custId, double totalAmount, int paymentModeId, int repId,
			double discount, double netTotalAmount) {
		this.dateOfPurchase = dateOfPurchase;
		this.custId = custId;
		this.totalAmount = totalAmount;
		this.paymentModeId = paymentModeId;
		this.repId = repId;
		this.discount = discount;
		this.netTotalAmount = netTotalAmount;
	}
	

	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}

	public String getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(String dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getPaymentModeId() {
		return paymentModeId;
	}
	public void setPaymentModeId(int paymentModeId) {
		this.paymentModeId = paymentModeId;
	}

	public int getRepId() {
		return repId;
	}
	public void setRepId(int repId) {
		this.repId = repId;
	}

	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getTotalNetAmount() {
		return netTotalAmount;
	}
	public void setTotalNetAmount(double netTotalAmount) {
		this.netTotalAmount = netTotalAmount;
	}

	public String getRepName() {
		return repName;
	}

	public void setRepName(String repName) {
		this.repName = repName;
	}
	
	
}
