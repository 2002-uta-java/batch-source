package com.revature.banking.services.security.models;

import com.revature.banking.services.models.BankAccount;
import com.revature.banking.services.models.User;

/**
 * The account number and balance are encrypted in the database, only the
 * account_key is not encrypted. This model is used as a bridge between the
 * service layer and the database layer. The DAO's will only deal with these
 * encrypted models and the service layer will decipher them and transform them
 * into {@link User}/{@link BankAccount} models suitable for the frontend.
 * 
 * @author Jared F Bennatt
 *
 */
public class EncryptedBankAccount {
	private int accountkey;
	private String accountNo;
	private String balance;

	public EncryptedBankAccount() {
		super();
	}

	public EncryptedBankAccount(int accountkey, String accountNo, String balance) {
		super();
		this.accountkey = accountkey;
		this.accountNo = accountNo;
		this.balance = balance;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EncryptedBankAccount other = (EncryptedBankAccount) obj;
		if (accountNo == null) {
			if (other.accountNo != null)
				return false;
		} else if (!accountNo.equals(other.accountNo))
			return false;
		if (accountkey != other.accountkey)
			return false;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		return true;
	}

	public int getAccountkey() {
		return accountkey;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public String getBalance() {
		return balance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNo == null) ? 0 : accountNo.hashCode());
		result = prime * result + accountkey;
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		return result;
	}

	public void setAccountkey(int accountkey) {
		this.accountkey = accountkey;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "EncryptedBankAccount [accountkey=" + accountkey + ", accountNo=" + accountNo + ", balance=" + balance
				+ "]";
	}
}
