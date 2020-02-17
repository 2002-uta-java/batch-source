package com.revature.bankingapp.dao;

import java.sql.SQLException;
import java.util.List;
import com.revature.bankingapp.model.*;


public interface BankAccountDAO {
	public List<BankAccount> getBankAccounts() throws SQLException;
	public BankAccount getBankAccountById(int id) throws SQLException;
	public int createBankAccount(BankAccount ba) throws SQLException;
	public int updateBankAccount(BankAccount ba);
	public int deleteBankAccount(BankAccount ba);
	public BankAccount createBankAccountWithFunction(BankAccount ba);
}
