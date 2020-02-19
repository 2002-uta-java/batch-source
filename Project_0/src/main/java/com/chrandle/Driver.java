package com.chrandle;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.chrandle.models.*;
import com.chrandle.daos.*;
import com.chrandle.services.*;


public class Driver {
	static UserService uService = new UserService();
	static AccountService aService = new AccountService();
	static Scanner input = new Scanner(System.in);
	static User loggedUser = null;
	public static void main(String[] args) {
		System.out.println("		PROGRAM START		\n");

		
//		List<User> users = uService.getUsers();
//		List<Account> accounts = aService.getAccounts();
//
//		
//		System.out.println("	USERS\n");
//		for (User u: users) {
//			System.out.println("		"+u.toString());
//		}
//		
//		System.out.println("	ACCOUNTS\n");
//		for (Account a: accounts) {
//			System.out.println("		"+a.toString());
//		}
		
		
		startMenu();
		
		System.out.println("\n		PROGRAM END		");
		return;
		
	}
	
	public static void startMenu() {
		
		while (true) {
			
			System.out.print("\nWhat would you like to do?\n"
					+ "Please enter a numeric input\n"
					+ "	1: Login to existing user\n"
					+ "	2: Create new user account\n"
					+ "	3. Exit application\n");
			int i = 0;
			
			try {
				i = Integer.parseInt(input.nextLine());
			} catch(Exception e) {
				System.out.print("\n------------------------------------\n"
						+ "Please enter a numeric input that corresponds to the options listed\n"
						+ "\n------------------------------------\n");
				continue;
			}
			
			switch (i) {
			case 1:
				login();
				userMenu();
				break;
				
			case 2:
			{
				newUser();
				userMenu();
			}
				break;
				
			case 3:
				System.out.println("\n		PROGRAM END		");
				System.exit(0);
				break;

			default:
				System.out.print("\n------------------------------------\n"
						+ "Please enter a numeric input that corresponds to the options listed\n"
						+ "\n------------------------------------\n");
				break;
			}
		}
	}
	
	static public void newUser() {
		String usern = "", email = "", pw = "";
		long  added = -1;
		while (added == -1) {

			System.out.print("Please Enter a user name: ");
			usern = input.nextLine();
			System.out.print("Please Enter a valid email address: ");
			email = input.nextLine();
			
			while (!email.matches("^([a-zA-Z0-9_\\-\\.]+)@"
					+ "([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) {
				System.out.print("that is not a valid email address; please enter an email adress: ");
				email = input.nextLine();
				continue;
			}
			
			System.out.print("Please Enter a password: ");
			pw = input.nextLine();
			added = uService.createUser (usern, email, pw);
		}
		loggedUser = uService.getUserById(added);
		System.out.println("Account Created for"+loggedUser.getEmail());
	}
	
	static public void userMenu(){
		while (true) {
			System.out.print("\n Welcome "+""+"\n"
					+ "What would you like to do?\n"
					+ "Please enter a numeric input\n"
					+ "		1: View Accounts\n"
					+ "		2: Withdraw from an account\n"
					+ "		3: Deposit to an account\n"
					+ "		4: Create an an account\n"
					+ "		5: Delete user\n"
					+ "		6. Logout\n");
			int i = 0;
			
			try {
				i = Integer.parseInt(input.nextLine());
			} catch(Exception e) {
				System.out.print("\n------------------------------------\n"
						+ "Please enter a numeric input that corresponds to the options listed\n"
						+ "\n------------------------------------\n");
				continue;
			}
			switch (i) {
			case 1:
				List<Account> a = uService.viewAccounts(loggedUser.getUserid());
				if (a == null) {
					System.out.println("You have no accounts open currently");
					continue;
				}
				for (Account index : a) {
					System.out.println("		"+index.toString());
				}
				break;
			case 2:
				//TODO:
				break;
			case 3:
				//TODO:
				break;
			case 4:
				accountCreation();
				break;
			case 5:
				//TODO:
				break;
			case 6:
				return;
			default:
				break;
			}
		}
	}
	
	static void login(){
		String usern = "", pw = "";
		
		while(true) {
			System.out.print("Please Enter a user name: ");
			usern = input.nextLine();
			
			System.out.print("Please Enter a password: ");
			pw = input.nextLine();
			
			loggedUser = uService.userLogin(usern, pw);
			
			if (loggedUser != null) {
				return;
			} else {
				System.out.println("Credentials invalid, please try again");
				continue;
			}
				
			
		}
	}
	
	static void accountCreation() {
		String bal = "", ty = "";
		double i = 0.0;
		long a = 0;
		while(true) {
			System.out.print("Please enter a starting balance: ");
			bal = input.nextLine();
			
			try {
				i = Double.parseDouble(bal);
				if (i<0) throw new Exception();
			} catch(Exception e) {
				System.out.print("\n------------------------------------\n"
						+ "Please enter a positive value in decimal format"
						+ "\n------------------------------------\n");
				continue;
			}
			
			System.out.print("Please Enter an account type: ");
			ty = input.nextLine();
			
			a = aService.createAccount(loggedUser.getUserid(),i,ty);
			
			if (a != 0) {
				loggedUser.addAcount(aService.getAccountById(a));
				return;
			} else {
				System.out.println("account specs invalid, please try again");
				continue;
			}
				
			
		}
	}
	
	
}
