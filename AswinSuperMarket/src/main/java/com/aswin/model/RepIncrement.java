package com.aswin.model;

public class RepIncrement {
	
	private int repId;
	private String repName;
	private int desigId;
	private String doj;
	private int age;
	private int totalNoOfSales;
	private double totalSalesAmount;
	private double currentSalary;
	private double incrementPercent;
	private double additionalIncrementedPercent;
	private double incrementedSalary;
	

	public RepIncrement(int repId, String repName, int desigId, String doj, int age, int totalNoOfSales, double totalSalesAmount,
			double currentSalary) {
		super();
		this.repId = repId;
		this.repName = repName;
		this.desigId = desigId;
		this.doj = doj;
		this.age = age;
		this.totalNoOfSales = totalNoOfSales;
		this.totalSalesAmount = totalSalesAmount;
		this.currentSalary = currentSalary;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getRepId() {
		return repId;
	}

	public void setRepId(int repId) {
		this.repId = repId;
	}

	public String getRepName() {
		return repName;
	}

	public void setRepName(String repName) {
		this.repName = repName;
	}

	public int getDesigId() {
		return desigId;
	}

	public void setDesigId(int desigId) {
		this.desigId = desigId;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public int getTotalNoOfSales() {
		return totalNoOfSales;
	}

	public void setTotalNoOfSales(int totalNoOfSales) {
		this.totalNoOfSales = totalNoOfSales;
	}

	public double getTotalSalesAmount() {
		return totalSalesAmount;
	}

	public void setTotalSalesAmount(double totalSalesAmount) {
		this.totalSalesAmount = totalSalesAmount;
	}

	public double getCurrentSalary() {
		return currentSalary;
	}

	public void setCurrentSalary(double currentSalary) {
		this.currentSalary = currentSalary;
	}

	public double getIncrementPercent() {
		return incrementPercent;
	}

	public void setIncrementPercent(double incrementPercent) {
		this.incrementPercent = incrementPercent;
	}

	public double getIncrementedSalary() {
		return incrementedSalary;
	}

	public void setIncrementedSalary(double incrementedSalary) {
		this.incrementedSalary = incrementedSalary;
	}
	
	public double getAdditionalIncrementedPercent() {
		return additionalIncrementedPercent;
	}

	public void setAdditionalIncrementedPercent(double additionalIncrementedPercent) {
		this.additionalIncrementedPercent = additionalIncrementedPercent;
	}
	
}
