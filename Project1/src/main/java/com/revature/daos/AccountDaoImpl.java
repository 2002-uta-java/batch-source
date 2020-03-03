package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.util.ConnectionUtil;

public class AccountDaoImpl implements AccountDao {

	private String accountTable = "reimburse.account";
	
	@Override
	public List<Account> getAllAccounts() {
		
		String sql = "select * from " + accountTable;
		List<Account> accounts = new ArrayList<Account>();
		
		try (Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql);) {
			
			while(rs.next()) {
				
				Account a = new Account();
				
				a.setId(rs.getInt("id"));
				a.setAccountCreated(((Timestamp)rs.getObject("date_created")).toLocalDateTime());
				a.setAcctType(rs.getString("account_type"));
				a.setEmail(rs.getString("email"));
				a.setManagerId(rs.getInt("manager_id"));
				a.setName(rs.getString("name"));
				a.setPassword(rs.getString("password"));
				
				accounts.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return accounts;
	}

	@Override
	public Account getAccountById(int id) {
		String sql = "select * from " + accountTable + " where id=?";
		ResultSet rs = null;
		Account a = null;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				a = new Account();
				
				a.setId(rs.getInt("id"));
				a.setAccountCreated(((Timestamp)rs.getObject("date_created")).toLocalDateTime());
				a.setAcctType(rs.getString("account_type"));
				a.setEmail(rs.getString("email"));
				a.setManagerId(rs.getInt("manager_id"));
				a.setName(rs.getString("name"));
				a.setPassword(rs.getString("password"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		return a;
	}

	@Override
	public Account getAccountByEmail(String email) {
		String sql = "select * from " + accountTable + " where email=?";
		ResultSet rs = null;
		Account a = null;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			
			ps.setString(1, email);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				a = new Account();
				
				a.setId(rs.getInt("id"));
				a.setAccountCreated(((Timestamp)rs.getObject("date_created")).toLocalDateTime());
				a.setAcctType(rs.getString("account_type"));
				a.setEmail(rs.getString("email"));
				a.setManagerId(rs.getInt("manager_id"));
				a.setName(rs.getString("name"));
				a.setPassword(rs.getString("password"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		return a;
	}

	@Override
	public int createAccount(Account a) {
		String sql = "insert into " + accountTable + " (name, email, password, manager_id, account_type, date_created) values (?, ?, ?, ?, ?, now())";
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			
			ps.setString(1, a.getName());
			ps.setString(2, a.getEmail());
			ps.setString(3, a.getPassword());
			ps.setInt(4, a.getManagerId());
			ps.setString(5, String.valueOf(a.getAcctType()));
			
			return ps.executeUpdate();
			
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int updateAccount(Account a) {
		String sql = "update " + accountTable + " set name=?, email=? password=?, manager_id=?, account_type=?, date_created=? where id=?";
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			
			ps.setString(1, a.getName());
			ps.setString(2, a.getEmail());
			ps.setString(3, a.getPassword());
			ps.setInt(4, a.getManagerId());
			ps.setString(5, String.valueOf(a.getAcctType()));
			ps.setObject(6, Timestamp.valueOf(a.getAccountCreated()));
			ps.setInt(7, a.getId());
			
			return ps.executeUpdate();
			
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int deleteAccount(Account a) {
		String sql = "delete from " + accountTable + " where id=?";
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			
			ps.setInt(1, a.getId());
			
			return ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

}
