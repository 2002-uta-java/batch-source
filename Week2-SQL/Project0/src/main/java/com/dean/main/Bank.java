package com.dean.main;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.dean.dao.AccountDaoImpl;
import com.dean.dao.ClientDaoImpl;
import com.dean.dao.TransactionDaoImpl;
import com.dean.models.Account;
import com.dean.models.Client;
import com.dean.models.Transaction;
import com.dean.util.InputValidator;

public class Bank {

	private static Bank singleton = null;
	static final Logger logger = Logger.getRootLogger();
	public static final Scanner sc = new Scanner(System.in);
	private Client activeClient = null;

	private Bank() {
		super();
	}
	
	public static Bank getBank() {
		
		if(singleton == null) {
			singleton = new Bank();
		}
		
		return singleton;
	}
	
	public void start() {
		logger.info("Welcome to Dean's Money Management Console!");
		listCommands();
		String userInput = "";
		
		while(userInput != "exit") {
			
			userInput = sc.nextLine().toLowerCase();
			
			completeAction(userInput);
			listCommands();
		}
		
		
	}
	
	
	private void listCommands() {
		ClientDaoImpl cdi = new ClientDaoImpl();
		if(activeClient == null) {
			logger.info("Please choose an option: ");
			logger.info("1 - register");
			logger.info("2 - login");
			logger.info("3 - exit");
		} else if (cdi.clientHasAccount(activeClient.getUsername())) {
			logger.info("Please choose an option: ");
			logger.info("1 - deposit");
			logger.info("2 - withdraw");
			logger.info("3 - view");
			logger.info("4 - transfer");
			logger.info("5 - history");
			logger.info("6 - logout");
			logger.info("7 - exit");
		} else {
			logger.info("Please choose an option: ");
			logger.info("8 - create an account");
			logger.info("6 - logout");
			logger.info("7 - exit");
		}
		
		logger.info("");
		
	}
	
	private void completeAction(String action) {
		if(activeClient == null) {
			noActiveClientActions(action);
		} else {
			activeClientActions(action);
		}
	}
	
	private void noActiveClientActions(String action) {
		switch(action) {
			case "1":
				register();
				break;
			case "2":
				login();
				break;
			case "3":
				exitProgram();
				break;
			default:
				break;
		}
	}
	
	private void activeClientActions(String action) {
		ClientDaoImpl cdi = new ClientDaoImpl();
		switch(action) {
			case "8":
				createAccount();
				break;
			case "6":
				logout();
				break;
			case "1":
				if(!cdi.clientHasAccount(activeClient.getUsername())) {
					logger.info("You have no existing accounts.");
					break;
				}
				deposit();
				break;
			case "2":
				if(!cdi.clientHasAccount(activeClient.getUsername())) {
					logger.info("You have no existing accounts.");
					break;
				}
				withdraw();
				break;
			case "3":
				if(!cdi.clientHasAccount(activeClient.getUsername())) {
					logger.info("You have no existing accounts.");
					break;
				}
				view();
				break;
			case "5":
				if(!cdi.clientHasAccount(activeClient.getUsername())) {
					logger.info("You have no existing accounts.");
					break;
				}
				transactionHistory();
				break;
			case "transfer": 
				if(!cdi.clientHasAccount(activeClient.getUsername())) {
					logger.info("You have no existing accounts.");
					break;
				}
				transfer();
				break;
			case "7":
				exitProgram();
				break;
			default: 
				break;
		}
	}
	
	private void register() {
		ClientDaoImpl cdi = new ClientDaoImpl();
		Client client = new Client();
		String username = InputValidator.getAUsername();
		client.setUsername(username);
		if(!cdi.isUsernameUnique(client)) {
			logger.info("That username had already been taken.");
			return;
		}
		String password = InputValidator.getAPassword();
		client.setPassword(password);
		
		cdi.createClient(client);
	}
	
	private void login() {
		
		ClientDaoImpl cdi = new ClientDaoImpl();
		logger.info("Enter your username: ");
		String name = sc.nextLine();
		Client client = cdi.getClientByUsername(name);
		if(client == null) {
			logger.info("User could not be found.");
			return;
		}
		
		logger.info("");
		logger.info("Enter the password for " + client.getUsername());
		String pw = sc.nextLine();
		
		if(client.getPassword().equals(pw)) {
			activeClient = client;
		} else {
			logger.info("Password is incorrect.");
		}
		
	}
	
	private void createAccount() {
		if(activeClient == null) {
			logger.info("No active client");
			return;
		}
		
		AccountDaoImpl adi = new AccountDaoImpl();
		ClientDaoImpl cdi = new ClientDaoImpl();
		String c = activeClient.getUsername();
		adi.createAccount(cdi.getUserIdByName(c));

	}
	
	private void deposit() {
		AccountDaoImpl adi = new AccountDaoImpl();
		Account acc = adi.getAccountByClientId(activeClient.getId());
		
		adi.deposit(acc.getId());	
	}
	
	private void withdraw() {
		AccountDaoImpl adi = new AccountDaoImpl();
		Account acc = adi.getAccountByClientId(activeClient.getId());
		
		adi.withdraw(acc.getId());	
	}
	
	private void transfer() {
		AccountDaoImpl adi = new AccountDaoImpl();
		adi.transfer(activeClient.getId());
	}
	
	private void transactionHistory() {
		TransactionDaoImpl tdi = new TransactionDaoImpl();
		List<Transaction> transactions = tdi.getTransactionsByClientId(activeClient.getId());
		tdi.logHistory(transactions);
	}
	
	private void view() {
		AccountDaoImpl adi = new AccountDaoImpl();
		logger.info("You have $"+ InputValidator.formatDecimals(adi.getBalanceByClientId(activeClient.getId())));
	}
	
	private void exitProgram() {
		logger.info("Leaving the Dean's Money Management Console.");
		activeClient = null;
		System.exit(0);
		sc.close();
	}

	private void logout() {
		logger.info("Logging out of Dean's Money Management Console.");
		activeClient = null;
	}
	
}
