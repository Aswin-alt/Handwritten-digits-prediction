package com.model;

public class Department {

	private int deptNo;
	private String deptName;
	private int deptHeadCount;
	
	public Department(String deptName) {
		super();
		this.deptName = deptName;
	}
	
	
	public int getDeptNo() {
		return deptNo;
	}
	
	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}
	
	
	public String getDeptName() {
		return deptName;
	}
	
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	
	public int getDeptHeadCount() {
		return deptHeadCount;
	}
	
	public void setDeptHeadCount(int deptHeadCount) {
		this.deptHeadCount = deptHeadCount;
	}
	
	
}
