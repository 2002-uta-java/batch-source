package com.dean.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.dean.models.Transaction;
import com.dean.util.ConnectionUtil;

public class TransactionDaoImpl {
	
	static final Logger logger = Logger.getRootLogger();
	
	public List<Transaction> getAllTransactions() {
		List<Transaction> transactions = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from transactions";
		
		try(Connection c = ConnectionUtil.getConnection();
				Statement s = c.prepareStatement(sql);
				) {
			rs = s.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("client_id");
				int transactionId = rs.getInt("transaction_id");
				double amount = rs.getDouble("transaction_amount");
				String transactionType = rs.getString("transaction_type");
				Date transactionDate = rs.getDate("transaction_date");
				transactions.add(new Transaction(id, transactionId, transactionType, 
						amount, transactionDate));
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		} finally {
			if( rs!= null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.info(e.getMessage());
				}
			}
		}
		
		return transactions;
	}
	
	public List<Transaction> getTransactionsByClientId(int id) {
		List<Transaction> transactions = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from transactions where client_id = ?";
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				int transactionId = rs.getInt("transaction_id");
				Double amount = rs.getDouble("transaction_amount");
				String transactionType = rs.getString("transaction_type");
				Date transactionDate = rs.getDate("transaction_date");
				transactions.add(new Transaction(id, transactionId, transactionType, 
						amount, transactionDate));
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.info(e.getMessage());
				}
			}
		}
		
		return transactions;
	}
	

	public void logHistory(List<Transaction> transactions) {
		for(Transaction transaction : transactions) {
			String username = "";
			
			logger.info("");
			logger.info(transaction.getTransactionDate() + " : "
					 + transaction.getTransactionType() + " : "
					+ transaction.getAmount() + " : " + username);
		}
	}
	

}
