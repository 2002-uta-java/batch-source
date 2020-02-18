package com.revature.banking.frontend;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.revature.banking.services.BankAccountService;
import com.revature.banking.services.UserService;
import com.revature.banking.services.models.BankAccount;
import com.revature.banking.services.models.User;

public class AddExistingUserToAccount extends AccountInteraction {
	public static final String TITLE = "Add an Existing User";

	private BankAccount account;

	protected AddExistingUserToAccount(CLI io, UserService uService, BankAccountService baService) {
		super(io, uService, baService);
		this.setTitle(TITLE);
	}

	public void setAccount(final BankAccount account) {
		this.account = account;
	}

	@Override
	public int realInteraction() {
		while (true) {
			io.clearScreen();
			io.println("You will need to have the user login to give them access to this account.");
			io.println("username");
			try {
				final String userName = io.readLine();
				io.println("password:");
				final String password = io.readPassword();
				final User newUser = uService.login(userName, password);

				if (newUser == null) {
					io.println("Invalid username/password.");
					if (!retry())
						return FAILURE;
					// else try again, continue
					continue;
				}

				// else try to add this user to the account
				if (baService.addUserToAccount(newUser.getUserKey(), account)) {
					io.clearScreen();
					io.println(newUser.getUserName() + " was successfully added to the following acount:");
					io.println('\t' + account.printAccountBalanceHideAccountno());
					return SUCCESS;
				} else {
					io.println("There was an error and " + newUser.getUserName() + " was not added.");
					return FAILURE;
				}
			} catch (IOException ioe) {
				Logger.getRootLogger().error("IOException: " + ioe.getMessage());
			}
		}
	}

}
