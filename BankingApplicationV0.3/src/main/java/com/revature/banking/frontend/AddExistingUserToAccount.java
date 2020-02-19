package com.revature.banking.frontend;

import com.revature.banking.services.BankAccountService;
import com.revature.banking.services.UserService;
import com.revature.banking.services.models.BankAccount;

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
		// TODO Auto-generated method stub
		return 0;
	}

}
