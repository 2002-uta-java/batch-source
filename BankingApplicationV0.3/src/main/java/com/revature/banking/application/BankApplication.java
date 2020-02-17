package com.revature.banking.application;

import java.util.ArrayList;
import java.util.List;

import com.revature.banking.dao.BankAccountDao;
import com.revature.banking.dao.UserDao;
import com.revature.banking.dao.postgres.BankAccountDaoPostgres;
import com.revature.banking.dao.postgres.UserDaoPostgres;
import com.revature.banking.frontend.BankInteraction;
import com.revature.banking.frontend.CLI;
import com.revature.banking.frontend.ConsoleCLI;
import com.revature.banking.frontend.CreateNewUser;
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

	private final List<BankInteraction> logIn;
	private final List<BankInteraction> userActions;

	public BankApplication() {
		console = new ConsoleCLI();
		uService = new UserService();
		baService = new BankAccountService();
		uDao = new UserDaoPostgres();
		baDao = new BankAccountDaoPostgres();
		secService = new SecurityService();

		this.logIn = new ArrayList<>();
		this.userActions = new ArrayList<>();

		setupInteractions();
		setupBankAccountService();
		setupUserService();
		setSecurityService();
	}

	private void setSecurityService() {
		secService.setKey(System.getenv("DB_KEY"));
	}

	private void setupUserService() {
		uService.setSecurityService(secService);
		uService.setBankAccountDao(baDao);
	}

	private void setupBankAccountService() {
		baService.setSecurityService(secService);
		baService.setUserDao(uDao);
	}

	private void setupInteractions() {
		final BankInteraction createNewUser = new CreateNewUser();
		createNewUser.setCLI(console);
		logIn.add(createNewUser);
	}

	public void run() {
		// TODO need to run program
	}

	public static void main(String[] args) {

	}
}
