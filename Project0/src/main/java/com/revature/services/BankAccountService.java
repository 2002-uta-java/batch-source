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
			System.out.println("(Username: " + u.getUsername() + ") Press 1 to make a deposit, 2 to make a withdrawal, "
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
//		    	e.printStackTrace();
		    	s.nextLine();
		    	System.out.println("Invalid input.");
		    }
		}
	}
		    
	    
	
	public void deposit(BankAccount b) {
		Scanner s = new Scanner(System.in);
		
		while (true) {
    		System.out.println("Enter your deposit amount (max balance = $999,999,999.99). Press # to cancel.");
    		try {
    			float depositRequest = roundTwoDecimal(s.nextFloat());
    			float oldBalance = roundTwoDecimal(b.getBalance());
    			float newBalance = roundTwoDecimal(oldBalance + depositRequest);
    			float MAX_BAL = (float) 999999999.99; // 11 total digits.
    			System.out.println("Depositing $" + depositRequest);
    			
    			if (newBalance > MAX_BAL) {
    				System.out.println("Cancelling deposit (max balance breached).");
    			}
    			else {
    				b.setBalance(newBalance);
    				dao.updateBankAccount(b);
    				System.out.println("Deposit successful!");
    				break;
    			}
    		}
    		catch (InputMismatchException e) {
    			try {
    				String depositRequest = s.nextLine();
    				if (depositRequest.equals("#")) {
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
		Scanner s = new Scanner(System.in);
		
		while (true) {
    		System.out.println("Enter your withdrawal amount (min balance = $0.00). Press # to cancel.");
    		try {
    			float withdrawalRequest = roundTwoDecimal(s.nextFloat());
    			float oldBalance = roundTwoDecimal(b.getBalance());
    			float newBalance = roundTwoDecimal(oldBalance - withdrawalRequest);
    			float MIN_BAL = (float) 0.00;
    			System.out.println("Withdrawing $" + withdrawalRequest);
    			
    			if (newBalance < MIN_BAL) {
    				System.out.println("Cancelling withdrawal (min balance breached).");
    			}
    			else {
    				b.setBalance(newBalance);
    				dao.updateBankAccount(b);
    				System.out.println("Withdrawal successful!");
    				break;
    			}
    		}
    		catch (InputMismatchException e) {
    			try {
    				String withdrawalRequest = s.nextLine();
    				if (withdrawalRequest.equals("#")) {
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
	
	public void viewBalance (BankAccount b) {
		System.out.println("Your balance = " + b.getBalance());
	}
	
	public float roundTwoDecimal(float f) {
		return (float) Math.round(f * 100f) / 100f;
	}
}
