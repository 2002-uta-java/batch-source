package com.revature.bankingapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.bankingapp.model.Transaction;

public class TransactionDaoImpl implements TransactionDAO {

	public List<Transaction> getTransaction(){
		String selectAll = "select * from Transaction";
		List<Transaction> transactions = new ArrayList<>();
		
		try (Connection databaseConnection = ConnectionUtil.getConnection();
			Statement returnAll = databaseConnection.createStatement();
			ResultSet rs = returnAll.executeQuery(selectAll)) {
			
			while (rs.next()) {
				// For each entry in rs, add to bankAccounts
				int tId = rs.getInt("id");
				double amount = rs.getDouble("amount");
				int accountFrom = rs.getInt("account_from");
				int accountTo = rs.getInt("account_to");
				Transaction t = new Transaction(tId, amount, accountFrom, accountTo);
				transactions.add(t);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return transactions;
	}

	public Transaction getTransactionById(int id) {
		String selectOneTransaction = "select * from Transaction where ?";
		Transaction t = null;
		ResultSet rs = null;
		
		try (Connection databaseConnection = ConnectionUtil.getConnection();
			PreparedStatement returnAll = databaseConnection.prepareStatement(selectOneTransaction)) {
			returnAll.setInt(1, id);
			rs = returnAll.executeQuery();
			
			while (rs.next()) {
				int transactionId = rs.getInt("id");
				double amount = rs.getDouble("amount");
				int accountFrom = rs.getInt("account_from");
				int accountTo = rs.getInt("account_To");
				t = new Transaction(transactionId, amount, accountFrom, accountTo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	public int createTransaction(Transaction t) {
		String addTransaction = "insert into Transaction (id, amount, account_from, account_to) values (?, ?, ?, ?)";
		int transactionAdded = 0;
		
		try (Connection databaseConnection = ConnectionUtil.getConnection();
				PreparedStatement createTransaction = databaseConnection.prepareStatement(addTransaction)) {
			createTransaction.setInt(1, t.getTransactionId());
			createTransaction.setDouble(2, t.getAmount());
			createTransaction.setInt(3, t.getAccountFrom());
			createTransaction.setInt(4, t.getAccountTo());
			
			transactionAdded = createTransaction.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactionAdded;
	}

	public int updateTransaction(Transaction t) {
		String updateTransaction = "update Transaction (amount, account_from, account_to) values (?, ?, ?) where id = ?";
		int transactionUpdated = 0;
		
		try (Connection databaseConnection = ConnectionUtil.getConnection();
				 PreparedStatement sendUpdate = databaseConnection.prepareCall(updateTransaction)) {
			sendUpdate.setDouble(1, t.getAmount());
			sendUpdate.setInt(2, t.getAccountFrom());
			sendUpdate.setInt(3, t.getAccountTo());
			sendUpdate.setInt(4, t.getTransactionId());

			transactionUpdated = sendUpdate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactionUpdated;
	}

	public int deleteTransaction(Transaction t) {
		String deleteTransaction = "delete from Transaction where id = ?";
		int transactionDeleted = 0;
		
		try (Connection databaseConnection = ConnectionUtil.getConnection();
				PreparedStatement sendDelete = databaseConnection.prepareStatement(deleteTransaction)) {
			sendDelete.setInt(1, t.getTransactionId());
			
			transactionDeleted = sendDelete.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactionDeleted;
	}

}
