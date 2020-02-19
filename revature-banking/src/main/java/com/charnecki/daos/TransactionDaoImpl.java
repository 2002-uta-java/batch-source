package com.charnecki.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.charnecki.models.Deposit;
import com.charnecki.models.Transaction;
import com.charnecki.models.Transfer;
import com.charnecki.models.Withdrawal;
import com.charnecki.util.ConnectionUtil;

public class TransactionDaoImpl implements TransactionDao {

	private String tranTable = "transact";
	private static Logger log = Logger.getRootLogger();
	
	@Override
	public List<Transaction> getAllTransactions() {
		
		List<Transaction> transactions = null;
		String sql = "select * from " + tranTable;
		
		try (Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql);) {
			
			while(rs.next()) {
				Transaction t = null;
				
				switch(rs.getString("tran_type")) {
					case "deposit":
						t = new Deposit();
						break;
					case "withdrawal":
						t = new Withdrawal();
						break;
					case "transfer":
						t = new Transfer();
						break;
				}
				
				t.setId(rs.getInt("id"));
				t.setAccount(rs.getInt("account_id"));
				t.setAmount(rs.getDouble("amount"));
				t.setNote(rs.getString("note"));
				
				if (transactions == null) {
					transactions = new ArrayList<Transaction>();
				}
				
				transactions.add(t);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return transactions;
	}

	@Override
	public Transaction getTransactionById(int id) {
		
		Transaction t = null;
		String sql = "select * from " + tranTable + " where id=?";
		ResultSet rs = null;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
//			log.info("Executed GetById");
			
			while(rs.next()) {
				
//				log.info("Found Result");
				
				switch(rs.getString("tran_type")) {
					case "deposit":
						t = new Deposit();
						break;
					case "withdrawal":
						t = new Withdrawal();
						break;
					case "transfer":
						t = new Transfer();
						break;
				}
				
				t.setId(id);
				t.setAccount(rs.getInt("account_id"));
				t.setAmount(rs.getDouble("amount"));
				t.setNote(rs.getString("note"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		return t;
	}

	@Override
	public int createTransaction(Transaction t) {
		
		String sql = "insert into "+tranTable+" (amount, note, account_id, tran_type) values (?, ?, ?, ?)";
		ResultSet rs = null;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			ps.setDouble(1, t.getAmount());
			ps.setString(2, t.getNote());
			ps.setInt(3, t.getAccountId());
			ps.setString(4, t.getTransactionType());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			while(rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		return 0;
	}

	@Override
	public int updateTransaction(Transaction t) {
		
		String sql = "update "+tranTable+" set amount=?, note=?, account_id=?, tran_type=? where id=?";
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setDouble(1, t.getAmount());
			ps.setString(2, t.getNote());
			ps.setInt(3, t.getAccountId());
			ps.setString(4, t.getTransactionType());
			ps.setInt(5,  t.getId());
			
			return ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int deleteTransaction(Transaction t) {
		
		String sql = "delete from "+tranTable+" where id=?";
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setInt(1,  t.getId());
			
			return ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public List<Transaction> getAllWithAccountId(int id) {

		List<Transaction> transactions = new ArrayList<Transaction>();
		String sql = "select * from " + tranTable + " where account_id=?";
		ResultSet rs = null;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Transaction t = null;
				
				switch(rs.getString("tran_type")) {
					case "deposit":
						t = new Deposit();
						break;
					case "withdrawal":
						t = new Withdrawal();
						break;
					case "transfer":
						t = new Transfer();
						break;
				}
				
				t.setId(rs.getInt("id"));
				t.setAccount(rs.getInt("account_id"));
				t.setAmount(rs.getDouble("amount"));
				t.setNote(rs.getString("note"));
				
				transactions.add(t);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		return transactions;
	}

}
