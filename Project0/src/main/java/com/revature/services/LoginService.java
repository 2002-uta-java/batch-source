package com.revature.services;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginService {

	public static void loginMenu() {
		Scanner s = new Scanner(System.in);
		boolean running = true;
		
		while (running) {  
		    System.out.println("Press 1 to login, 2 to create an account, 3 to close the application.");
		    try {
		    	int request = s.nextInt();
		    	s.nextLine(); // bug fix: need a nextLine after a nextInt, otherwise skips the next nextLine.
		    	
		    	switch (request) {
		    	case 1:
		    		while (true) {
			    		System.out.println("Enter your username (1-25 characters). (Press # to cancel.)");
			    		String usernameRequest = s.nextLine();
			    		// check if #? -> break
			    		// check if valid input length
			    		// check if username exists (not null return)
			    		// ask for password
			    		// if passwords match, refer to bankaccountmenu ->break
			    		// if not, back to enter username
			    		System.out.println("usernamereq = " + usernameRequest);
			    		break;
		    		}
		    		break;
		    	case 2:
		    		System.out.println("2"); // refer to useraccountmenu
		    		break;
		    	case 3:
		    		System.out.println("3");
		    		running = false;
		    		break;
		    	default:
		    		System.out.println("Invalid input.");
		    	}
		    }
		    catch (InputMismatchException e) {
		    	e.printStackTrace();
		    	System.out.println("Invalid input.");
		    }
		}
		
	}

}
