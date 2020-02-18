package com.revature.banking.frontend;

import com.revature.banking.services.BankAccountService;
import com.revature.banking.services.UserService;
import com.revature.banking.services.models.BankAccount;

public class AddNewUserToAccount extends AccountInteraction {
	public static final String TITLE = "Add a new user to this account.";

	private BankAccount account;

	protected AddNewUserToAccount(CLI io, UserService uService, BankAccountService baService) {
		super(io, uService, baService);
		this.setTitle(TITLE);
	}

	public void setAccount(final BankAccount account) {
		this.account = account;
	}

	@Override
	public int realInteraction() {
//		final User newUser = 
		return FAILURE;
	}

}
