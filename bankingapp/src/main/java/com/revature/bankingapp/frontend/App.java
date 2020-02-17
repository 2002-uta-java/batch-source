package com.revature.bankingapp.frontend;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
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
	private static Scanner intsc = new Scanner(System.in);
	private static Scanner strsc = new Scanner(System.in);
	private static boolean keepGoing = true;
	private static Connection databaseConnection = null;
	private static BankAccountDaoImpl badao = new BankAccountDaoImpl();
	private static TransactionDaoImpl tdao  = new TransactionDaoImpl();
	private static UserAccountDaoImpl uadao = new UserAccountDaoImpl();
	
	static boolean begin() {
		System.out.println("Welcome to University of the Wizards Bank!");
		System.out.println("Would you like to Login or Create Account?");
		
		String command = strsc.nextLine();
		
		if (command.equals("Create Account")) {
			createAccount();
		}
		
		System.out.print("Username: ");
		String uName = strsc.nextLine();
		
		System.out.print("Password: ");
		String password = strsc.nextLine();
		if (uadao.getUserAccountByUsername(uName) || uadao.getUserAccountByPassword(password))
			return true;
		
		return false;
	}
	
	static void end() {
		System.out.println("Have a nice day!");
	}
	

	private static void doTransaction() {
		System.out.println("Commands: Quit, Sign out, Transaction");
		System.out.println("What would you like to do? ");
		String command = strsc.nextLine();
		
		if (command.equals("Quit"))
			keepGoing = false;
		else if (command.equals("Sign out"))
			begin();
		else if (command.equals("Transaction"))
			createTransaction();
		
	}
	
	private static void createTransaction() {

		List<Transaction> transactions = tdao.getTransaction();
		int transactionId = transactions.size();
		
		System.out.print("Amount: ");
		Double amount = intsc.nextDouble();
		
		System.out.print("Account From: ");
		int accountFrom = intsc.nextInt();
		
		System.out.print("Account To: ");
		int accountTo = intsc.nextInt();
		
		Transaction t = new Transaction(transactionId, amount, accountFrom, accountTo);
		
		// add transaction to database
		
		tdao.createTransaction(t);
	}
	
	static void createBankAccount() {
		
	}

	static void createAccount() {
		
		List<UserAccount> userAccounts = uadao.getUserAccount();
		int uaId = 0;
		int baId = 0;
		if (userAccounts.size() != 0)
			uaId = userAccounts.size();
			
		List<BankAccount> bankAccounts = badao.getBankAccounts();
		if (bankAccounts.size() != 0)
			baId = bankAccounts.size();
		
		System.out.print("Username: ");
		String uName = strsc.nextLine();
		
		System.out.print("Password: ");
		String password = strsc.nextLine();
		
		System.out.print("Email: ");
		String email = strsc.nextLine();
		
		System.out.print("Phone number: ");
		String phoneNumber = strsc.nextLine();
		
		BankAccount account = new BankAccount(baId, 0);
		
		UserAccount creation = new UserAccount(uaId, uName, password, email, phoneNumber);
		
		// add user to the database
		uadao.createUserAccount(creation);
		badao.createBankAccount(account);
		
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
