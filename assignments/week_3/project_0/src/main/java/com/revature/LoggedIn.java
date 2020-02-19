package com.revature;

import java.util.List;
import java.util.Scanner;

import com.revature.models.BankAccount;
import com.revature.models.Client;
import com.revature.models.UserAccount;
import com.revature.service.BankAccountService;
import com.revature.service.ClientService;
import com.revature.service.UserToBankAccountService;

public class LoggedIn {
	
	private static Scanner sc = new Scanner(System.in);
	
	public void loggedInView(UserAccount ua) {
		
		boolean stayLoggedIn = true;
		
		ClientService cs = new ClientService();
		BankAccountService bas = new BankAccountService();
		UserToBankAccountService utbas = new UserToBankAccountService();
		Client c = cs.getClientById(ua.getClientId());
		
		// get logged in user's account(s)
		List<BankAccount> userBankAccounts = null;
		
		while (stayLoggedIn) {
			
			userBankAccounts = bas.getAccounts(ua.getId());
			
			int count = 0;
			int success = 0;
			int accountNumber = 0;

		
			System.out.println("________________________________________________________________________________________________________________________");
			System.out.println("#                                                                                                                      #");
			System.out.println("#                                               CONSOLE BANK APPLICATION                                               #");
			System.out.println("#----------------------------------------------------------------------------------------------------------------------#");

			
			for (BankAccount ba: userBankAccounts) {
				System.out.println(ba);
			}
			
			System.out.println("#----------------------------------------------------------------------------------------------------------------------#");			
			System.out.println("#  TYPE: (1) Deposit Money | (2) Withdraw Money | (3) Open CHECKINGS account | (4) Open SAVINGS Account | (5) Log Out  #");
			System.out.println("#----------------------------------------------------------------------------------------------------------------------#");			
			System.out.println(">");
			System.out.println("> Welcome " + c.getFirstName() + " " + c.getLastName() + ".");
			System.out.println(">");
			System.out.println("> What would you like to do? (see options above)");
			System.out.println(">");
			System.out.print("> INPUT: ");
			
			String userInput = sc.nextLine();
			
			switch (userInput) {
			case "1":
				// deposit
				System.out.println(">");
				System.out.println("> Makeing a deposit to your account.....................................................................................");
				System.out.println(">");
				System.out.println("> Which account would you like to make a deposit to:");
				
				for (BankAccount ba: userBankAccounts) {
					System.out.println("> " + count + " - " + ba);
					count++;
				}
				
				System.out.println(">");
				System.out.print("> Input the number left of the account: ");
				
				accountNumber = sc.nextInt();
				
				if(accountNumber < 0 || accountNumber > (userBankAccounts.size() - 1)) {
					System.out.println(">");
					System.out.println(">");
					System.out.println("> Invalid input, aborting process. PRESS ENTER TO CONTINUE..............................................................");
					System.out.println(">");
					System.out.println(">");
					System.out.println("#----------------------------------------------------------------------------------------------------------------------#");
					sc.nextLine();
					sc.nextLine();
					break;
				}
				
				System.out.println(">");
				System.out.println("> Your current balance is: " + userBankAccounts.get(accountNumber).getBalance());
				
				System.out.print("> Enter amount of deposit: ");
				
				double depositAmount = sc.nextDouble();
				
				// validate here
				
				// deposit amount into account
				success = bas.depositAmount(userBankAccounts.get(accountNumber), depositAmount);
				
				if (success == 1) {
					System.out.println(">");
					System.out.println("> Success. Deposit made to account. PRESS ENTER TO CONTINUE.............................................................");
					System.out.println(">");
					System.out.println(">");
					System.out.println("#----------------------------------------------------------------------------------------------------------------------#");
					sc.nextLine();
				}
				
				sc.nextLine();

				break;
			case "2":
				// withdraw amount from account
				System.out.println(">");
				System.out.println("> Withdrawing from your account.....................................................................................");
				System.out.println(">");
				System.out.println("> Which account would you like to withdraw from:");
				
				for (BankAccount ba: userBankAccounts) {
					System.out.println("> " + count + " - " + ba);
					count++;
				}
				
				System.out.println(">");
				System.out.print("> Input the number left of the account: ");
				
				accountNumber = sc.nextInt();
					
				if(accountNumber < 0 || accountNumber > (userBankAccounts.size() - 1)) {
					System.out.println(">");
					System.out.println(">");
					System.out.println("> Invalid input, aborting process. PRESS ENTER TO CONTINUE..............................................................");
					System.out.println(">");
					System.out.println(">");
					System.out.println("#----------------------------------------------------------------------------------------------------------------------#");
					sc.nextLine();
					sc.nextLine();
					break;
				}
					
				System.out.println(">");
				System.out.println("> Your current balance is: " + userBankAccounts.get(accountNumber).getBalance());
				
				System.out.print("> Enter amount to withdraw: ");
				
				double withdrawAmount = sc.nextDouble();
				
				// validate here
				
				// withdraw amount from account
				success = bas.withdrawAmount(userBankAccounts.get(accountNumber), withdrawAmount);
				
				if (success == 1) {
					System.out.println(">");
					System.out.println("> Success. Withdraw made from account. PRESS ENTER TO CONTINUE..........................................................");
					System.out.println(">");
					System.out.println(">");
					System.out.println("#----------------------------------------------------------------------------------------------------------------------#");
					sc.nextLine();
				} else if (success == 3) {
					System.out.println(">");
					System.out.println("> Insufficient funds. PRESS ENTER TO CONTINUE...........................................................................");
					System.out.println(">");
					System.out.println(">");
					System.out.println("#----------------------------------------------------------------------------------------------------------------------#");
					sc.nextLine();
				}
				
				sc.nextLine();

				break;
			case "3":
				// CHECKINGS
				BankAccount cba = new BankAccount(1 , 0);
				bas.createBankAccount(cba);
				utbas.establishAssociation(ua.getId(), cba.getId());
				
				System.out.println(">");
				System.out.println("> Success. Created the following CHECKING account:");
				System.out.println(">");
				System.out.println("> " + cba);
				System.out.println(">");
				System.out.println("> PRESS ENTER TO CONTINUE...............................................................................................");
				System.out.println(">");
				System.out.println("#----------------------------------------------------------------------------------------------------------------------#");
				sc.nextLine();

				break;
			case "4":
				// SAVINGS
				BankAccount sba = new BankAccount(2 , 0);
				bas.createBankAccount(sba);
				utbas.establishAssociation(ua.getId(), sba.getId());
				
				System.out.println(">");
				System.out.println("> Success. Created the following SAVINGS account:");
				System.out.println(">");
				System.out.println("> " + sba);
				System.out.println(">");
				System.out.println("> PRESS ENTER TO CONTINUE...............................................................................................");
				System.out.println(">");
				System.out.println("#----------------------------------------------------------------------------------------------------------------------#");
				sc.nextLine();
				
				break;
			case "5":
				stayLoggedIn = false;
				System.out.println(">");
				System.out.println(">");
				System.out.println("> Logging out..........................................................................................................#");
				System.out.println(">");
				System.out.println(">");
				System.out.println("#----------------------------------------------------------------------------------------------------------------------#");
				break;
			default:
				System.out.println(">");
				System.out.println(">");
				System.out.println("> Invalid input, please try again. PRESS ENTER TO CONTINUE...");
				System.out.println(">");
				System.out.println(">");
				System.out.println("#----------------------------------------------------------------------------------------------------------------------#");
				sc.nextLine();
			}
		}
	}

}
