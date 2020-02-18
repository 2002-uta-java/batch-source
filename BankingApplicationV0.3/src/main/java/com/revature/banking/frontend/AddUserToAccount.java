package com.revature.banking.frontend;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.banking.services.BankAccountService;
import com.revature.banking.services.UserService;
import com.revature.banking.services.models.BankAccount;

public class AddUserToAccount extends AccountInteraction {
	public static final String TITLE = "Add User To An Account";
	public static final String PROMPT = "Which account would you like to add a user to?";

	private final AddExistingUserToAccount existingUser;
	private final AddNewUserToAccount newUser;

	protected AddUserToAccount(CLI io, UserService uService, BankAccountService baService) {
		super(io, uService, baService);
		this.setTitle(TITLE);
		existingUser = new AddExistingUserToAccount(io, uService, baService);
		newUser = new AddNewUserToAccount(io, uService, baService);

		addMenuOption(existingUser);
		addMenuOption(newUser);
	}

	@Override
	public int realInteraction() {
		final List<BankAccount> accounts = baService.getAccounts(user);
		io.clearScreen();
		int option = this.chooseAccount(PROMPT, accounts);

		switch (option) {
		case EXIT:
		case LOGOUT:
		case FAILURE:
			return option;
		}
		// now we have a valid option.

		final BankAccount account = accounts.get(option - 1);
		existingUser.setAccount(account);
		newUser.setAccount(account);

		option = getMenu();
		switch (option) {
		case EXIT:
		case LOGOUT:
		case FAILURE:
			return option;
		}

		try {
			return interact(option);
		} catch (IOException e) {
			Logger.getRootLogger().error("IOException: " + e.getMessage());
			return FAILURE;
		}
	}

}
