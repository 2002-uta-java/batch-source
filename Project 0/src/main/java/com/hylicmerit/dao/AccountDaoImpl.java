package com.hylicmerit.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.hylicmerit.ConnectionUtil;
import com.hylicmerit.models.Account;
import com.hylicmerit.models.User;

public class AccountDaoImpl implements AccountDao {

	@Override
	public List<Account> getAssociatedAccounts(User u) {
		String sql = "{call get_associated_accounts(?)}";
		List<Account> accounts = new ArrayList<>();
		ResultSet rs = null;
		try (Connection c = ConnectionUtil.getConnection();
				CallableStatement cs = c.prepareCall(sql);) {
			cs.setString(1, u.getUsername());
			rs = cs.executeQuery();
			while (rs.next()) {
				int accountId = rs.getInt("account_id");
				double balance = rs.getDouble("balance");
				String type = rs.getString("type");
				Account a = new Account(accountId, balance, type);
				accounts.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return accounts;
	}

	@Override
	public int createAccount(Account a, User u) {
		String sql = "{? = call create_account(?,?,?)}";
		int numRowsAffected = 0;
		try (Connection c = ConnectionUtil.getConnection();
			CallableStatement cs = c.prepareCall(sql)){
			//result of user defined function
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setDouble(2, a.getBalance());
			cs.setString(3, a.getType());
			cs.setString(4, u.getUsername());
			cs.executeUpdate();
			//get result (expecting 3 rows affected)
			numRowsAffected = cs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numRowsAffected;
	}

	@Override
	public int updateAccount(Account a) {
		String sql = "update account set balance = ?,type = ? where account_id = ?";
		int numRowsAffected = 0;
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			ps.setDouble(1, a.getBalance());
			ps.setString(2, a.getType());
			ps.setInt(3, a.getAccountId());
			numRowsAffected = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numRowsAffected;
	}

	@Override
	public int deleteAccount(Account a) {
		String sql = "delete from account where account_id = ?";
		int numRowsAffected = 0;
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, a.getAccountId());
			numRowsAffected = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numRowsAffected;
	}

}
