package com.revature.banking.frontend;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.banking.services.BankAccountService;
import com.revature.banking.services.UserService;
import com.revature.banking.services.models.BankAccount;

public class WithdrawFunds extends AccountInteraction {
	public static final String TITLE = "Withdraw Funds";

	protected WithdrawFunds(CLI io, UserService uService, BankAccountService baService) {
		super(io, uService, baService);
		this.setTitle(TITLE);
	}

	@Override
	public int realInteraction() {
		final List<BankAccount> accounts = baService.getAccounts(user);
		io.println("Which account would you like to withdraw from?");
		final int option = super.chooseAccount(accounts);

		switch (option) {
		case EXIT:
		case LOGOUT:
		case FAILURE:
			return option;
		}

		return withdrawFunds(accounts.get(option - 1));
	}

	private int withdrawFunds(BankAccount account) {
		double amt = 0;
		while (true) {
			io.print("How much would you like to withdraw? $");
			this.readAmount();
			switch (amount.getReturnStatus()) {
			case EXIT:
			case LOGOUT:
				return amount.getReturnStatus();
			}

			if (amount.getReturnStatus() == FAILURE) {
				if (!retry()) {
					return FAILURE;
				}
				// else try again, continue
				continue;
			}
			amt = amount.getAmount();
			// else amt is good, withdraw it

			final int withdrawalStatus = baService.withdrawFromAccount(account, amt);

			switch (withdrawalStatus) {
			case BankAccountService.WITHDRAWAL_SUCCESS:
				io.clearScreen();
				io.println("Your withdrawal was successful:");
				io.println('\t' + account.printAccountBalanceHideAccountno());
				return BankInteraction.SUCCESS;
			case BankAccountService.WITHDRAWAL_FAILURE:
				io.clearScreen();
				io.println("There was a problem withdrawing");
				return FAILURE;
			case BankAccountService.WITHDRAWAL_INSUFFICIENT_FUNDS:
				io.println("You have insufficient funds.");
				break;
			default:
				Logger.getRootLogger().error("Unrecognized Withdrawal status: " + withdrawalStatus);
				return FAILURE;
			}

			// if you reach this point, you have insufficient funds, try to make another
			// withdrawal
			if (!retry())
				return FAILURE;
		}
	}

}
