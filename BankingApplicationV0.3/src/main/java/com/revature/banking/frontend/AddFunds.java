package com.revature.banking.frontend;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.banking.dao.BankAccountDao;
import com.revature.banking.services.BankAccountService;
import com.revature.banking.services.UserService;
import com.revature.banking.services.models.BankAccount;

public class AddFunds extends AccountInteraction {
	public static final String TITLE = "Add funds";
	public static final String PROMPT = "Which account would you like to add funds to?";

	protected AddFunds(CLI io, UserService uService, BankAccountService baService) {
		super(io, uService, baService);
		super.setTitle(TITLE);
	}

	private int addFunds(final BankAccount account) {
		while (true) {
			io.clearScreen();
			super.printChosenAccountWithPrompt(PROMPT, account);
			// this was a valid choice prompt user to add funds
			io.print("How much would you like to add? $");
			readAmount();
			switch (amount.getReturnStatus()) {
			case EXIT:
			case LOGOUT:
				return amount.getReturnStatus();
			}

			if (amount.getReturnStatus() == FAILURE) {
				if (!super.retry())
					return BankInteraction.FAILURE;
				// else try again (continue
			} else
				break;// it was a success, break out of loop
		}

		// else it was a success, perform the operation
		final double amt = amount.getAmount();
		final int addFundsStatus = baService.addFundsToAccount(account, amt);

		switch (addFundsStatus) {
		case BankAccountService.ADD_FUNDS_SUCCESS:
			io.clearScreen();
			io.println("Your funds have been added: ");
			io.println('\t' + account.printAccountBalanceHideAccountno());
			return BankInteraction.SUCCESS;
		case BankAccountService.ADD_FUNDS_DEPOSIT_LIMIT:
			io.println("You have attempted to deposit more than "
					+ BankAccount.MONEY_FORMAT.format(BankAccountDao.MAX_DEPOSIT));
			io.println("The FBI has been contacted and should be in touch with you shortly");
			io.println("You are now being logged out of our system.");
			this.promptToContinue();
			return BankInteraction.LOGOUT;
		case BankAccountService.ADD_FUNDS_FAILURE:
			io.println("There was a problem adding the funds to your account.");
			io.println('\t' + account.printAccountBalanceHideAccountno());
			return BankInteraction.FAILURE;
		default:
			Logger.getRootLogger().debug("addFunds returns a unrecognized value: " + addFundsStatus);
			return BankInteraction.FAILURE;
		}
	}

	@Override
	public int realInteraction() {
		final List<BankAccount> accounts = baService.getAccounts(user);

		int option = super.chooseAccount(PROMPT, accounts);

		switch (option) {
		case EXIT:
		case LOGOUT:
		case FAILURE:
			return option;
		}

		// else go to add funds prompt
		return addFunds(accounts.get(option - 1));
	}

}
