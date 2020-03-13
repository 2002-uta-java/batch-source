package com.revature.banking.frontend;

import com.revature.banking.services.BankAccountService;
import com.revature.banking.services.UserService;
import com.revature.banking.services.models.BankAccount;

public class OpenNewAccount extends AccountInteraction {
	public static final String TITLE = "Open a New Account";

	protected OpenNewAccount(CLI io, UserService uService, BankAccountService baService) {
		super(io, uService, baService);
		this.setTitle(TITLE);
	}

	@Override
	public int realInteraction() {
		io.clearScreen();
		io.println("Are you sure you want to create a new account (that's a lot of responsibility)? y/n");
		if (this.readYesOrNo() != YES)
			return FAILURE;

		// else open a new account
		io.clearScreen();
		io.print("Opening a new account...");
		io.working();
		final BankAccount newAccount = baService.addAccountToUser(user);
		io.done();
		if (newAccount != null) {
			io.println('\t' + newAccount.printFullAccount());
			return SUCCESS;
		} else {
			io.println("There was an error and your account was not created.");
			return FAILURE;
		}
	}

}
