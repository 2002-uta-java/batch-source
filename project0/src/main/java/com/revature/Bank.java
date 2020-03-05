package com.revature;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.models.BankAccount;
import com.revature.models.UserAccount;
import com.revature.services.BankAccountService;
import com.revature.services.UserAccountService;

public class Bank {
	private static Scanner sc = new Scanner(System.in);
	private static UserAccountService uas = new UserAccountService();
	private static BankAccountService bas = new BankAccountService();
	private static Logger log = Logger.getRootLogger();

	public void startCustomerOptions() {
		listOptionsHelper("customer");
		String input = sc.nextLine();
		System.out.println();
		
		while(input != null) {
			if("1".contentEquals(input)) {
				// LOG IN to a USER, MVP
				log.info("Please enter your USERNAME/EMAIL: ");
				String login = sc.nextLine();
				// LOG IN with a PASSWORD, MVP
				log.info("Please enter your PASSWORD: ");
				String password = sc.nextLine();
				log.info("\n");

				// find valid login
				List<UserAccount> users = uas.getAllUserAccounts();
				UserAccount ua;
				boolean validUsername = false;
				boolean validEmail = false;
				for(UserAccount u : users) {
					if(u.getUsername().equals(login) && u.getPassword().equals(password) && !"".equals(login)) {
						validUsername = true;
						break;
					} 
					else if(u.getEmail().equals(login) && u.getPassword().equals(password) && !"".equals(login)) {
						validEmail = true;
						break;
					} 
				}
				
				if(validUsername) {
					ua = uas.getUserAccountByUsername(login);
					log.info("Hello, " + login + "\n");
					getUserOptions(ua);
				}
				else if(validEmail) {
					ua = uas.getUserAccountByEmail(login);
					log.info("Hello, " + login + "\n");
					getUserOptions(ua);
				}
				else {					
					log.warn("Incorrect LOGIN combination\n\n");
				}
			} 
			else if("2".equals(input)) {
				// CREATE a new USER ACCOUNT, MVP
				log.info("Create your new USER ACCOUNT\n");
				log.info("Enter your desired USERNAME: ");
				String username = sc.nextLine();
				log.info("Enter your desired EMAIL: ");
				String email = sc.nextLine();

				if("".equals(username) && "".equals(email)) {
					log.warn("Invalid USERNAME and EMAIL. USER ACCOUNTs must have at least one USERNAME or EMAIL associated with it\n\n");
				}
				else {
					// search existing users
					List<UserAccount> users = uas.getAllUserAccounts();
					boolean invalidUser = false;
					boolean invalidEmail = false;
					for(UserAccount ua : users) {
						if(ua.getUsername().equals(username) && !"".equals(username)) {
							invalidUser = true;
							break;
						}
						else if(ua.getEmail().equals(email) && !"".equals(email)) {
							invalidEmail = true;
							break;
						}
					}

					if(invalidUser) {						
						log.warn("Username '" + username + "' is already taken.\n\n");
					}
					else if(invalidEmail) {
						log.warn("Email '" + email + "' is already used by another account.\n\n");
					}
					else {
						log.info("Enter your desired PASSWORD: ");
						String pw = sc.nextLine();
						
						if("".equals(pw)) {
							log.info("Invalid password. Every account requires a password.\n");
						}
						else {
							UserAccount ua = new UserAccount(username, email, pw);
							uas.createUserAccount(ua);
							log.info("Your account has been created. Please LOG IN to continue to banking services.\n\n");														
						}
					}
				} 
			}
			else if("3".equals(input)) {
				log.info("Thank you for using our banking services.\n\n");
				break;
			} 
			else {
				log.warn("Invalid customer option.\n\n");
			}
			listOptionsHelper("customer");
			input = sc.nextLine();		
			log.info("\n");
		}
	}
	
