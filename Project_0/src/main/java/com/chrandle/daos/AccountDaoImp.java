package com.chrandle.daos;

import java.sql.ResultSet;
import java.sql.Statement;
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
		String query = "Select * from Account where AccountID = ? ";
		Account account = null;
		ResultSet result = null;
		
		try {
			Connection c = ConnectionUtil.getConnection();
			PreparedStatement pstatement =  c.prepareStatement(query);
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
	public int createAccount(Account a) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double updateAccount(Account a, double amount) throws InvalidTransactionException {
		
		if(amount <0 && (a.getBalance() - amount <0) ) {
			throw new InvalidTransactionException("Withdrawal over balance");
		} else {
			a.setBalance(a.getBalance() + amount);
		}
		
		return a.getBalance();
	}

	@Override
	public int deleteAccount(Account a) {
		// TODO Auto-generated method stub
		return 0;
	}

}
