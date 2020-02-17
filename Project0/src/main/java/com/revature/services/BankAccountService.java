package com.revature.services;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.revature.daos.AccountDao;
import com.revature.daos.AccountDaoImpl;
import com.revature.models.BankAccount;
import com.revature.models.UserAccount;

public class BankAccountService {
	
	private AccountDao dao = new AccountDaoImpl();
	
	public void bankAccountMenu(UserAccount u) {
		Scanner s = new Scanner(System.in);
		BankAccount b = dao.getBankAccount(u);
		boolean running = true;
		
		while (running) {  
			System.out.println("(Username: " + u.getUsername() + ")\nPress 1 to make a deposit, 2 to make a withdrawal, "
																			  + "3 to view your balance, 4 to logout.");
			
		    try {
		    	int request = s.nextInt();
		    	s.nextLine();
		    	
		    	switch (request) {
		    	case 1:
		    		deposit(b);
		    		break;
		    	case 2:
		    		withdraw(b);
		    		break;
		    	case 3:
		    		viewBalance(b);
		    		break;
		    	case 4:
		    		running = false;
		    		System.out.println("Logging out...");
		    		break;
		    	default:
		    		System.out.println("Invalid input (choose option 1, 2, 3, or 4).");
		    	}
		    }
		    catch (InputMismatchException e) {
		    	e.printStackTrace();
		    	System.out.println("Invalid input.");
		    }
		}
	}
		    
	    
	
	public void deposit(BankAccount b) {
		// ask for deposit amount, offer cancel
		// if cancel break
		// valid entry (must be float any limit, perhaps test enormous float)
		// if valid, do overflow balance calculation (999billion.99)
		// if overflow return error
		// if valid, perform operation, say deposit successful, return updated balance.
		Scanner s = new Scanner(System.in);
		
		while (true) {
    		System.out.println("Enter your deposit amount (max balance = $999,999,999.99). Press # to cancel.");
    		try {
    			float depositRequest = s.nextFloat();
//    			s.nextLine()
    			
    			// TODO: is float, proceed with valid length check, break at end of this
    			// need to round excess decimals
    		}
    		catch (InputMismatchException e) {
    			try {
//    				s.nextLine();
    				String depositRequest = s.nextLine();
    				if (depositRequest == "#") {
		    			break;
		    		}
    				else {
    					System.out.println("Invalid input.");
    				}
    			}
    			catch (InputMismatchException e1) {
    				e1.printStackTrace();
    				System.out.println("Invalid input.");
    			}
    		}
		}
	}
	
	public void withdraw(BankAccount b) {
		// ask for withdrawal amount, offer cancel
		// if cancel break
		// validate entry (must be float any limit)
		// if valid, do negative balance calculation
		// if negative return error
		// if valid, perform operation, say operation successful, return updated balance.
		Scanner s = new Scanner(System.in);
		
		while (true) {
    		System.out.println("Enter your withdrawal amount (min balance = $0.00). Press # to cancel.");
    		try {
    			float withdrawalRequest = s.nextFloat();
//    			s.nextLine()
    			
    			// TODO: is float, proceed with valid length check, break at end of this
    			// need to round excess decimals
    		}
    		catch (InputMismatchException e) {
    			try {
//    				s.nextLine();
    				String withdrawalRequest = s.nextLine();
    				if (withdrawalRequest == "#") {
		    			break;
		    		}
    				else {
    					System.out.println("Invalid input.");
    				}
    			}
    			catch (InputMismatchException e1) {
    				e1.printStackTrace();
    				System.out.println("Invalid input.");
    			}
    		}
		}
	}
	
	public void viewBalance(BankAccount b) {
		System.out.println("Your balance = " + b.getBalance());
	}
}
