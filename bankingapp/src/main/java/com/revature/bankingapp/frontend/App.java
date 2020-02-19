package com.revature.bankingapp.frontend;

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
public class App {
	private static Scanner strsc = new Scanner(System.in);
	private static boolean keepGoing = true;
	private static UserAccountDaoImpl uadao = new UserAccountDaoImpl();
	private static UserAccount user = null;
	private static Options options = new Options();
	
	static boolean begin() {
		System.out.println("Welcome to University of the Wizards Bank!");
		System.out.println("Would you like to Login or Create Account?");
		
		String command = strsc.nextLine();
		
		if (command.equals("Create Account")) {
			options.createAccount();
		}
		
		System.out.print("Username: ");
		String uName = strsc.nextLine();
		
		System.out.print("Password: ");
		String password = strsc.nextLine();
		
		options.displayBankAccounts();
		
		user = uadao.getUserAccountByUsername(uName);
		
		if (user.getPassword().equals(password))
			return true;
		
		return false;
	}
	
	static void end() {
		System.out.println("Have a nice day!");
	}
	

	private static void doTransaction() {
		System.out.println("Commands: Quit, Sign out, Transaction, Create BankAccount, Display BankAccounts, Balance, Deposit, Withdraw");
		System.out.println("What would you like to do? ");
		String command = strsc.nextLine();
		
		if (command.equals("Quit"))
			keepGoing = false;
		else if (command.equals("Sign out"))
			begin();
		else if (command.equals("Transaction"))
			options.createTransaction();
		else if (command.equals("Create BankAccount"))
			options.createBankAccount();
		else if (command.equals("Display BankAccounts"))
			options.displayBankAccounts();
		else if (command.equals("Balance"))
			options.displayBalance();
		else if (command.equals("Deposit"))
			options.depositMoney();
		else if (command.equals("Withdraw"))
			options.withdrawMoney();
		
	}

    public static void main( String[] args ) throws SQLException
    {
		if (begin())
			while (keepGoing)
				doTransaction();
		end();
    }
}
