package com.revature.project0.userfxns;

import java.util.Scanner;

import com.revature.project0.models.CustomerAccount;
import com.revature.project0.services.CustomerAccountService;
import com.revature.project0.ui.Menu;

public class CreateUser {
	
	public static String createUsername(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your desired username: ");
		//Scanner newUser = new Scanner(System.in);
		Menu.newUsername = sc.nextLine();
		//CustomerAccount some = new CustomerAccount();
		CustomerAccountService some = new CustomerAccountService();
		some.checkCustomerAccountUsername(Menu.newUsername);
		if (some.checkCustomerAccountUsername(Menu.newUsername) == false) {
			System.out.println("Username Taken");
			return null;
		} else {
			System.out.println("Username available");
			return Menu.newUsername;
		}
	}
	
	public static void createPassword() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter desired password: ");
		//Scanner newPass = new Scanner(System.in);
		Menu.newPassword = sc.nextLine();
		
		System.out.println("Enter first name: ");
		//Scanner newFirst = new Scanner(System.in);
		Menu.newFirstName = sc.nextLine();
		
		System.out.println("Enter last name: ");
		//Scanner newLast = new Scanner(System.in);
		Menu.newLastName = sc.nextLine();
		
		System.out.println("Enter email: ");
		//Scanner newE = new Scanner(System.in);
		Menu.newEmail = sc.nextLine();
	}
	
	// enter info into db
	
}
