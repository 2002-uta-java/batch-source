package com.hylicmerit.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.hylicmerit.ConnectionUtil;
import com.hylicmerit.models.Account;
import com.hylicmerit.models.Transaction;
import com.hylicmerit.models.User;

public class TransactionDaoImpl implements TransactionDao {

	@Override
	public List<Transaction> getAllTransactions(User u) {
		String sql = "{call get_associated_transactions(?)}";
		List<Transaction> transactions = new ArrayList<>();
		ResultSet rs = null;
		try (Connection c = ConnectionUtil.getConnection();
			CallableStatement cs = c.prepareCall(sql);){
			cs.setString(1, u.getUsername());
			rs = cs.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					int id = rs.getInt("transaction_id");
					String date = rs.getString("date");
					double openingBalance = rs.getDouble("opening_balance");
					double closingBalance = rs.getDouble("final_amount");
					Transaction t = new Transaction(id, date, openingBalance, closingBalance);
					transactions.add(t);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactions;
	}

	@Override
	public int createTransaction(Account a, User u, double amount) {
		
		String sql = "insert into transaction (username, account_id, date, opening_balance, final_amount) values(?,?,?,?,?)";
		int numRowsAffected = 0;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-DD-YYYY HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String curDate = now.format(dtf);
		double prevBalance = a.getBalance();
		double newBalance = (prevBalance + amount);
		try (Connection c = ConnectionUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, u.getUsername());
			ps.setInt(2, a.getAccountId());
			ps.setString(3, curDate);
			ps.setDouble(4, prevBalance);
			ps.setDouble(5, newBalance);
			numRowsAffected = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numRowsAffected;
	}

}