	public void getUserOptions(UserAccount ua) {

		//------ADDITIONAL---------
		// TRANSFER money
		// VIEW TRANSACTION HISTORY
		listOptionsHelper("user");
		String input = sc.nextLine();
		log.info("\n");
		
		while(input != null) {			
			// CREATE a new BANKACCOUNT
			if("1".contentEquals(input)) {
				log.info("Create your new BANK ACCOUNT\n");
				log.info("Please input the ACCOUNT TYPE you desire ('CHECKING'/'SAVINGS'): ");
				input = sc.nextLine().toLowerCase();
				
				if("checking".equals(input) || "savings".equals(input)) {
					BankAccount ba = new BankAccount(ua, input);
					BankAccount createdBankAccount = bas.createBankAccount(ba);
					log.info("Your new " + input.toUpperCase() + " account, account number " + createdBankAccount.getBaid() + ", has been created.\n\n");
				}
				else {
					log.warn("Invalid ACCOUNT TYPE. Please select valid type 'CHECKING' or 'SAVINGS'\n\n");
				}
			}
			else if("2".equals(input)) {
				// DEPOSIT money, MVP
				List<BankAccount> bankAccounts = bas.getAllBankAccounts(ua);
				List<Integer> baids = new ArrayList<>();
				if(bankAccounts.size() == 0) {
					log.warn("You do not have any BANK ACCOUNTs to DEPOSIT to\n\n");
				}
				else {
					log.info("You have the following BANK ACCOUNTs...\n");
					for(BankAccount ba : bankAccounts) {
						log.info(String.format("Account Number: %d | Account Type: %s | Balance: $%.2f%n", ba.getBaid(), ba.getAccountType(), ba.getBalance()));
						baids.add(ba.getBaid());
					}
					log.info("Please enter the ACCOUNT NUMBER of the account you wish to DEPOSIT money to: ");
					input = sc.nextLine();
					if(input.matches("\\d+")) {
						int baid = Integer.parseInt(input);
						if(baids.contains(baid)) {
							log.info("Please enter how much money you would like to DEPOSIT: ");
							input = sc.nextLine();
							if(input.matches("\\$?+\\d+(.\\d\\d)?+")) {
								if(input.charAt(0) == '$') {
									input = input.substring(1);
								}
								BankAccount ba = bas.getBankAccountById(baid);
								double deposit = Double.parseDouble(input);
								double balance = ba.getBalance();
								if(balance + deposit < 1000000000000.00) {
									ba.setBalance(ba.getBalance()+deposit);
									bas.updateBankAccount(ba);
									log.info(String.format("You have DEPOSITED $%.2f to ACCOUNT NUMBER %d%n", deposit, ba.getBaid()));
									log.info(String.format("Current BALANCE: $%.2f%n%n", ba.getBalance()));									
								}
								else {
									log.warn("Large DEPOSIT error. We are a small bank that can't insure accounts with more than $1,000,000,000,000.00\n\n");
								}
							}
							else {
								log.warn("Invalid input. Cannot DEPOSIT " + input + "\n\n");
							}
						}
						else {
							log.warn("Invalid ACCOUNT NUMBER. Please enter a valid ACCOUNT NUMBER\n\n");
						}
					}
					else {
						log.warn("Invalid ACCOUNT NUMBER. Please enter a valid ACCOUNT NUMBER\n\n");
					}
				}
			}
			else if("3".equals(input)) {
				// WITHDRAW money, MVP
				List<BankAccount> bankAccounts = bas.getAllBankAccounts(ua);
				List<Integer> baids = new ArrayList<>();
				if(bankAccounts.size() == 0) {
					log.warn("You do not have any BANK ACCOUNTs to WITHDRAW from\n\n");
				}
				else {
					log.info("You have the following BANK ACCOUNTs...\n");
					for(BankAccount ba : bankAccounts) {
						log.info(String.format("Account Number: %d | Account Type: %s | Balance: $%.2f%n", ba.getBaid(), ba.getAccountType(), ba.getBalance()));
						baids.add(ba.getBaid());
					}
					log.info("Please enter the ACCOUNT NUMBER of the account you wish to WITHDRAW money from: ");
					input = sc.nextLine();
					if(input.matches("\\d+")) {
						int baid = Integer.parseInt(input);
						if(baids.contains(baid)) {
							log.info("Please enter how much money you would like to WITHDRAW: ");
							input = sc.nextLine();
							if(input.matches("\\$?+\\d+(.\\d\\d)?+")) {
								if(input.charAt(0) == '$') {
									input = input.substring(1);
								}
								BankAccount ba = bas.getBankAccountById(baid);
								double withdrawal = Double.parseDouble(input);
								if(ba.getBalance() >= withdrawal) {
									ba.setBalance(ba.getBalance()-withdrawal);
									bas.updateBankAccount(ba);
									log.info(String.format("You have withdrawn $%.2f from ACCOUNT NUMBER %d%n", withdrawal, ba.getBaid()));
									log.info(String.format("Current BALANCE: $%.2f%n%n", ba.getBalance()));									
								}
								else {
									log.warn("Invalid input. Cannot WITHDRAW more money than you have\n\n");
								}
							}
							else {
								log.warn("Invalid input. Cannot WITHDRAW " + input + "\n\n");
							}
						}
						else {
							log.warn("Invalid ACCOUNT NUMBER. Please enter a valid ACCOUNT NUMBER\n\n");
						}
					}
					else {
						log.warn("Invalid ACCOUNT NUMBER. Please enter a valid ACCOUNT NUMBER\n\n");
					}
				}
			}
			else if("4".equals(input)) {
				// VIEW BALANCE, MVP
				List<BankAccount> bankAccounts = bas.getAllBankAccounts(ua);
				List<Integer> baids = new ArrayList<>();
				if(bankAccounts.size() == 0) {
					log.warn("You do not have any BANK ACCOUNTs\n\n");
				}
				else {
					log.info("You have the following BANK ACCOUNTs...\n");
					for(BankAccount ba : bankAccounts) {
						log.info("Account Number: " + ba.getBaid() + " | Account Type: " + ba.getAccountType() + "\n");
						baids.add(ba.getBaid());
					}
					log.info("Please enter the ACCOUNT NUMBER of the account you wish to check: ");
					input = sc.nextLine();
					if(input.matches("\\d+")) {
						int baid = Integer.parseInt(input);
						if(baids.contains(baid)) {
							BankAccount ba = bas.getBankAccountById(baid);
							log.info(String.format("Current BALANCE: $%.2f%n%n", ba.getBalance()));
						}
						else {
							log.warn("Invalid ACCOUNT NUMBER. Please enter a valid ACCOUNT NUMBER\n\n");
						}
					}
					else {
						log.warn("Invalid ACCOUNT NUMBER. Please enter a valid ACCOUNT NUMBER\n\n");
					}
				}
			}
			else if("5".equals(input)) {
				
			}
			else if("6".equals(input)) {
				
			}
			else if("7".equals(input)) {
				// LOG OUT, MVP
				log.info("Logging out...\n\n");
				break;
			}
			else {
				log.warn("Invalid user option.\n\n");
			}
			listOptionsHelper("user");
			input = sc.nextLine();
			log.info("\n");
		}
	}
	
	public void listOptionsHelper(String s) {
		if("customer".contentEquals(s)) {
			log.info("Enter '1' to LOG IN\n");
			log.info("Enter '2' to CREATE a NEW USER\n");
			log.info("Enter '3' to QUIT\n");
		}
		else if("user".contentEquals(s)) {
			log.info("Enter '1' to CREATE a NEW BANK ACCOUNT\n");
			log.info("Enter '2' to DEPOSIT money\n");
			log.info("Enter '3' to WITHDRAW money\n");
			log.info("Enter '4' to VIEW BALANCE\n");
			log.info("Enter '5' to TRANSFER money\n");
			log.info("Enter '6' to VIEW TRANSACTION HISTORY\n");
			log.info("Enter '7' to LOG OUT\n");
		}
		log.info("Choose an option: ");
	}
}
