package com.revature.bankingapp.frontend;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.bankingapp.dao.*;
import com.revature.bankingapp.model.*;

/**
 * - use at least one Statement, PreparedStatement, and CallableStatement 
 * - Use the DAO design pattern
 * - Use proper coding conventions 
 * - Ensure that input is thoroughly validated 
 * - Include JUnit tests to test application code
 * 
 * 1) create a user account with a unique email and/or username (must have multiple users)
 * 2) secure my account using a password 
 * 3) log in/log out 
 * 4) create a bank account associated with each user 
 * 5) deposit money 
 * 6) withdraw money (no negative balances!) 
 * 7) view account balance 
 * 
 * Your bank can optionally support: 
 * - Use log4j to replace System.out.println with logging
 * - Multiple user bank accounts (checking & savings) 
 * - Transfer funds functionality between user accounts 
 * - Joint accounts (a single account with two separate users having access) 
 * - Ability to view transaction history Password encryption for added security 
 * - Include mocking and/or an H2 database for proper unit testing
 * 
 * 
 */
public class App 
{
	private static Scanner sc = new Scanner(System.in);
	private static boolean keepGoing = true;
	private static Connection databaseConnection = null;
	
	static boolean begin() {
		System.out.println("Welcome to University of the Wizards Bank!");
		System.out.println("Would you like to Login or Create Account?");
		
		String command = sc.nextLine();
		
		if (command == "Create Account") {
			createAccount();
		}
		
		System.out.print("Username: ");
		String uName = sc.nextLine();
		
		System.out.print("Password: ");
		String password = sc.nextLine();
//		if (uName /* is not correct */ || password /* also not correct */) {
//			System.out.println("Username or Password Incorrect. Try again");
//		}
		
		return true;
	}
	
	static void end() {
		System.out.println("Have a nice day!");
	}
	

	private static void doTransaction() {
		System.out.println("Commands: Quit, Sign out, Transaction");
		System.out.println("What would you like to do? ");
		String command = sc.nextLine();
		
		if (command == "Quit")
			keepGoing = false;
		else if (command == "Sign out")
			begin();
		else if (command == "Transaction")
			createTransaction();
		
	}
	
	private static void createTransaction() {
		
		System.out.print("Amount: ");
		Double amount = sc.nextDouble();
		
		System.out.print("Account From: ");
		int accountFrom = sc.nextInt();
		
		System.out.print("Account To: ");
		int accountTo = sc.nextInt();
		
		Transaction t = new Transaction(amount, accountFrom, accountTo);
		
		// add transaction to database
	}

	static void createAccount() {
		System.out.print("Username: ");
		String uName = sc.nextLine();
		
		System.out.print("Password: ");
		String password = sc.nextLine();
		
		System.out.print("Email: ");
		String email = sc.nextLine();
		
		System.out.print("Phone number: ");
		String phoneNumber = sc.nextLine();
		
		UserAccount creation = new UserAccount(uName, password, email, phoneNumber);
		
		// add user to the database
	}
	
    public static void main( String[] args ) throws SQLException
    {
    	try {
    		
			databaseConnection = ConnectionUtil.getConnection();
			
			if (begin()) {
				while (keepGoing) {
					doTransaction();
				}
			}
			
			end();
			
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
}
