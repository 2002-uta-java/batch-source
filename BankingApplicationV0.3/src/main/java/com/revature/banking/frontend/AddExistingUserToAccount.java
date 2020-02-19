package com.revature.banking.frontend;

import java.util.List;

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
	public int interact() {
		while (true) {
			io.clearScreen();
			io.println("You will need to have the user login to give them access to this account.");
			io.println("username:");
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

			// first make sure this user doesn't already have access to this account
			final List<BankAccount> newUserAccounts = baService.getAccounts(newUser);

			for (final BankAccount newUserAccount : newUserAccounts) {
				if (newUserAccount.getAccountKey() == account.getAccountKey()) {
					io.clearScreen();
					io.println(userName + " already has access to this account");
					return FAILURE;
				}
			}

			// can now attempt to add this user to this account

			if (baService.addUserToAccount(newUser.getUserKey(), account)) {
				io.clearScreen();
				io.println(userName + " was successfully added to the following acount:");
				io.println('\t' + account.printAccountBalanceHideAccountno());
				return SUCCESS;
			} else {
				io.println("There was an error and " + userName + " was not added.");
				return FAILURE;
			}
		}
	}

	@Override
	public int realInteraction() {
		// this is bad code but this needs to be an AccountInteraction
		return 0;
	}

}
