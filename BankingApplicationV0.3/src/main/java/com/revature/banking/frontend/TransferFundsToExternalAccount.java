package com.revature.banking.frontend;

import java.util.List;

import com.revature.banking.frontend.validation.Validation;
import com.revature.banking.services.BankAccountService;
import com.revature.banking.services.UserService;
import com.revature.banking.services.models.BankAccount;
import com.revature.banking.services.models.User;

public class TransferFundsToExternalAccount extends AccountInteraction {
	public static final String TITLE = "Transfer Funds To Another Account";

	protected TransferFundsToExternalAccount(CLI io, UserService uService, BankAccountService baService) {
		super(io, uService, baService);
		this.setTitle(TITLE);
	}

	@Override
	public int interact() {
		return realInteraction();
	}

	@Override
	public int realInteraction() {
		BankAccount otherAccount = null;

		while (true) {
			io.clearScreen();
			io.println("You must give a username and account number to transfer to:");
			final String username = getUserName();
			if (username == null)
				return FAILURE;
			final String accountNo = getAccountNo();
			if (accountNo == null)
				return FAILURE;

			io.working();
			final User otherUser = uService.getUserByUserName(username);
			io.done();
			if (otherUser != null) {
				otherAccount = getOtherAccount(otherUser, accountNo);
				if (otherAccount != null) {
					// you found the account, break out of loop
					break;
				}
			}
			// else either the user doesn't exist or the account number doesn't match our
			// records
			io.clearScreen();
			io.println("Ther username/account number is invalid.");
			if (!retry())
				return FAILURE;
			// else try again, continue loop
		}

		io.working();
		final List<BankAccount> yourAccounts = baService.getAccounts(user);
		io.done();
		// choose a bank account to transfer from
		int yourAccountChoice = chooseAccount("Choose an account to transfer funds from:", yourAccounts);

		switch (yourAccountChoice) {
		case EXIT:
		case LOGOUT:
		case FAILURE:
			return yourAccountChoice;
		}

		final BankAccount yourAccount = yourAccounts.get(yourAccountChoice - 1);

		// else choose amount to transfer
		while (true) {
			io.clearScreen();
			io.println("How much would you like to transfer?");
			io.println('\t' + yourAccount.printAccountBalanceHideAccountno());
			io.print("Amount to transfer: $");
			readAmount();

			final int status = amount.getReturnStatus();

			switch (status) {
			case EXIT:
			case LOGOUT:
				return status;
			}

			if (status == FAILURE) {
				if (!retry())
					return FAILURE;
				// try again, continue loop
				continue;
			}

			// you chose an amount try to do the transfer:
			final double amt = amount.getAmount();

			io.working();
			final int withdrawStatus = baService.withdrawFromAccount(yourAccount, amt);
			io.done();
			switch (withdrawStatus) {
			case BankAccountService.WITHDRAWAL_FAILURE:
				io.clearScreen();
				io.println("There was an error, the transfer was not made.");
				io.println('\t' + yourAccount.printAccountBalanceHideAccountno());
				return FAILURE;
			case BankAccountService.WITHDRAWAL_INSUFFICIENT_FUNDS:
				io.clearScreen();
				io.println("There were insufficient funds for the transfer.");
				io.println('\t' + yourAccount.printAccountBalanceHideAccountno());
				return FAILURE;
			}

			// try to transfer the funds
			io.working();
			if (!baService.addFundsToAccountNoLimit(otherAccount, amt)) {
				io.done();
				// there was a problem, attempt to put the money back
				baService.addFundsToAccountNoLimit(yourAccount, amt);
				io.clearScreen();
				io.println("There was an error and the transfer didn't process.");
				io.println('\t' + yourAccount.printAccountBalanceHideAccountno());
				return FAILURE;
			}
			io.done();

			// the transfer was a success!!!
			io.clearScreen();
			io.println("The transfer was completed.");
			io.println('\t' + yourAccount.printAccountBalanceHideAccountno());
			return SUCCESS;
		}
	}

	private BankAccount getOtherAccount(final User otherUser, final String accountNo) {
		final List<BankAccount> accounts = baService.getAccounts(otherUser);
		for (final BankAccount account : accounts) {
			if (account.getAccountNo().equals(accountNo)) {
				return account;
			}
		}
		// account number wasn't found, return null
		return null;
	}

	private String getAccountNo() {
		while (true) {
			io.println("Account Number: ");
			final String accountNo = io.readLine();
			if (Validation.validateAccountNo(accountNo))
				return accountNo;
			// else it wasn't a valid account number
			io.println(accountNo + " isn't a valid account number");
			if (!retry())
				return null;
			// else continue
		}
	}

	private String getUserName() {
		while (true) {
			io.println("username: ");
			final String username = io.readLine();
			if (Validation.validateUserName(username))
				return username;
			// else the username is invalid (no point in searching for it in the DB
			io.println(username + " isn't a valid username");
			if (!retry())
				return null;
			// else continue
		}
	}

}
