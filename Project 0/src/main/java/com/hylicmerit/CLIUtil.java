package com.hylicmerit;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.validator.routines.EmailValidator;

import com.hylicmerit.models.Account;
import com.hylicmerit.models.Transaction;
import com.hylicmerit.models.User;
import com.hylicmerit.service.AccountService;
import com.hylicmerit.service.TransactionService;
import com.hylicmerit.service.UserService;

public class CLIUtil {

	private static UserService uService = new UserService();

	private static AccountService aService = new AccountService();
	
	private static TransactionService tService = new TransactionService();

	private static User user = null;

	private static List<Account> accounts = null;

	private static Scanner scan = new Scanner(System.in);

	private static boolean exit = false;
	
	private static boolean success = false;

	public static void options() {
		if (user == null) {
			System.out.println("Welcome to the NAVICO Banking System. \nPlease enter 'login' or 'create profile': ");

			String initOption = scan.nextLine();

			switch (initOption) {
			case "login": {
				login();
				break;
			}
			case "create profile": {
				createProfile();
				break;
			}
			default: {
				System.out.println("Please enter a valid option.");
				options();
				break;
			}
			}
		} else {
			System.out.println("\nWelcome, " + user.getName() + ". Please enter one of the following options: \n"
					+ "\nlogout (exit the application) create account (creates a new checkings/savings account)"
					+ "\nbalance (displays the current balance of your accounts) deposit (deposit into account)"
					+ "\nwithdraw (withdraw from account) delete profile (removes user profile and accounts from database)"
					+ "\ndelete account (removes selected account from database) show transactions (view transactions from an account) \n");

			String profileOption = scan.nextLine();
			switch (profileOption) {
			case "logout": {
				logout();
				break;
			}
			case "create account": {
				createAccount();
				break;
			}
			case "delete account":{
				deleteAccount();
				break;
			}
			case "delete profile":{
				deleteProfile();
				break;
			}
			case "balance": {
				viewBalance();
				break;
			}
			case "deposit": {
				updateBalance(profileOption);
				break;
			}
			case "withdraw": {
				updateBalance(profileOption);
				break;
			}
			case "show transactions": {
				showTransactions();
				break;
			}
			default: {
				System.out.println("Please enter a valid option.");
				options();
				break;
			}
			}
		}

		accounts = aService.getAllAccounts(user);
	}

	private static void login() {

		System.out.println("------------LOG IN------------\nPlease enter your username: ");

		String username = scan.nextLine();

		uService.getUserById(username);

		user = User.getInstance();

		if(user != null) {
			System.out.println("Please enter your password: ");

			while (scan.hasNextLine()) {
				String password = scan.nextLine();
				if (password.equals(user.getPassword())) {
					break;
				} else {
					System.out.println("Wrong credentials. Please try again: ");
				}
			}

			user.setLoggedIn(true);

			success = uService.updateUser(user);
			
			if(success) {
				System.out.println("Successfully logged in.");
			} else {
				System.out.println("We were unable to log you into your account.");
			}
		} else {
			System.out.println("The username you have entered does not exist.");
		}
	}

	private static void logout() {
		System.out.println("------------LOGGING OUT------------");

		user.setLoggedIn(false);

		uService.updateUser(user);

		setExit(true);
	}

	private static void createProfile() {
		System.out.println("------------CREATING USER PROFILE------------");

		System.out.println("Please enter your full name (max 40 characters): ");

		String name = scan.nextLine();

		String username = checkUsernameAvailability();
		
		System.out.println("Please enter a secure password (max 20 characters): ");

		String password = scan.nextLine();
		
		String email = validateEmail();

		User.init(name, username, password, email, true);

		user = User.getInstance();

		success = uService.createUser(user);
		
		if(success) {
			System.out.println("Successfully created a profile for " + user.getName() + " in our system.");
		} else {
			System.out.println("We were unable to create a profile for " + user.getName() + " in our system.");
		}
	}

	private static void createAccount() {
		StringBuilder types = new StringBuilder();
		for (Account a : accounts) {
			types.append(a.getType());
		}
		if (accounts.size() >= 2) {
			System.out.println("You cannot create more accounts.");
		} else {
			System.out.println("Please enter a starting balance: ");
			double balance = Double.parseDouble(scan.nextLine());
			Account a = null;
			if (types.indexOf("savings") != -1) {
				System.out.println("Would you like to create a checking account?");
				if (promptYesNo()) {
					a = new Account(balance, "checking");
					success = aService.createAccount(a, User.getInstance());
				}
			} else {
				System.out.println("Would you like to create a savings account?");
				if (promptYesNo()) {
					a = new Account(balance, "savings");
					success = aService.createAccount(a, User.getInstance());
				}
			}
			
			if(success) {
				System.out.println("Successfully created your " + a.getType() + " account.");
			} else {
				System.out.println("We were unable to create this account for your profile.");
			}
		}
	}

