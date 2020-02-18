package com.revature.banking.frontend;

import com.revature.banking.services.BankAccountService;
import com.revature.banking.services.UserService;
import com.revature.banking.services.models.User;

public abstract class AccountInteraction extends BankInteraction {

	protected User user;

	protected AccountInteraction(CLI io, UserService uService, BankAccountService baService) {
		super(io, uService, baService);
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public void removeUser() {
		user = null;
	}
}
