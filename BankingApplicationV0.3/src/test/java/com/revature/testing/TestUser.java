package com.revature.testing;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import com.revature.banking.services.models.BankAccount;
import com.revature.banking.services.models.User;

public class TestUser {
	private final User user;
	private final List<BankAccount> accounts = new LinkedList<BankAccount>();

	public TestUser(final User user) {
		this.user = user;
	}

	public int getUserKey() {
		return user.getUserKey();
	}

	public void addAccount(final BankAccount account) {
		this.accounts.add(account);
	}

	@Override
	public String toString() {
		return user.toString();
	}

	public Iterable<BankAccount> getAccounts() {
		return accounts;
	}

	public void write(PrintWriter pw) {
		// print the user's name, tax id, username, password, then their accounts
		pw.print(user.getFirstName());
		pw.print(", " + user.getLastName());
		pw.print(", " + user.getTaxID());
		pw.print(", " + user.getUserName());
		pw.print(", " + user.getPassword());

		for (final BankAccount account : accounts)
			pw.print(", " + account.getAccountNo());
		pw.println();
	}
}
