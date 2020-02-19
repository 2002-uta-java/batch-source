package com.revature.banking.frontend;

import java.io.IOException;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.banking.frontend.validation.Validation;
import com.revature.banking.services.BankAccountService;
import com.revature.banking.services.UserService;
import com.revature.banking.services.models.BankAccount;
import com.revature.banking.services.models.User;

public abstract class AccountInteraction extends BankInteraction {

	protected User user;
	protected AmountReturn amount = new AmountReturn();

	protected AccountInteraction(CLI io, UserService uService, BankAccountService baService) {
		super(io, uService, baService);
	}

	public void setUser(final User user) {
		this.user = user;
		loginUser();
	}

	public void removeUser() {
		user = null;
	}

	public void printChosenAccountWithPrompt(final String prompt, final BankAccount account) {
		io.println(prompt);
		io.println('\t' + account.printAccountBalanceHideAccountno());
	}

	@Override
	public int interact() {
		final int result = realInteraction();
		// don't need to prompt user to logout or exit, just do it
		switch (result) {
		case EXIT:
		case LOGOUT:
			return result;
		}
		this.promptToContinue();
		return result;
	}

	public int chooseAccount(final String prompt, final List<BankAccount> accounts) {
		while (true) {
			io.clearScreen();
			io.println(prompt);
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
				continue;
			}
			// option was good, return it
			return option;
		}
	}

	public int chooseAccount(final String prompt, final List<BankAccount> accounts, final int except) {
		while (true) {
			io.clearScreen();
			io.println(prompt);
			int count = 1;
			final int size = accounts.size();
			for (int i = 0; i < size; ++i) {
				if (i != except - 1)
					io.println("\t" + count++ + ". " + accounts.get(i).printAccountBalanceHideAccountno());
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
				continue;
			}
			// option was good, return it
			return option;
		}
	}

	public void readAmount() {
		String amountString = null;
		try {
			amountString = io.readLine();
		} catch (IOException ioe) {
			Logger.getRootLogger().error("IOException: " + ioe.getMessage());

			amount.setReturnStatus(FAILURE);
			return;
		}
		if (Validation.validateAmount(amountString)) {
			amount.setAmount(Double.parseDouble(amountString));
			amount.setReturnStatus(SUCCESS);
			return;
		} else {
			if (Validation.validateDecimal(amountString)) {
				final double value = Double.parseDouble(amountString);
				if (value < 0) {
					io.println("You cannot add a negative amount to your account.");
				} else {
					io.println("Amounts must have, at most, 2 decimal places");
				}
				amount.setReturnStatus(FAILURE);
			} else {
				io.println(amountString + " isn't a number");
				amount.setReturnStatus(FAILURE);
			}
		}
	}

	private void loginUser() {
		final int options = getNumMenuOptions();
		for (int i = 0; i < options; ++i) {
			final AccountInteraction interaction = (AccountInteraction) getMenuOption(i);
			interaction.setUser(user);
		}
	}

	public abstract int realInteraction();
}
