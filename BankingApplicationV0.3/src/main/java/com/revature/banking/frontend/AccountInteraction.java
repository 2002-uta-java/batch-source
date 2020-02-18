package com.revature.banking.frontend;

import java.io.IOException;

import org.apache.log4j.Logger;

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

	@Override
	public final int interact() {
		final int result = realInteraction();
		// don't need to prompt user to logout or exit, just do it
		switch (result) {
		case EXIT:
		case LOGOUT:
			return result;
		}
		try {
			io.println("Press Enter to continue");
			io.readLine();
		} catch (IOException ioe) {
			Logger.getRootLogger().error("IOException: " + ioe.getMessage());
		}
		return result;
	}

	public abstract int realInteraction();
}
