package com.revature.bankingapp.dao;

import java.util.List;
import com.revature.bankingapp.model.*;


public interface BankAccountDAO {
	public List<BankAccount> getBankAccounts();
	public BankAccount getBankAccountById(int id);
	public int createBankAccount(BankAccount ba);
	public int updateBankAccount(BankAccount ba);
	public int deleteBankAccount(BankAccount ba);
}
