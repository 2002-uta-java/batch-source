package com.revature.services;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.daos.AccountDao;
import com.revature.daos.AccountDaoImpl;
import com.revature.models.UserAccount;

public class LoginService {
	
	private AccountDao dao = new AccountDaoImpl();
	private UserAccountService uas = new UserAccountService();
	private BankAccountService bas = new BankAccountService();

	public void loginMenu() {
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
			    		
			    		if (usernameRequest == "#") {
			    			break;
			    		}
			    		else {
			    			// Validate length of input.
			    			int len = usernameRequest.length();
			    			
			    			if (len < 1 || len > 25) {
			    				System.out.println("Invalid input (username must be between 1-25 characters).");
			    			}
			    			else {
			    				if (uas.userAccountExist(usernameRequest)) {
			    					UserAccount u = dao.getUserAccount(usernameRequest);
			    					
			    					System.out.println("Enter your password.");
			    					String passwordAttempt = s.nextLine();
			    					
			    					if (passwordAttempt == u.getPassword()) { // would rather use u.passwordMatch(passwordAttempt)
			    						bas.bankAccountMenu(u);				  // but pointless because of dao
			    						break;
			    					}
			    					else {
			    						System.out.println("Password incorrect.");
			    					}
			    				}
			    			}
			    		}
		    		}
		    		break;
		    	case 2:
		    		uas.userAccountMenu();
		    		break;
		    	case 3:
		    		running = false;
		    		break;
		    	default:
		    		System.out.println("Invalid input (choose option 1, 2, or 3).");
		    	}
		    }
		    catch (InputMismatchException e) {
		    	e.printStackTrace();
		    	System.out.println("Invalid input.");
		    }
		}
		
	}

}
