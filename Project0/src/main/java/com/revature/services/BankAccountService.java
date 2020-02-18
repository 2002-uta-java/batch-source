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
		BankAccount b = dao.getBankAccountByUserAccount(u);
		boolean running = true;
		
		while (running) {  
			System.out.println("(Username: " + u.getUsername() + "  BankId: " + u.getBankId() + ")\n"
				     + "Press 1 to make a deposit, 2 to make a withdrawal, 3 to view your balance, "
															 + "4 to transfer funds, 5 to logout.");
			
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
		    		transferFunds(b);
		    		break;
		    	case 5:
		    		running = false;
		    		System.out.println("Logging out...");
		    		break;
		    	default:
		    		System.out.println("Invalid input (choose option 1, 2, 3, 4, or 5).");
		    	}
		    }
		    catch (InputMismatchException e) {
		    	s.nextLine();
		    	System.out.println("Invalid input.");
		    }
		}
	}
	
	public void deposit(BankAccount b) {
		Scanner s = new Scanner(System.in);
		
		while (true) {
    		System.out.println("Enter your deposit amount (max balance = $20,000,000). Press # to cancel.");
    		try {
    			float depositRequest = roundTwoDecimal(s.nextFloat());
    			float oldBalance = roundTwoDecimal(b.getBalance());
    			float newBalance = roundTwoDecimal(oldBalance + depositRequest);
    			float MAX_BAL = (float) 20000000.00; // 11 digits in SQL, but max val of float is around 2.7*e7.
    			System.out.println("Depositing $" + depositRequest);
    			
    			if (depositRequest < 0) {
    				System.out.println("Deposit amount cannot be negative.");
    			}
    			else if (newBalance > MAX_BAL) {
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
    			
    			if (withdrawalRequest < 0.00) {
    				System.out.println("Withdrawal amount cannot be negative.");
    			}
    			else if (newBalance < MIN_BAL) {
    				System.out.println("Cancelling withdrawal (insufficient funds).");
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
    				System.out.println("Invalid input.");
    			}
    		}
		}
	}
	
	public void viewBalance (BankAccount b) {
		System.out.println("Your balance = $" + b.getBalance());
	}
	
	public void transferFunds (BankAccount b1) {
		Scanner s = new Scanner(System.in);
		
		while (true) {
    		System.out.println("Enter your transfer amount (max balance = $20,000,000). Press # to cancel.");
    		try {
    			float transferRequest = roundTwoDecimal(s.nextFloat());
    			float oldBalance1 = roundTwoDecimal(b1.getBalance()); // b1 = current account, b2 = target account
    			float newBalance1 = roundTwoDecimal(oldBalance1 - transferRequest);
    			float MAX_BAL = (float) 20000000.00;
    			
    			if (transferRequest < 0) {
    				System.out.println("Transfer amount cannot be negative.");
    			}
    			else if (newBalance1 < 0) {
    				System.out.println("Insufficient funds.");
    			}
    			else {
    				System.out.println("Enter the BankId of the account you'd like to transfer funds to.");
    				
    				try {
	    				int bankId2 = s.nextInt(); // be able to handle the nextline bug, even with invalid entries
	    				s.nextLine();
	    				
	    				if (bankAccountExists(bankId2)) {
		    				BankAccount b2 = dao.getBankAccountByBankId(bankId2);
		    				float oldBalance2 = roundTwoDecimal(b2.getBalance());
		        			float newBalance2 = roundTwoDecimal(oldBalance2 + transferRequest);
		        			
		        			if (newBalance2 > MAX_BAL) {
		        				System.out.println("Cancelling transfer (max capacity issue).");
		        			}
		        			else {
			    				b1.setBalance(newBalance1);
			    				b2.setBalance(newBalance2);
			    				dao.updateBankAccount(b1);
			    				dao.updateBankAccount(b2);
			    				System.out.println("Transfer successful!");
			    				break;
		        			}
	    				}
	    				else {
		    				System.out.println("BankId does not exist.");
	    				}
	    				
    				}
    				catch (InputMismatchException e2) {
    					s.nextLine();
    					System.out.println("Invalid input.");
    				}
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
    				System.out.println("Invalid input.");
    			}
    		}
		}
	}
	
	public boolean bankAccountExists(int bankId) {
		List<Integer> bankAccounts = dao.getBankAccounts();
		
		for (int dbBankId: bankAccounts) {
			if (bankId == dbBankId) {
				return true;
			}	
		}
		
		return false;
	}
	
	public float roundTwoDecimal(float f) {
		return (float) Math.round(f * 100f) / 100f;
	}
}
