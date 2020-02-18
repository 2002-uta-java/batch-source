package com.revature.bankingapp.dao;

import java.sql.CallableStatement;
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

	public BankAccount getBankAccountById(int id) {
		String selectOneAccount = "select * from BankAccount where id = ?";
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

	public int createBankAccount(BankAccount ba) {
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
		String updateAccount = "{call update_account(?, ?)}";
		int accountUpdated = 0;
		
		try (Connection databaseConnection = ConnectionUtil.getConnection();
				 CallableStatement sendUpdate = databaseConnection.prepareCall(updateAccount)) {
			sendUpdate.setInt(1, ba.getAccountNumber());
			sendUpdate.setInt(2, (int) ba.getBalance());
			
			accountUpdated = sendUpdate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountUpdated;
	}

	public int deleteBankAccount(BankAccount ba) {
		String deleteAccount = "delete from BankAccount where id = ?";
		int accountDeleted = 0;
		
		try (Connection databaseConnection = ConnectionUtil.getConnection();
				PreparedStatement sendDelete = databaseConnection.prepareStatement(deleteAccount)) {
			sendDelete.setInt(1, ba.getAccountNumber());
			
			accountDeleted = sendDelete.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountDeleted;
	}
}