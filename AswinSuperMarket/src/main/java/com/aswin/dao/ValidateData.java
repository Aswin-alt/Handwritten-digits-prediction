package com.aswin.dao;

import java.util.regex.Pattern;

public class ValidateData {
	
	public static boolean validatePhone(String phone) {
		
		String pattern = "[6-9]{1}[0-9]{9}";
		boolean validate = Pattern.matches(pattern, phone);
		
		return validate;
	}
	
	public static boolean validateEmail(String email) {
		
		String pattern = "[A-Za-z0-9]+[@][a-z]+[.][a-z]{2,6}";
		boolean validate = Pattern.matches(pattern, email);
		
		return validate;
	}

}
