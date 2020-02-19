package com.revature.daos;

import com.revature.daos.UserDao;
import com.revature.daos.UserDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.revature.util.ConnectionUtil;

public class Driver {
	
	public static void main(String[] args) {
		      // Test the Connection to Database
		try {
			Connection c = ConnectionUtil.getConnection();
			String driverName = c.getMetaData().getDriverName();
			System.out.println(driverName); 
		} catch (SQLException e) {
			e.printStackTrace();
		}showSwitchMenu();
	}

	private static void showSwitchMenu() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		char option = '0';
		Scanner scanner = new Scanner(System.in);
		
		do { 
		System.out.println(dateFormat.format(date)); 
		System.out.println("Welcome to the A1 Trust!\n");
        System.out.println("a. Log into an account");
		System.out.println("b. Create a new account");
		System.out.println("c. Quick deposit into an account");
		System.out.println("d. Exit\n");
		System.out.println("Please enter an option: ");

		option = scanner.next().charAt(0);
		System.out.println("");

		switch(option) {
			case 'a':
			try {
				UserDao userDao = new UserDaoImpl();
				userDao.login();
			} catch (SQLException e) {
				System.out.println("SQLException encounter at login \nStack trace: " + e);
			}
			break;
			case 'b':
				try {
					UserDao userDao = new UserDaoImpl();
					userDao.createUser();
				} catch (SQLException e) {
					System.out.println("Account successfully created.");
				}
				break;
			case 'c':
				try {
					UserDao userDao = new UserDaoImpl();
					userDao.deposit();
					//System.out.println("Deposit was succesful.");
				} catch (SQLException e) {
					System.out.println("SQLException encounter at login\nStack trace: " + e);
				}
				break;
			case 'd':
				break;
			default:
				System.out.println("Not a valid option. Try again.");
				break; 
			} 
		} while (option != 'd');
		System.out.println("Thank you for using the A1 Trust. Have a nice day!");
		scanner.close();
	}
}