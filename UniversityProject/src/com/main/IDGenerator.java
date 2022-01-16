package com.main;

public class IDGenerator {

	public static int id = 0;
	
	public static int getId() {
		id++;
		return id;
	}
	
}
