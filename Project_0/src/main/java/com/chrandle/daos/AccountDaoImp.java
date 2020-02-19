package com.chrandle.daos;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chrandle.models.Account;
import com.chrandle.util.ConnectionUtil;
import com.chrandle.util.InvalidTransactionException;

public class AccountDaoImp implements AccountDao {

	@Override
	public List<Account> getAccounts() {
		String query = "Select * from Accounts";
		List<Account> accounts = new ArrayList<>();

		try (
				Connection c = ConnectionUtil.getConnection();
				Statement statement  = c.createStatement();
				ResultSet result = statement.executeQuery(query);
			)
		{
			
			while(result.next()) {
				Account a = new Account();
				a.setAccountid(result.getLong("AccountID"));
				a.setBalance(result.getDouble("Balance"));
				a.setType(result.getString("Type"));
				accounts.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return accounts;
	}

	@Override
	public Account getAccountById(long id) {
		String query = "Select * from Accounts where AccountID = ? ";
		Account account = null;
		ResultSet result = null;
		
		try(Connection c = ConnectionUtil.getConnection();
			PreparedStatement pstatement =  c.prepareStatement(query)) {
			
			pstatement.setLong(1, id);
			result = pstatement.executeQuery();
			
			//TODO: use if?
			while(result.next()) {
				account = new Account();
				account.setAccountid(result.getLong("AccountID"));
				account.setBalance(result.getDouble("Balance"));
				account.setType(result.getString("Type"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (result !=null) {
				try{
					result.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return account;
	}

	@Override
	 public long createAccount(long uid,double balance, String type) {
		String sql = "{call add_account(?::bigint, ?::numeric::money, ?)}";
		ResultSet result = null;
		try(Connection userConn = ConnectionUtil.getConnection()){
			CallableStatement pcall =  userConn.prepareCall(sql);
			pcall.setLong(1, uid);
			pcall.setDouble(2, balance);
			pcall.setString(3,type);
			pcall.execute();
			result = pcall.getResultSet();
			
			if (result.next()) {
				return result.getLong(1);
			}
			
			
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return 0;
		}
		return 0;
	}

	@Override
	public double updateAccount(Account a, double amount) {
		
		if(amount <0 && (a.getBalance() - amount <0) ) {
			return -1;
		} else {
		
			
			String query = "update accounts set balance = ?::numeric::money where accountid = ? " ; 
			ResultSet result = null;
			try(Connection c = ConnectionUtil.getConnection();
					PreparedStatement pstatement =  c.prepareStatement(query)) {
					
					pstatement.setDouble(1, a.getBalance()+amount);
					pstatement.setLong(2, a.getAccountid());
				    pstatement.executeUpdate();
					return a.getBalance();
					
				} catch (SQLException e) {
					e.printStackTrace();
					return -1;
				} 
		}
		
	
	}

	@Override
	public int deleteAccount(Account a) {
		// TODO Auto-generated method stub
		return 0;
	}

}
