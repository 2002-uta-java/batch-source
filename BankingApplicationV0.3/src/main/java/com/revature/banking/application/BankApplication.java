package com.revature.banking.application;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.revature.banking.dao.BankAccountDao;
import com.revature.banking.dao.UserDao;
import com.revature.banking.dao.postgres.BankAccountDaoPostgres;
import com.revature.banking.dao.postgres.UserDaoPostgres;
import com.revature.banking.frontend.BankInteraction;
import com.revature.banking.frontend.CLI;
import com.revature.banking.frontend.ConsoleCLI;
import com.revature.banking.frontend.MainMenu;
import com.revature.banking.services.BankAccountService;
import com.revature.banking.services.UserService;
import com.revature.banking.services.security.SecurityService;

public class BankApplication {
	private final CLI console;
	private final UserService uService;
	private final BankAccountService baService;
	private final UserDao uDao;
	private final BankAccountDao baDao;
	private final SecurityService secService;

	private final BankInteraction mainMenu;

	public BankApplication() {
		console = new ConsoleCLI();
		uService = new UserService();
		baService = new BankAccountService();
		uDao = new UserDaoPostgres();
		baDao = new BankAccountDaoPostgres();
		secService = new SecurityService();
		this.mainMenu = new MainMenu(console, uService, baService);

		setupInteractions();
		setupBankAccountService();
		setupUserService();
		setupSecurityService();
		setupUserDao();
		setupBankAccountDao();
	}

	private void setupBankAccountDao() {
		// TODO I don't think this needs any setup

	}

	private void setupUserDao() {
		uDao.setBankAccountDao(baDao);
	}

	private void setupSecurityService() {
		secService.setKey(System.getenv("DB_KEY"));
	}

	private void setupUserService() {
		uService.setSecurityService(secService);
		uService.setBankAccountService(baService);
		uService.setUserDao(uDao);
//		uService.setBankAccountDao(baDao);
	}

	private void setupBankAccountService() {
		baService.setSecurityService(secService);
		baService.setBankAccountDao(baDao);
//		baService.setUserDao(uDao);// TODO I don't think I need this
	}

	private void setupInteractions() {
	}

	public void run() {
		try {
			mainMenu.interact();
		} catch (IOException e) {
			Logger.getRootLogger().error("We got an IOException: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		final BankApplication application = new BankApplication();

		application.run();
	}
}
