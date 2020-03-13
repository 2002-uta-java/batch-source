package com.revature.testing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.revature.banking.dao.BankAccountDao;
import com.revature.banking.dao.UserDao;
import com.revature.banking.dao.postgres.BankAccountDaoPostgres;
import com.revature.banking.dao.postgres.UserDaoPostgres;
import com.revature.banking.services.BankAccountService;
import com.revature.banking.services.UserService;
import com.revature.banking.services.models.BankAccount;
import com.revature.banking.services.models.User;
import com.revature.banking.services.security.SecurityService;

public class PopulateDatabase {
	public static final String USERS_FILE = "users.txt";
	public static final String USER_ACCOUNTS_FILE = "user_accounts.txt";
	private static final UserService uService = new UserService();
	private static final BankAccountService baService = new BankAccountService();
	private static final UserDao uDao = new UserDaoPostgres();
	private static final BankAccountDao baDao = new BankAccountDaoPostgres();
	private static final SecurityService secService = new SecurityService();

	private static final List<TestUser> users = new ArrayList<TestUser>();
	private static final int MULTIPLIER = 2;
	private static final Random rand = new Random();

	public static final DecimalFormat df = new DecimalFormat("####.##");

	public static void main(String[] args) {
		setupBankAccountService();
		setupUserService();
		setupSecurityService();
		setupUserDao();

		createUsers();
		addRandomAccounts();
		addRandomAmounts();
		printResults();
	}

	private static void addRandomAmounts() {
		for (final TestUser user : users) {
			for (final BankAccount account : user.getAccounts()) {
				final int intAmount = rand.nextInt(1000000);
				final double amount = Double.parseDouble(df.format(intAmount / 100.0));
				baService.addFundsToAccountNoLimit(account, amount);
			}
		}
	}

	private static void printResults() {
		try (final PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(USER_ACCOUNTS_FILE)))) {
			for (final TestUser user : users) {
				user.write(pw);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void addRandomAccounts() {
		final int numAccounts = users.size() * MULTIPLIER;

		for (int i = 0; i < numAccounts; ++i) {
			// get a random user
			final TestUser user = users.get(rand.nextInt(users.size()));
			System.out.println("Adding account to " + user);
			// add an account to this user
			final BankAccount account = baService.createNewAccount();
			baService.addUserToAccount(user.getUserKey(), account);
			user.addAccount(account);
		}
	}

	private static void setupUserDao() {
		uDao.setBankAccountDao(baDao);
	}

	private static void setupSecurityService() {
		secService.setKey(System.getenv("DB_KEY"));
	}

	private static void setupUserService() {
		uService.setSecurityService(secService);
		uService.setBankAccountService(baService);
		uService.setUserDao(uDao);
//		uService.setBankAccountDao(baDao);
	}

	private static void setupBankAccountService() {
		baService.setSecurityService(secService);
		baService.setBankAccountDao(baDao);
//		baService.setUserDao(uDao);// TODO I don't think I need this
	}

	private static void createUsers() {
		try (final BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {
			String line;

			while ((line = br.readLine()) != null) {
				final User user = readUser(line);
				uService.createUser(user);
				System.out.println("Creating user: " + user);
				final BankAccount account = baService.createNewAccount();
				baService.addUserToAccount(user.getUserKey(), account);

				final TestUser newUser = new TestUser(user);
				newUser.addAccount(account);
				users.add(newUser);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static User readUser(String line) {
		final String[] inputs = line.split(" ");
		final User newUser = new User();
		newUser.setFirstName(inputs[0]);
		newUser.setLastName(inputs[1]);
		newUser.setTaxID(inputs[2]);
		newUser.setUserName(inputs[3]);
		newUser.setPassword(inputs[4]);

		return newUser;
	}
}
