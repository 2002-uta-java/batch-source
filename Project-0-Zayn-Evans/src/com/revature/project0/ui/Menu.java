package com.revature.project0.ui;

import java.util.Scanner;

import com.revature.project0.userfxns.CreateUser;
import com.revature.project0.userfxns.ValidateUser;

public class Menu {
	public static String newUsername;
	public static String newPassword;
	public static String newFirstName;
	public static String newLastName;
	public static String newEmail;
	
	public static double balance;
	
	
	public void displayMainMenu() {
		System.out.println("Welcome to Zayn's Bank\n");
		System.out.println("Main Menu");
		System.out.println("1) Create Account (if new user)\n" 
		+ "2) Log In (if existing user)\n" 
		+ "3) Exit");
		System.out.println("(Type \"1\" for option 1, \"2\" for option 2, and \"exit\" to exit application.)\n");
	}

	public void inputSelection() {

	}

	public Menu() {
		Scanner input = new Scanner(System.in);
		displayMainMenu();
		
		while (true) {
			switch (input.nextLine().trim()) {
			
			case "exit":
				System.out.println("Thanks for using Zayn's Bank");
				input.close();
				System.exit(0);
				break;
				
			case "1":
				System.out.println("Create new account...");
				CreateUser.createUsername();
				// TODO check if username is unique
				
				CreateUser.createPassword();
				// TODO create user record in db, then come back to menu
				
				System.out.println("Account Created");
				displayMainMenu();
				break;
				
			case "2":
				System.out.println("Logging in...");
				ValidateUser.login();
				// TODO validate credentials
					// if invalid, re-enter
					// if valid, continue to 2nd menu
				
				ValidateUser.loginp();
				System.out.println("Successfully logged in\n");
				
				ValidateUser.menu2();
				break;
				
			default:
				System.out.println("Invalid input, please use 1, 2, or 'exit' to select one of the menu options");
				continue;
			}
		}
	}

}
