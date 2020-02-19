package com.revature.banking.frontend;

import java.util.List;

import com.revature.banking.services.BankAccountService;
import com.revature.banking.services.UserService;
import com.revature.banking.services.models.BankAccount;

public class AddUserToAccount extends AccountInteraction {
	public static final String TITLE = "Add User To An Account";
	public static final String PROMPT = "Which account would you like to add a user to?";

	protected AddUserToAccount(CLI io, UserService uService, BankAccountService baService) {
		super(io, uService, baService);
		this.setTitle(TITLE);
	}

	@Override
	public int realInteraction() {
		final List<BankAccount> accounts = baService.getAccounts(user);
		io.clearScreen();
		final int option = this.chooseAccount(PROMPT, accounts);

		switch (option) {
		case EXIT:
		case LOGOUT:
		case FAILURE:
			return option;
		}
		// now we have a valid option.

		final BankAccount account = accounts.get(option - 1);
		while (true) {
			io.clearScreen();
			io.println("Adding a user to account:");
			io.println('\t' + account.printAccountBalanceHideAccountno());
			io.println("Type the username of the user you would like to add.")
		}
	}

}
