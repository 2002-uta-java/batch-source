package com.revature.ui;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.AccountServices;
import com.revature.services.UserServices;

public class UIDriver {
	
	static Scanner in = new Scanner(System.in);
	static UserServices us = new UserServices();
	static User user;
	static AccountServices as = new AccountServices();
	static Account account;
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	
	public static void start() throws NoSuchAlgorithmException {
		while(true) {
			printLine();
			printMainMenuOptions();
			char option = getInput();
			switch(option) {
				case '1':
					signIn();
					break;
				case '2':
					signUp();
					break;
				default:
					System.out.println("Invalid input\n\n");
			}
		}	
	}
	
	private static char getInput() {
		if(in.hasNext()) {
			String option = in.next().trim().replace("\n", "");
			if(option.length() > 1)
				return 0;
			else
				return option.charAt(0); //Need to handle whitespace
		}
		return 0;
	}

	private static void signIn() throws NoSuchAlgorithmException {
		System.out.println("\n************************************\n");
		System.out.println("Enter your Username:\n");
		System.out.print(">>");
		String username = in.next();
		System.out.println("\nEnter your password:\n");
		System.out.print(">>");
		String password = in.next();
		user = us.validateUser(username, SHA1(SHA256(password)));
		if(user != null)
			welcome();
		
		return;
	}
	
	private static void welcome() throws NoSuchAlgorithmException {
		while(true) {
			System.out.println("\nWelcome to Revabank\n\nUser ID: " + user.getUser_id() + ", Username: " + user.getUsername() + "\n");
			account = as.getAccount(user.getUser_id());
			System.out.println("Account number: " + account.getAccount_id() + ", Balance: $" + df2.format(account.getBalance()) + "\n");
			printUserOptions();
			char a = getInput();
			switch(a) {
				case '1':
					System.out.print("\nEnter amount:\n>>");
					if(in.hasNextDouble()) {
						double amount = in.nextDouble();
						if(as.deposit(account, amount)) {
							System.out.println("Successfully deposited: " + amount +"\n");
						}
						else {
							System.out.println("Error with deposit\n");
						}
					}
					else {
						System.out.println("Invalid input\n");
					}
					break;
				case '2':
					System.out.print("\nEnter amount:\n>>");
					if(in.hasNextDouble()) {
						double amount = in.nextDouble();
						if(as.withdraw(account, amount)) {
							System.out.println("Successful withdraw: " + amount +"\n");
						}
						else {
							System.out.println("Error with withdraw\n");
						}
					}
					else {
						System.out.println("Invalid input\n");
					}
					break;
				case '3':
					System.out.println("Signing out\n\n");
					signOut();
					break;
				default:
					System.out.println("Invalid input\n");
			}
		}	
		
	}
	
	private static void signOut() throws NoSuchAlgorithmException {
		user = null;
		account = null;
		start();
	}

	private static void printUserOptions() {
		System.out.println("\n\nSelect one of the following options:\n");
		System.out.print("\n[ 1 ] Make a Deposit.\n[ 2 ] Make a Withdraw.\n[ 3 ] Sign out.\n>>");
		return;
	}

	private static void signUp() throws NoSuchAlgorithmException {
		System.out.println("\n************************************\n");
		System.out.println("Enter your Username:\n");
		System.out.print(">>");
		String username = in.next();
		System.out.println("\nEnter your password:\n");
		System.out.print(">>");
		String password = in.next();
		user = new User(username, SHA1(SHA256(password)));
		if(us.createUser(user)) {
			user = us.getUserByUsername(username);
			account = new Account(user.getUser_id());
			/* This method creates an account using a prepared statement
			if(as.createAccount(account)) {
				account = as.getAccount(user.getUser_id());
				welcome();
			}*/
			if(as.createAccountWithFunction(account)) {
				account = as.getAccount(user.getUser_id());
				welcome();
			}
		}
		return;
	}
	
	private static void printMainMenuOptions() {
		System.out.println("\n\nSelect one of the following options:\n");
		System.out.print("\n[ 1 ] Sign in.\n[ 2 ] Sign up.\n>>");
		return;
	}
	
	
	private static void printLine() {
		System.out.println("************************************");
		System.out.println("*****Hello, Welcome to Revabank*****\n");
		System.out.println("************************************");
	}
	
	
	/*
	 *--------------------Methods for Encryption---------------------
	 */
	
	private static byte[] SHA256(String raw) throws NoSuchAlgorithmException{
		MessageDigest SHA256 = MessageDigest.getInstance("SHA-256");
		byte[] hash = SHA256.digest(raw.getBytes(StandardCharsets.UTF_8));
		
		return hash;
	}
	
	private static String SHA1(byte[] raw) throws NoSuchAlgorithmException{
		MessageDigest SHA1 = MessageDigest.getInstance("SHA-1");
		raw = SHA1.digest(BH(raw).getBytes(StandardCharsets.UTF_8));
		String hashed = BH(raw);
		
		return hashed;
	}
	
	private static String BH(byte[] b){ //bytes to hex
		String h = "";
		for(int i = 0; i < b.length; ++i)
			h = h + String.format("%02x", b[i]);
		
		return h;
	}
}
