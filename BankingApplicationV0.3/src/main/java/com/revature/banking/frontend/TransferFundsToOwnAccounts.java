package com.revature.banking.frontend;

import java.util.List;

import com.revature.banking.services.BankAccountService;
import com.revature.banking.services.UserService;
import com.revature.banking.services.models.BankAccount;

public class TransferFundsToOwnAccounts extends AccountInteraction {
	public static final String TITLE = "Transfer Funds Between Your Accounts";

	protected TransferFundsToOwnAccounts(CLI io, UserService uService, BankAccountService baService) {
		super(io, uService, baService);
		this.setTitle(TITLE);
	}

	@Override
	public int interact() {
		return realInteraction();
	}

	private int chooseTransferFrom(final List<BankAccount> accounts) {
		final int option = this.chooseAccount("Which Account Would You Like to Withdraw From?", accounts);

		switch (option) {
		case EXIT:
		case LOGOUT:
		case FAILURE:
			return option;
		}

		// return the withdrawal account
		return option;
	}

	@Override
	public int realInteraction() {
		final List<BankAccount> accounts = baService.getAccounts(user);

		final int withdrawFromChoice = chooseTransferFrom(accounts);

		// handle exit cases
		switch (withdrawFromChoice) {
		case EXIT:
		case LOGOUT:
		case FAILURE:
			return withdrawFromChoice;
		}

		final BankAccount withdrawFrom = accounts.get(withdrawFromChoice - 1);

		final int transferToChoice = chooseTransferTo(accounts, withdrawFromChoice);

		switch (transferToChoice) {
		case EXIT:
		case LOGOUT:
		case FAILURE:
			return transferToChoice;
		}

		final BankAccount transferTo = accounts.get(transferToChoice - 1);
		final double amt = amount.getAmount();
		// attempt to withdraw from the first account
		final int withdrawStatus = baService.withdrawFromAccount(withdrawFrom, amt);
		switch (withdrawStatus) {
		case BankAccountService.WITHDRAWAL_FAILURE:
			io.clearScreen();
			io.println("There was an error. Your funds were not transferred.");
			this.promptToContinue();
			return FAILURE;
		case BankAccountService.WITHDRAWAL_INSUFFICIENT_FUNDS:
			io.clearScreen();
			io.println("There were insufficient funds to perform the transfer.");
			io.println('\t' + withdrawFrom.printAccountBalanceHideAccountno());
			return FAILURE;
		}
		// else the withdrawal was successful, attempt to transfer the funds

		if (!baService.addFundsToAccountNoLimit(transferTo, amt)) {
			io.clearScreen();
			io.println("We were unable to perform the transfer, sorry.");
			// attempt to transfer the money back into the withdrawn account
			baService.addFundsToAccountNoLimit(withdrawFrom, amt);
			io.println('\t' + withdrawFrom.printAccountBalanceHideAccountno());
			io.println('\t' + transferTo.printAccountBalanceHideAccountno());
			this.promptToContinue();
			return FAILURE;
		}

		// the transfer was successful
		io.clearScreen();
		io.println("Your funds were successfully transferred.");
		io.println('\t' + withdrawFrom.printAccountBalanceHideAccountno());
		io.println('\t' + transferTo.printAccountBalanceHideAccountno());
		this.promptToContinue();
		return SUCCESS;
	}

	private int chooseTransferTo(List<BankAccount> accounts, int withdrawFromChoice) {
		final int option = this.chooseAccount("Which Account Would You Like to Transfer To?", accounts,
				withdrawFromChoice);

		switch (option) {
		case EXIT:
		case LOGOUT:
		case FAILURE:
			return option;
		}

		final BankAccount account = accounts.get(option - 1);

		// else try to get amount
		while (true) {
			io.clearScreen();
			this.printChosenAccountWithPrompt("How much would you like to transfer?", account);
			this.readAmount();

			int returnStatus = amount.getReturnStatus();

			switch (returnStatus) {
			case EXIT:
				return EXIT;
			case LOGOUT:
				return LOGOUT;
			}

			if (returnStatus == FAILURE) {
				if (!retry())
					return FAILURE;
				// else try again, continue
				continue;
			}

			// the user input a valid amount, return success
			// the amount to transfer is held in the amount object
			return option;
		}
	}

}
