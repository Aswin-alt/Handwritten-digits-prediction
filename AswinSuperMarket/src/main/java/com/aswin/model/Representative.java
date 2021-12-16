package com.aswin.model;

public class Representative {

	private int repId;
	private String repName;
	private String doj;
	private int age;
	private double salary;
	private int desigId;
	private String desigName;
	private double totalAmount;
	
	public Representative() {
		
	}
	
	public Representative(int repId, String repName, String doj, int age, double salary, int desigId) {
		super();
		this.repId = repId;
		this.repName = repName;
		this.doj = doj;
		this.age = age;
		this.salary = salary;
		this.desigId = desigId;
	}
	
	public Representative(int repId, String repName, String doj, int age, double salary, int desigId, String designName) {
		super();
		this.repId = repId;
		this.repName = repName;
		this.doj = doj;
		this.age = age;
		this.salary = salary;
		this.desigId = desigId;
		this.desigName = designName;
	}
	
	public Representative(int repId, String repName) {
		this.repId = repId;
		this.repName = repName;
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
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public int getDesigId() {
		return desigId;
	}
	public void setDesigId(int desigId) {
		this.desigId = desigId;
	}
	public String getDesigName() {
		return desigName;
	}
	public void setDesigId(String desigName) {
		this.desigName = desigName;
	}
	public double getToalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
