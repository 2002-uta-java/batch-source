package com.revature.banking.frontend;

import com.revature.banking.services.BankAccountService;
import com.revature.banking.services.UserService;

public class TransferFunds extends AccountInteraction {
	public static final String TITLE = "Transfer Funds To Another Account";

	protected TransferFunds(CLI io, UserService uService, BankAccountService baService) {
		super(io, uService, baService);
		this.setTitle(TITLE);
		this.addMenuOption(new TransferFundsToOwnAccounts(io, uService, baService));
	}

	@Override
	public int realInteraction() {
		final int choice = getMenu();
		switch (choice) {
		case EXIT:
		case LOGOUT:
		case FAILURE:
			return choice;
		}

		return interact(choice);
	}

}
