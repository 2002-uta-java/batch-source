package com.revature.bankingapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.bankingapp.model.BankAccount;

public class BankAccountDaoImpl implements BankAccountDAO {

	public List<BankAccount> getBankAccounts() {
		String selectAll = "select * from BankAccount";
		List<BankAccount> bankAccounts = new ArrayList<>();
		
		try (Connection databaseConnection = ConnectionUtil.getConnection();
			Statement returnAll = databaseConnection.createStatement();
			ResultSet rs = returnAll.executeQuery(selectAll)) {
			
			while (rs.next()) {
				// For each entry in rs, add to bankAccounts
				int baId = rs.getInt("id");
				double balance = rs.getDouble("balance");
				BankAccount ba = new BankAccount(baId, balance);
				bankAccounts.add(ba);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return bankAccounts;
	}

	public BankAccount getBankAccountById(int id) throws SQLException {
		String selectOneAccount = "select * from BankAccount where ?";
		BankAccount ba = null;
		ResultSet rs = null;
		
		try (Connection databaseConnection = ConnectionUtil.getConnection();
			PreparedStatement returnAll = databaseConnection.prepareStatement(selectOneAccount)) {
			returnAll.setInt(1, id);
			rs = returnAll.executeQuery();
			
			while (rs.next()) {
				int baId = rs.getInt("id");
				double balance = rs.getDouble("balance");
				ba = new BankAccount(baId, balance);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ba;
	}

	public int createBankAccount(BankAccount ba) throws SQLException {
		String addAccount = "insert into BankAccount (id, balance) values (?, ?)";
		int accountsAdded = 0;
		
		try (Connection databaseConnection = ConnectionUtil.getConnection();
				PreparedStatement createAccount = databaseConnection.prepareStatement(addAccount)) {
			createAccount.setInt(1, ba.getAccountNumber());
			createAccount.setDouble(2, ba.getBalance());
			accountsAdded = createAccount.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountsAdded;
	}

	public int updateBankAccount(BankAccount ba) {
		String updateAccount = "update BankAccount set id = ?, balance = ?";
		int accountUpdated = 0;
		
		try (Connection databaseConnection = ConnectionUtil.getConnection();
				)
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
