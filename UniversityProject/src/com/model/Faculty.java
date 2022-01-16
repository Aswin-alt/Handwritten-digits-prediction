package com.model;

public class Faculty {
	
	private String name;
	private int deptNo;
	
	
	public Faculty(String name, int deptNo) {
		super();
		this.name = name;
		this.deptNo = deptNo;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}
	
	

}