	private static void viewBalance() {
		if (accounts.size() > 0) {
			for (Account a : accounts) {
				System.out.println("Your current balance in your " + a.getType() + " account is " + a.getBalance());
			}
		} else {
			System.out.println("Please select the option to create a bank account.");
		}
	}

	private static void updateBalance(String option) {
		if (accounts.size() == 0) {
			System.out.println("Please select the option to create an account.");
		}
		else {
			System.out.println("Which account would you like to " + option + (option == "deposit" ? " into?" : " from?"));
			System.out.println("Available accounts: ");
			StringBuilder types = new StringBuilder();
			for (Account a : accounts) {
				types.append(a.getType());
				System.out.println(a.getType());
			}
			String answer = scan.nextLine();
			Account a = null;
			if(types.indexOf(answer) == -1) {
				System.out.println("You currently don't have a " + answer + " account.");
			}
			else {
				System.out.println("Enter the amount you would like to " + option + " : \n");
				int amount = Integer.parseInt(scan.nextLine());
				if(accounts.get(0).getType().equals(answer)) {
					a = accounts.get(0);
				}
				else {
					a = accounts.get(1);
				}
				if(option.equals("withdraw")) {
					amount *= -1;
				}
				
				success = aService.updateAccount(a, amount);
				
				if(success) {
					System.out.println("Successfully updated your " + a.getType() + " account.");
					
					success = tService.createTransaction(a, user, amount);
					
					if(success) {
						System.out.println("Successfully added the transaction to your " + a.getType() + " account.");
					} else {
						System.out.println("Unable to save transaction to your account.");
					}
				} else {
					System.out.println("Insufficient funds in your account.");
				}
			}
		}
	}
	
	private static void deleteProfile() {
		success = uService.deleteUser(user);
		
		if(success) {
			System.out.println("Successfully deleted your profile and accounts from our database.");
		} else {
			System.out.println("We were unable to delete your profile.");
		}
	}
	
	private static void deleteAccount() {
		if (accounts.size() == 0) {
			System.out.println("Please select the option to create an account.");
		}
		else {
			System.out.println("Which account would you like to delete?");
			System.out.println("Available accounts: ");
			StringBuilder types = new StringBuilder();
			for (Account a : accounts) {
				types.append(a.getType());
				System.out.println(a.getType());
			}
			String answer = scan.nextLine();
			Account a = null;
			if(types.indexOf(answer) == -1) {
				System.out.println("You currently don't have a " + answer + " account.");
			}
			else {
				if(accounts.get(0).getType().equals(answer)) {
					a = accounts.get(0);
				}
				else {
					a = accounts.get(1);
				}
				success = aService.deleteAccount(a);
				
				if(success) {
					System.out.println("Successfully deleted your " + a.getType() + " account.");
				} else {
					System.out.println("We were unable to delete this account.");
				}
			}
		}
	}
	
	//functions that handle user input and don't go through service
	
	private static void showTransactions() {
		List<Transaction> transactions = tService.getAssociatedTransactions(user);
		for(Transaction t : transactions) {
			System.out.println(t);
		}
	}

	public static boolean isExiting() {
		return exit;
	}

	public static void setExit(boolean exit) {
		CLIUtil.exit = exit;
	}

	public static boolean promptYesNo() {
		String answer = scan.nextLine();
		if (answer.toLowerCase().trim().equals("yes")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String checkUsernameAvailability() {
		System.out.println("Please enter a unique username (max 15 characters): ");

		String username = scan.nextLine();
		if(uService.checkUsernameAvailability(username)) {
			return username;
		} else {
			System.out.println("That username isn't available in our system.");
			return checkUsernameAvailability();
		}
	}
	
	public static String validateEmail() {
		System.out.println("Please enter your email (max 20 characters): ");

		String email = scan.nextLine();
		
		if(EmailValidator.getInstance().isValid(email)) {
			return email;
		} else {
			System.out.println("Please provide a valid email.");
			return validateEmail();
		}
	}
}
