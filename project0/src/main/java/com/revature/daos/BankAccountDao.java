package com.revature.daos;

import java.util.List;

import com.revature.models.BankAccount;
import com.revature.models.UserAccount;

public interface BankAccountDao {

	public BankAccount createBankAccount(BankAccount a);
	public List<BankAccount> getAllBankAccounts(UserAccount ua);
	public BankAccount getBankAccountById(int baid);
	public int updateBankAccountBalance(BankAccount a);
	public int deleteBankAccount(BankAccount a);
}
