package com.model;

import java.util.ArrayList;

import com.main.IDGenerator;

public class University {
	
	private String name;
	private ArrayList<Department> department = new ArrayList<Department>();
	private ArrayList<Faculty> faculty = new ArrayList<Faculty>();
	
	
	public University(String name) {
		
		this.name = name;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public ArrayList<Department> getDepartment() {
		return department;
	}

	public void setDepartment(ArrayList<Department> department) {
		this.department = department;
	}


	public ArrayList<Faculty> getFaculty() {
		return faculty;
	}

	public void setFaculty(ArrayList<Faculty> faculty) {
		this.faculty = faculty;
	}
	
	
	public int addDepartment(String deptName) {
		
		Department d = new Department(deptName);
		d.setDeptHeadCount(0);
		int deptNo = IDGenerator.getId();
		d.setDeptNo(deptNo);
		department.add(d);
		return department.size();
	}
	
	public int addFaculty(Faculty faculty) {
		int flag = 0;
		int headCount = 0;
		for(Department d : department) {
			if(d.getDeptNo() == faculty.getDeptNo()) {
				flag = 1;
				d.setDeptHeadCount(d.getDeptHeadCount()+1);
				headCount = d.getDeptHeadCount();
			}
		}
		
		if(flag == 1) {
			this.faculty.add(faculty);
			return headCount;
		}
		else {
			System.out.println("No such Department Exist");
			return -1;
		}
		
	}
	
	public int shiftFacultyToNewDepartment(String name, int deptNo) {
		
		int flag1 = 0;
		int flag2 = 0;
		int headCount = 0;
		for(Faculty f : faculty) {
			if(f.getName().equals(name)) {
				flag1 = 1;
				for(Department d : department) {
					if(deptNo == d.getDeptNo()) {
						flag2 = 1;
						for(Department d1 : department) {
							if(f.getDeptNo() == d1.getDeptNo()) {
								d1.setDeptHeadCount(d1.getDeptHeadCount()-1);
							}
						}
						d.setDeptHeadCount(d.getDeptHeadCount()+1);
						headCount = d.getDeptHeadCount();
						f.setDeptNo(deptNo);
					}
				}
				
			}
		}
		
		if(flag1 == 0) {
			System.out.println("No facult exist!");
			return -1;
		}
		
		else if(flag1 == 0) {
			System.out.println("No Department exist!");
			return -2;
		}
		else {
			return headCount;
		}
	}
	
	public int deleteFaculty(String name) {
		
		int flag = 0;
		int headCount = 0;
		for(Faculty f : faculty) {
			if(f.getName().equals(name)) {
				flag = 1;
				for(Department d : department) {
					if(f.getDeptNo() == d.getDeptNo()) {
						d.setDeptHeadCount(d.getDeptHeadCount()-1);
						headCount = d.getDeptHeadCount();
					}
				}
				faculty.remove(f);
			}
		}
		
		if(flag == 1) {
			return headCount;
		}
		else {
			System.out.println("Faculty does not exist!");
			return -1;
		}
	}
	
}
