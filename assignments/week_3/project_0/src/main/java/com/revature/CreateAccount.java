package com.revature;

import java.util.Scanner;

import com.revature.models.Client;
import com.revature.models.UserAccount;
import com.revature.service.ClientService;
import com.revature.service.UserAccountService;

public class CreateAccount {
	
	private static Scanner sc = new Scanner(System.in);
	
	public void CreateNewAccount() {
		
		ClientService cs = new ClientService();
		UserAccountService uas = new UserAccountService();

		System.out.println(">");
		System.out.println("> Creating a new user account...........................................................................................");
		System.out.println(">");
		Client c = new Client();
		
		System.out.print("> Please enter a first name: ");
		c.setFirstName(sc.nextLine());
		
		// VALIDATE HERE
		
		System.out.print("> Please enter a last name: ");
		c.setLastName(sc.nextLine());
		
		// VALIDATE HERE
		
		cs.createClient(c);
		
		System.out.println(">");
		UserAccount ua = new UserAccount();
		ua.setClientId(c.getId());
		
		System.out.print("> Please enter an email: ");
		ua.setEmail(sc.nextLine());
		
		// VALIDATE HERE
		
		System.out.println(">");
		System.out.print("> Would you like to use a username? (y/n): ");
		String userInput = sc.nextLine();
		
		if(userInput.contentEquals("y")) {
			System.out.print("> Please enter a username: ");
			ua.setUserName(sc.nextLine());
		} else if (userInput.contentEquals("n")){
			//
		} else {
			System.out.println("> Invalid input - will continue without username");
		}
		
		System.out.println("> ");
		System.out.print("> Please set a password for your user account: ");
		ua.setPassword(sc.nextLine());
		
		// VALIDATE HERE
		
		uas.createUserAccount(ua);

		System.out.println(">");
		System.out.println(">");
		System.out.println("> Account created. Please proceed to the login screen and log in. PRESS ENTER TO CONTINUE..............................");
		System.out.println(">");
		System.out.println(">");
		sc.hasNextLine();
		
	}

}
