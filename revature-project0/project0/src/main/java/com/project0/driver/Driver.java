package com.project0.driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.project0.models.Account;
import com.project0.models.Customer;
import com.project0.services.AccountService;
import com.project0.services.CustomerService;
import com.project0.util.ConnectionUtil;

public class Driver {

	public static boolean isValidEmail(String email) 
    { 
		// email regex
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 
	
	public static void main(String[] args) {
		
		// Driver ======================================================
		// =============================================================
		
		// boolean flag for loop menu of login and create customer
		boolean begin = true;
		boolean logIn = false;
		boolean createFlag = true;
		
		// switch case for log in
		int beginMenu = 0;
		int menu = 0;
		int createAccType=0;
		
		//variables
		int customerId = 0;
		int accountId = 0;
		double accountBalance = 0;
		double depositAmount = 0;
		double withdrawAmount = 0;
		
		String accountType = "";
		String identifier = "";
		String password= "";
		
		// variable check for user input
		String str = "";
		
		// Customer/account service
		CustomerService cService = new CustomerService();
		AccountService aService = new AccountService();
		
		// Customer  and account 
		Customer c = new Customer();
		Account a = new Account();
		
		
		// scanner object, get user input
		Scanner sc = new Scanner(System.in);
		
		//=================================================================================
		// Menu
		while (begin) {
			// prompt user for login/create customer account
			System.out.println("================================================================");
			System.out.println("Welcome to the Bank App!");
			System.out.println();
			System.out.println("1. Log In");
			System.out.println("2. Create New Account");
			System.out.println("3. Quit");
			str = sc.next();
			// take only digit
			if (str.matches("[\\d]")) {
				// convert str to int
				if (str.length() == 1){
					beginMenu = Integer.parseInt(str);
				}
				else {
					System.out.println("Please input single digit number!");
					continue;
				}
			}
			else {
				System.out.println("Please input single number!");
				continue;
			}
			System.out.println("================================================================");
			System.out.println();
			
			// validate user input for beginMenu
			if (beginMenu == 1 || beginMenu == 2 || beginMenu == 3) {
				// do nothing
			}
			else {
				System.out.println("Wrong Input: Must be numbers from 1 to 3.");
			}
			
			// Start Menu: log in or create
			switch (beginMenu) {
				case 1:
					System.out.print("User Name or Email: ");
					identifier = sc.next();
					System.out.print("Password: ");
					password = sc.next();
					c = cService.credentialValidation(identifier, password);
					if (c.getId() == 0) {
						System.out.println("Incorrect User Name / Email or Password!");
						begin = true;
					} else {
						System.out.println();
						System.out.println("Welcome " + c.getUserName() +" to Bank App!");
						begin = false;
						logIn = true;
					}
					break;
				case 2:
					createFlag = true;
					while (createFlag) {
						System.out.println();
						System.out.println("Create New Customer Account");
						System.out.print("Enter your email: ");
						String email = sc.next();
						// check email
						if (!isValidEmail(email)) {
							System.out.println("Invalid Email!");
							continue;
						}
						System.out.print("Enter your user name: ");
						String userName = sc.next();
						System.out.print("Enter your password: ");
						password = sc.next();
						// store into customer object
						c.setEmail(email);
						c.setUserName(userName);
						c.setPassword(password);
						cService.createCustomer(c);
						
						// create acc after creating customer account
						System.out.println();
						System.out.println("================================================================");
						System.out.println("Creat New Bank Account");
						System.out.println("================================================================");
						
						// create bank account
						a.setCustId(c.getId());
						a.setAccType("Checking");
						aService.createAccount(a);
						createFlag = false;
						logIn = true;
					}
					// end of create customer account
					break;
				case 3:
					System.out.println("Exiting Bank App!");
					begin = false;
			}
		}
		
		// for Menu option after log in
		while (logIn) {
			// prompt Menu
			System.out.println();
			System.out.println("================================================================");
			System.out.println("1. Deposit");
			System.out.println("2. Withdraw");
			System.out.println("3. View Balance");
			System.out.println("4. Log Out");
			System.out.println("================================================================");
			// check for input from user for menu
			str = sc.next();
			if (str.matches("[\\d]")) {
				// convert str to int
				if (str.length() == 1){
					menu = Integer.parseInt(str);
				}
				else {
					System.out.println("Please input single digit number!");
					continue;
				}
			}
			else {
				System.out.println("Please input single number!");
				continue;
			}
			
			// only 4 input
			// validate user input for beginMenu
			if (menu == 1 || menu == 2 || menu == 3 || menu == 4) {
				// do nothing
			}
			else {
				System.out.println("Wrong Input: Must be numbers from 1 to 4.");
			}
			
			switch (menu) {
			case 1:
				// deposit
				a = aService.getAccountByCustId(c.getId());
				System.out.print("Deposit Amount: ");
				depositAmount = sc.nextDouble();
				// checking for negative amount
				if (depositAmount < 0) {
					System.out.println("Cannot deposit negative amount!");
				}
				else {
					accountBalance = a.getBalance() + depositAmount;
					a.setBalance(accountBalance);
					aService.updateAccount(a);
					System.out.println("New Account Balance: "+ a.getBalance());
				}
				
				break;
			case 2:// withdraw
				a = aService.getAccountByCustId(c.getId());
				System.out.print("Withdraw Amount: ");
				withdrawAmount = sc.nextDouble();
				accountBalance = a.getBalance() - withdrawAmount;
				if (accountBalance < 0) {
					System.out.println("Cannot withdraw more money than current balance!");
				}
				else {
					a.setBalance(accountBalance);
					aService.updateAccount(a);
					System.out.println("New Account Balance: "+ a.getBalance());
				}
				break;
			case 3:
				// view balance
				System.out.println("View Balance");
				a = aService.getAccountByCustId(c.getId());
				System.out.println("Current Balance: " + a.getBalance());
				break;
			case 4:
				// log out
				System.out.println("Log Out Account");
				logIn = false;
			}
		}
		
	}

}
