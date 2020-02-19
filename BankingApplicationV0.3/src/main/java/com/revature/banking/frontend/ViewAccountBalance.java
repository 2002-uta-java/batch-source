package com.revature.banking.frontend;

import java.util.List;

import com.revature.banking.services.BankAccountService;
import com.revature.banking.services.UserService;
import com.revature.banking.services.models.BankAccount;

public class ViewAccountBalance extends AccountInteraction {
	public static final String TITLE = "View Account Balance";

	public ViewAccountBalance(final CLI cli, final UserService uService, final BankAccountService baService) {
		super(cli, uService, baService);
		super.setTitle(TITLE);
	}

	@Override
	public int realInteraction() {
		final List<BankAccount> accounts = baService.getAccounts(user);

		io.println("You Account Balances:");
		for (final BankAccount account : accounts) {
			io.println('\t' + account.printAccountBalanceHideAccountno());
		}

		return BankInteraction.SUCCESS;
	}
}
