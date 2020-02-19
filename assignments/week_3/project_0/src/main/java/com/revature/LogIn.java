package com.revature;

import java.util.Scanner;

import com.revature.models.UserAccount;
import com.revature.service.UserAccountService;

public class LogIn {
	
	private static Scanner sc = new Scanner(System.in);	
	
	public void logIntoAccount() {
		
		UserAccountService uas = new UserAccountService();
		
		System.out.println(">");
		System.out.println("> Loging into your account..............................................................................................");
		System.out.println(">");
				
		System.out.print("> Enter account username (you can also use your email): ");
		String userIdentifierInput = sc.nextLine();
		
		System.out.print("> Enter account password: ");
		String userPasswordInput = sc.nextLine();
		
		
		UserAccount ua = uas.getUserAccount(userIdentifierInput, userPasswordInput);
		
		
		if(ua.getId() == 0) {
			System.out.println(">");
			System.out.println(">");
			System.out.println("> No user was found with credentials provided, please start over. PRESS ENTER TO CONTINUE...............................");
			System.out.println(">");
			System.out.println(">");
			System.out.println("#----------------------------------------------------------------------------------------------------------------------#");
			sc.nextLine();
			return;
		}
		
		LoggedIn li = new LoggedIn();
		li.loggedInView(ua);
	}

}
