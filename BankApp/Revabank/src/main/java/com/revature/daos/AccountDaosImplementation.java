package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Account;
import com.revature.util.ConnectionUtil;

public class AccountDaosImplementation implements AccountDaos {
	
	@Override
	public Account getAccount(int id) {
		String sql = "select * from account where user_id = ?";
		Account account = null;
		ResultSet rs = null;
		
		try(Connection c = ConnectionUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int account_id = rs.getInt("account_id");
				int user_id = rs.getInt("user_id");
				double balance = rs.getDouble("balance");
				account = new Account(account_id, user_id, balance);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return account;
	}

	@Override
	public int createAccount(Account a) {
		String sql = "insert into account (user_id, balance) values (?, ?)";
		int accountCreated = 0;
		
		try(Connection c = ConnectionUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, a.getUser_id());
			ps.setDouble(2, a.getBalance());
			accountCreated = ps.executeUpdate();
			return accountCreated;
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return accountCreated;
	}

	@Override
	public int updateAccount(Account a) {
		String sql = "update account set balance = ? where account_id = ?";
		int accountUpdated = 0;
		
		try(Connection c = ConnectionUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
			ps.setDouble(1, a.getBalance());
			ps.setInt(2, a.getAccount_id());
			accountUpdated = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return accountUpdated;
	}

	@Override
	public int deleteAccount(Account a) {
		String sql = "delete from account where account_id = ?";
		int rowsDeleted = 0;
		
		try(Connection c = ConnectionUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, a.getAccount_id());
			rowsDeleted = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsDeleted;
	}

}
