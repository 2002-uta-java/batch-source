package com.charnecki;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jasypt.util.password.BasicPasswordEncryptor;

import com.charnecki.models.Account;
import com.charnecki.models.Checking;
import com.charnecki.models.Deposit;
import com.charnecki.models.Savings;
import com.charnecki.models.Transaction;
import com.charnecki.models.User;
import com.charnecki.models.Withdrawal;
import com.charnecki.services.AccountService;
import com.charnecki.services.TransactionService;
import com.charnecki.services.UserService;

public class Driver {
	
	private static User activeUser = null;
	
	private static UserService us = new UserService();
	private static AccountService as = new AccountService();
	private static TransactionService ts = new TransactionService();
	
	private static BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
	
	private static Scanner sc = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		
		String input = "";
		
		while(!"3".equals(input) && !"exit".equals(input.toLowerCase())) {
			System.out.println("Welcome to Bank | Please pick an option below");
			System.out.println("(1) Log In\n(2) Create Account\n(3) Exit");
			input = sc.nextLine();
			
			switch(input) {
				case "1":
				case "Log In":
				case "log in":
					logIn();
					break;
				case "2":
				case "Create Account":
				case "create account":
					createAccount();
					break;
			}
		}
		
		sc.close();
		
	}
	
	public static boolean validPassword(String p) {
		if (p.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$")) {
			return true;
		}
		return false;
	}
	
	public static void createAccount() {
		
		User u = new User();
		
			
		System.out.println("Please enter a username to associate with the account: ");
		u.setUsername(sc.nextLine());
		System.out.println("Please enter your email address: ");
		u.setEmail(sc.nextLine());
		
		String tempPassword = "temp";
		
		while (!tempPassword.equals(u.getPassword()) || tempPassword.equals("")) {
			System.out.println("Please enter a valid password: ");
			System.out.println("Password must contain: \n- At least 8 characters\n- One uppercase character\n- One lowercase character\n- One number\n- One special symbol(@#$%)");
			u.setPassword(sc.nextLine()); // encryption here
			if (validPassword(u.getPassword())) {
				System.out.println("Please confirm your password by re-entering it: ");
				tempPassword = sc.nextLine();				
			} else {
				System.out.println("Please enter a valid password: ");
			}
		}
		
		u.setPassword(passwordEncryptor.encryptPassword(u.getPassword()));
		
		if (!us.checkUniqueUser(u)) {
			System.out.println("A user already exists with that username or email. Please try again with different values.");
		} else {

			u.setId(us.createUser(u)); // Validate unique user
			
			activeUser = u;
			
			System.out.println("Account has been created."); 
			
			Account a = newAccount();
			
			List<Account> accounts = new ArrayList<Account>();
			accounts.add(a);
			
			u.setAccounts(accounts);
			
			loggedIn();
		
		}
	}
	
	public static Account newAccount() {
		System.out.println("Would you like to open a Savings or Checking account?");
		System.out.println("(1) Savings\n(2) Checking");
		
		String input = "";
		
		while(!"1".equals(input) && !"2".equals(input)  && !"savings".equals(input.toLowerCase()) && !"checking".equals(input.toLowerCase())) {
			input = sc.nextLine();
			if (!"1".equals(input) && !"2".equals(input)  && !"savings".equals(input.toLowerCase()) && !"checking".equals(input.toLowerCase())) {
				System.out.println("Please select one of the options.");
			}
		}
		
		Account a = null;
		
		switch(input.toLowerCase()) {
			case "1":
			case "savings":
				a = new Savings();
				break;
			case "2":
			case "checking":
				a = new Checking();
				break;
		}
		
		List<Integer> holder = new ArrayList<Integer>();
		holder.add(activeUser.getId());
		
		a.setHolders(holder);
		
		a.setId(as.createAccount(a));
		
		return a;
	}
	
	public static void logIn() {
		
		System.out.println("Please enter username: ");
		String username = sc.nextLine();
		System.out.println("Please enter password: ");
		String password = sc.nextLine();
		
		User u = us.getUserByUsername(username);
		if (u == null) {
			System.out.println("User not found.");
		} else if(passwordEncryptor.checkPassword(password, u.getPassword())) {
			activeUser = u;
			u.setPassword("");
			loggedIn();
		} else {
			System.out.println("Invalid password");
		}
	}
	
	public static void loggedIn() {
		
		while(activeUser != null) {
			
			System.out.println("Options Menu:");
			System.out.println("(1) Check Balances\n(2) Make a Deposit\n(3) Make a Withdrawal\n(4) Manage Accounts\n(5) Log Out");
			
			String input = "";
			
			while(!input.matches("^[1-5]$")) {
				input = sc.nextLine();
			}
			
			switch(input) {
				case "1":
					checkBalances();
					break;
				case "2":
					makeDeposit();
					break;
				case "3":
					makeWithdrawal();
					break;
				case "4":
					manageAccounts();
					break;
				case "5":
					activeUser = null;
					break;
			}
			
		}
		
	}
	
	private static Account selectAccounts() {
		System.out.println("Please select an account to transact with: ");
		int i = 0;
		for (Account a:activeUser.getAccounts()) {
			System.out.println("("+(++i)+") "+a.getId() + " | " + a.getAccountType() + " account\nBalance: $" + String.valueOf(a.getBalanceFormatted()));
		}
		System.out.println("--------------------------------------");
		String input = "";
		while(!input.matches("^[1-"+activeUser.getAccounts().size()+"]$")) {
			input = sc.nextLine();
		}
		return activeUser.getAccounts().get(Integer.valueOf(input)-1);
	}
	
	private static void manageAccounts() {
		
		Account a;
		
		System.out.println("Please select an account to manage: ");
		int i = 0;
		for (Account acc:activeUser.getAccounts()) {
			System.out.println("("+(++i)+") "+acc.getId() + " | " + acc.getAccountType() + " account | Holders: " + acc.getHolders());
		}
		System.out.println("("+(++i)+") New Account");
		System.out.println("--------------------------------------");
		String input = "";
		while(!input.matches("^[1-"+(activeUser.getAccounts().size()+1)+"]$")) {
			input = sc.nextLine();
		}
		
		if (String.valueOf(activeUser.getAccounts().size()+1).equals(input)) {
			a = newAccount();
			List<Account> accounts = activeUser.getAccounts();
			accounts.add(a);
			activeUser.setAccounts(accounts);
		} else {			
			a = activeUser.getAccounts().get(Integer.valueOf(input)-1);
		}
		
		System.out.println("What would you like to do with this account?");
		System.out.println("(1) View Transaction History\n(2) Add Holder\n(3) Destroy Account\n(4) Go Back");
		
		input = "";
		
		while(!input.matches("^[1-4]$")) {
			input = sc.nextLine();
		}
		
		switch(input) {
			case "1":
				List<Transaction> tList = ts.getAllWithAccountId(a.getId());
				System.out.println("--------------------------------------");
				for (Transaction t: tList) {
					System.out.println(t.getId() + ": " + t.getTransactionType() + " | " + t.getAmount());
					System.out.println("Note: " + t.getNote());
					System.out.println("--------------------------------------");
				}
				break;
			case "2":
				
				break;
			case "3":
				as.deleteAccount(a);
				System.out.println("Account has been permanently destroyed.");
				break;
			case "4":
				break;
		}
		
	}

	private static void makeWithdrawal() {

		Account a;
		
		if (activeUser.getAccounts().size() > 1) {
			a = selectAccounts();
		} else {
			a = activeUser.getAccounts().get(0);
		}
		
		System.out.println("How much would you like to withdraw?");
		String input = "";
		
		while(!input.matches("[0-9]{1,13}(\\.[0-9]{0,2})?") || Double.valueOf(input) > a.getBalance()) {
			input = sc.nextLine();
			if (!input.matches("[0-9]{1,13}(\\.[0-9]{0,2})?")) {
				System.out.println("Please enter a valid positive number");
			} else if(Double.valueOf(input) > a.getBalance()) {
				System.out.println("You do not have that much in the account.");
			}
		}
		
		System.out.println("Set a note for this withdrawal:");
		String note = sc.nextLine();
		
		Transaction t = new Withdrawal();
		t.setAmount(Double.valueOf(input));
		t.setNote(note);
		t.setAccount(a.getId());
		
		t.setId(ts.createTransaction(t));
		
		a.setBalance( a.getBalance() - t.getAmount() );
		
		as.updateAccount(a);
		
		System.out.println("$" + input + " has been withdrawn from the account. Who knows where it went. New balance: $"+ a.getBalanceFormatted());
		
	}

	private static void makeDeposit() {
		
		Account a;
		
		if (activeUser.getAccounts().size() > 1) {
			a = selectAccounts();
		} else {
			a = activeUser.getAccounts().get(0);
		}
		
		System.out.println("How much would you like to deposit?");
		String input = "";
		
		while(!input.matches("[0-9]{1,13}(\\.[0-9]{0,2})?")) {
			input = sc.nextLine();
			if (!input.matches("[0-9]{1,13}(\\.[0-9]{0,2})?")) {
				System.out.println("Please enter a valid positive number");
			}
		}
		
		System.out.println("Set a note for this deposit:");
		String note = sc.nextLine();
		
		Transaction t = new Deposit();
		t.setAmount(Double.valueOf(input));
		t.setNote(note);
		t.setAccount(a.getId());
		
		t.setId(ts.createTransaction(t));
		
		a.setBalance( a.getBalance() + t.getAmount() );
		
		as.updateAccount(a);
		
		System.out.println("$" + input + " has been deposited into the account. New balance: $"+ a.getBalanceFormatted());
		
	}

	public static void checkBalances() {
		System.out.println("--------------------------------------");
		for (Account a:activeUser.getAccounts()) {
			System.out.println(a.getId() + " | " + a.getAccountType() + " account\nBalance: $" + String.valueOf(a.getBalanceFormatted()));
		System.out.println("--------------------------------------");
		}
	}

}
