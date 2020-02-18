package com.revature.banking.frontend;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.banking.dao.BankAccountDao;
import com.revature.banking.frontend.validation.Validation;
import com.revature.banking.services.BankAccountService;
import com.revature.banking.services.UserService;
import com.revature.banking.services.models.BankAccount;

public class AddFunds extends AccountInteraction {
	public static final String TITLE = "Add funds";

	protected AddFunds(CLI io, UserService uService, BankAccountService baService) {
		super(io, uService, baService);
		super.setTitle(TITLE);
	}

	private int addFunds(final int option, final List<BankAccount> accounts) {
		while (true) {
			// this was a valid choice prompt user to add funds
			io.print("How much would you like to add? $");
			String amountString = null;
			try {
				amountString = io.readLine();
			} catch (IOException ioe) {
				Logger.getRootLogger().error("IOException: " + ioe.getMessage());
				return FAILURE;
			}
			if (Validation.validateAmount(amountString)) {
				final double amount = Double.parseDouble(amountString);
				if (amount > BankAccountDao.MAX_DEPOSIT) {
					io.println("You have attempted to deposit more than "
							+ BankAccount.MONEY_FORMAT.format(BankAccountDao.MAX_DEPOSIT));
					io.println("The FBI has been contacted and should be in touch with you shortly");
					io.println("You are now being logged out of our system.");
					return BankInteraction.LOGOUT;
				}

				final BankAccount account = accounts.get(option - 1);
				// else add funds to the account
				account.addFunds(amount);
				if (baService.addFunds(account, amount)) {
					io.println("Your funds have been added: ");
					io.println('\t' + account.printAccountBalanceHideAccountno());
					return BankInteraction.SUCCESS;
				}
				// need to undo the add funds because I'm about to print the value (not from the
				// db)
				account.addFunds(-amount);
				// else there was a problem adding the funds to the account
				io.println("There was a problem adding the funds to your account.");
				io.println('\t' + account.printAccountBalanceHideAccountno());
				return BankInteraction.FAILURE;
			} else {
				if (Validation.validateDecimal(amountString)) {
					final double value = Double.parseDouble(amountString);
					if (value < 0) {
						io.println("You cannot add a negative amount to your account.");
					} else {
						io.println("Amounts must have, at most, 2 decimal places");
					}
				} else {
					io.println(amountString + " isn't a number");
				}
				if (!super.retry())
					return BankInteraction.FAILURE;
			}
		}
	}

	@Override
	public int realInteraction() {
		final List<BankAccount> accounts = baService.getAccounts(user);
		while (true) {
			io.println("Which account would you like to add funds to?");
			int count = 1;
			for (final BankAccount account : accounts) {
				io.println("\t" + count++ + ". " + account.printAccountBalanceHideAccountno());
			}
			final int option = super.readOption(accounts.size());

			switch (option) {
			case EXIT:
				return EXIT;
			case LOGOUT:
				return LOGOUT;
			}

			if (option == FAILURE) {
				io.println("That's not a valid option");
				if (!retry())
					return FAILURE;
				// else try again
				io.clearScreen();
				continue;
			}
			// else go to add funds prompt
			return addFunds(option, accounts);
		} // else try again
	}

}
