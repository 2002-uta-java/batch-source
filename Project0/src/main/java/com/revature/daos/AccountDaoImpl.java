package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.BankAccount;
import com.revature.models.UserAccount;
import com.revature.util.ConnectionUtil;

public class AccountDaoImpl implements AccountDao{

	@Override
	public void createAccount(String username, String password) {
		// TODO: DO NOT CREATE CLASS, simply add the user/pass to db, AND generate bankaccount -> bankId
		// NOTE: no instances of classes seem to be created in this application.
		// assumed that username does not exist.
	}

	@Override
	public List<UserAccount> getUserAccounts() {
		String sql = "select * from user_account";
		
		List<UserAccount> userAccounts = new ArrayList<>();
		
		try (Connection c = ConnectionUtil.getConnection()) {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("user_password");
				int bankId = rs.getInt("bank_id");
				UserAccount u = new UserAccount(username, password, bankId);
				userAccounts.add(u);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userAccounts;
	}
	
	@Override
	public UserAccount getUserAccount(String username) {
		String sql = "select * from user_account where username = ?";
		UserAccount u = null;
		ResultSet rs = null;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
			
			ps.setString(1, username);
			
			while (rs.next()) {
				String uN = rs.getString("username");
				String password = rs.getString("user_password");
				int bankId = rs.getInt("bank_id");
				u = new UserAccount(uN, password, bankId);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null) {
					rs.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return u;
	}
	
	@Override
	public BankAccount getBankAccount(UserAccount u) {
		String sql = "select * from bank_account where bank_id = ?";
		BankAccount b = null;
		ResultSet rs = null;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
			
			ps.setInt(1, u.getBankId());
			
			while (rs.next()) {
				int bankId = rs.getInt("bank_id");
				float balance = rs.getFloat("balance");
				b = new BankAccount(bankId, balance);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null) {
					rs.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return b;
	}

	@Override
	public void updateBankAccount(BankAccount b) {
		// TODO Auto-generated method stub
		
	}

	
}
