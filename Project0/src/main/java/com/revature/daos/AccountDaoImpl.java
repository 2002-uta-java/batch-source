package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	public void updateBalance(UserAccount u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getBalance(UserAccount u) {
		// TODO Auto-generated method stub
		
	}

	
}
