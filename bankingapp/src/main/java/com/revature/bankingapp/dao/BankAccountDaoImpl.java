package com.revature.bankingapp.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.revature.bankingapp.model.BankAccount;

public class BankAccountDaoImpl implements BankAccountDAO {

	public List<BankAccount> getBankAccounts() {
		String selectAll = "select * from BankAccount";
		List<BankAccount> bankAccounts = new ArrayList<>();
		
		try (Connection databaseConnection = ConnectionUtil.getConnection();
			Statement returnAll = databaseConnection.createStatement();
			ResultSet rs = returnAll.executeQuery(selectAll)) {
			
			
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

	public BankAccount getBankAccountById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int createBankAccount(BankAccount ba) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateBankAccount(BankAccount ba) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteBankAccount(BankAccount ba) {
		// TODO Auto-generated method stub
		return 0;
	}

	public BankAccount createBankAccountWithFunction(BankAccount ba) {
		// TODO Auto-generated method stub
		return null;
	}

}
