package com.revature.banking.application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.banking.dao.BankAccountDao;
import com.revature.banking.dao.BankAccountDaoImpl;
import com.revature.banking.dao.UserDao;
import com.revature.banking.dao.UserDaoImpl;
import com.revature.banking.frontend.BankInteraction;
import com.revature.banking.frontend.CLI;
import com.revature.banking.frontend.ConsoleCLI;
import com.revature.banking.frontend.CreateNewUser;
import com.revature.banking.services.BankAccountService;
import com.revature.banking.services.UserService;

public class BankApplication {

	public static void main(String[] args) {
		final BankAccountService bas = new BankAccountService();
		final UserService us = new UserService();
		final UserDao ud = new UserDaoImpl();
		final BankAccountDao bad = new BankAccountDaoImpl();
		final CLI console = new ConsoleCLI();
		final String dbKey = System.getenv("DB_KEY");

		System.out.println("dbKey: " + dbKey);

		bas.setKey(dbKey);
		bas.setDao(bad);
		us.setKey(dbKey);
		us.setDao(ud);
		us.setBankAccountService(bas);
		ud.setBankAccountDao(bad);
		bad.setBankAccountService(bas);

		final List<BankInteraction> interactions = new ArrayList<>();
		interactions.add(new CreateNewUser());

		try {
			interactions.get(0).interact(console, bas, us);
		} catch (IOException e) {
			Logger.getRootLogger().error(e.getStackTrace());
		}
	}
}
