package com.revature.bank;

import java.text.NumberFormat;
import java.util.Locale;

import com.revature.bank.application.BankApplication;
import com.revature.bank.exceptions.IllegalTransactionException;
import com.revature.bank.exceptions.InsufficientFundsException;

/**
 * All methods in this class have package level access modifiers because only a
 * Bank should make changes to a BankAccount.
 * 
 * @author Jared F Bennatt
 *
 */
public class BankAccount {
	private double balance;

	/**
	 * Rather than have a getAccountNumber() method I'm giving package level access
	 * to this (it's immutable so no modifications can be made). Classes outside of
	 * this package still will not be able to access this variable.
	 */
	final String accountNumber;
	private final NumberFormat currencyFormat;
	private Person principal = null;

	/**
	 * Creates a new BankAccount with an initial balance of 0.
	 * 
	 * All constructors have package level access because only a Bank should create
	 * a BankAccount.
	 * 
	 * @param accountNumber
	 */
//	BankAccount(final String accountNumber) {
//		this(accountNumber, 0);
//	}

	/**
	 * Used to create a BankAccount with an initial amount using the default locale.
	 * 
	 * All constructors have package level access because only a Bank should create
	 * a BankAccount.
	 * 
	 * @param accountNumber
	 * @param initialAmount
	 */
//	BankAccount(final String accountNumber, final double initialAmount) {
//		this(accountNumber, initialAmount, BankApplication.DEF_LOCALE);
//	}

	/**
	 * Specifies a BankAccount with a specific Locale (used to format messages that
	 * report the balance amount).
	 * 
	 * All constructors have package level access because only a Bank should create
	 * a BankAccount.
	 * 
	 * @param accountNumber
	 * @param initialAmount
	 * @param locale
	 */
//	BankAccount(final String accountNumber, final double initialAmount, final Locale locale) {
//		this(accountNumber, initialAmount, NumberFormat.getCurrencyInstance(locale));
//	}

	BankAccount(final String accountNumber, final NumberFormat currencyFormat) {
		this(accountNumber, 0, currencyFormat);
	}

	/**
	 * Constructor which specifies the NumberFormat object for the currency
	 */
	BankAccount(final String accountNumber, final double initialAmount, final NumberFormat currencyFormat) {
		this.accountNumber = accountNumber;
		this.balance = initialAmount;
		this.currencyFormat = currencyFormat;
	}

	/**
	 * Returns the balance of this BankAccount.
	 * 
	 * @return
	 */
	double getBalance() {
		return balance;
	}

	/**
	 * Adds given amount to this bank account.
	 * 
	 * @param addedAmount Amount to be added (cannot be negative or zero).
	 * @throws IllegalTransactionException If addedAmount is negative or zero.
	 */
	synchronized void addFunds(final double addedAmount) throws IllegalTransactionException {
		// throws an IllegalTransactionException and shows the amount attempted to
		// "withdrawal".
		if (addedAmount < 0)
			throw new IllegalTransactionException("Cannot add negative funds (-" + currencyFormat.format(-addedAmount)
					+ "). If you wish to withdrawal, make a withdrawal.");
		if (addedAmount == 0)
			throw new IllegalTransactionException("Adding " + currencyFormat.format(0) + " has no effect.");

		// else add (strictly positive) amount specified.
		balance += addedAmount;
	}

	/**
	 * Withdraws the given amount.
	 * 
	 * @param withdrawal Amount to be withdrawn (cannot be negative, zero, or more
	 *                   than the balance).
	 * @throws IllegalTransactionException If the withdrawal is zero or negative.
	 * @throws InsufficientFundsException  If the withdrawal is more than the
	 *                                     current balance.
	 */
	synchronized void withdrawFunds(final double withdrawal)
			throws IllegalTransactionException, InsufficientFundsException {
		if (withdrawal < 0)
			throw new IllegalTransactionException(
					"Cannot withdraw negative funds (-" + currencyFormat.format(-withdrawal) + ")");
		if (withdrawal == 0)
			throw new IllegalTransactionException("Withdrawing " + currencyFormat.format(0) + " has no effect.");
		if (withdrawal > balance) {
			throw new InsufficientFundsException(balance, withdrawal, currencyFormat);
		}

		balance -= withdrawal;
	}

	public boolean hasPrincipal() {
		return principal != null;
	}

	void setPrincipal(final Person principal) {
		this.principal = principal;
	}

	@Override
	public int hashCode() {
		return accountNumber.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return accountNumber.equals(obj);
	}

}
