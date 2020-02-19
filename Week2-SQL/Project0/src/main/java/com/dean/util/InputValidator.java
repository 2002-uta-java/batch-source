package com.dean.util;

import java.text.DecimalFormat;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class InputValidator {
	private static Scanner sc = new Scanner(System.in);
	private static Logger logger = Logger.getRootLogger();
	
	public static String getAUsername() {
		logger.info("Enter a username between 5-15 characters: ");
		String username = sc.nextLine();
		while(!validUsername(username)) {
			logger.info("Enter a valid username: ");
			username = sc.nextLine();
		}
		return username;
	}
	
	public static boolean validUsername(String username) {
		String regex = "^[a-z0-9_-]{5,15}$";
		System.out.println("hello");
	
		return username.matches(regex);
	}
	
	public static String getAPassword() {
		logger.info("Please enter a valid password. \n- at least 6 characters \n- 1 uppercase letter \n- 1 number \n- 1 special character");
		String password = sc.nextLine();
		
		while(!validPassword(password)) {
			logger.info("Please enter a valid password. \n- at least 6 characters \n- 1 uppercase letter \n- 1 number \n- 1 special character");
			password = sc.nextLine();
		}
		return password;
		
	}
	
	public static boolean validPassword(String password) {
		String regex = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9\\\\\\\\s]).{6,}";
				
		if (password == null) {
			return false;
		}
		
		return password.matches(regex);
	}
	
	public static String formatDecimals(double val) {
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(val);
	}

	
}
