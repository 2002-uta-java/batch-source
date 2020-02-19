package com.dean.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.dean.models.Account;
import com.dean.util.ConnectionUtil;
import com.dean.util.InputValidator;

public class AccountDaoImpl implements AccountDao {
	
	static final Scanner sc = new Scanner(System.in);
	static final Logger logger = Logger.getRootLogger();
	
	@Override
	public Account getAccountById(int id) {
		return null;
	}
	
	@Override
	public Account getAccountByClientId(int id) {
		Account account = null;
		ResultSet rs = null;
		String sql = "select * from account where client_id = ?";
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int accountId = rs.getInt("account_id");
				double amount = rs.getDouble("balance");
				int clientId = rs.getInt("client_id");
				
				account = new Account(accountId, amount, clientId);
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
		
		return account;
	}
	
	@Override
	public int createAccount(Account account) {
		int createdAccounts = 0;
		
		String sql = "insert into account (client_id) values (?)";
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);
				) {
			ps.setInt(1, account.getClientId());
			createdAccounts = ps.executeUpdate();
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		if(createdAccounts > 0) {
			logger.info("Your account was successfully created!");
		}
		return createdAccounts;
	}
	
	
	public int createAccount(int clientId) {
		int createdAccounts = 0;
		
		String sql = "insert into account (client_id) values (?)";
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);
				) {
			ps.setInt(1, clientId);
			createdAccounts = ps.executeUpdate();
		} catch (SQLException e) {
			logger.info(e.getMessage());
		} 
		if(createdAccounts > 0) {
			logger.info("Your account was successfully created!");
		}
		return createdAccounts;
	}
	
	@Override
	public void deposit(int id) {
		double amount = 0;
		logger.info("Enter a valid positive number");
		String input = sc.nextLine();
		amount = Double.parseDouble(input);
		if(amount > 0.00) {	
			String sql = "{call deposit(?, ?)}";
			try(Connection c = ConnectionUtil.getConnection();
					CallableStatement cs = c.prepareCall(sql)) {
				cs.setInt(1, id);
				cs.setDouble(2, amount);
				cs.execute();
				
				logger.info("You deposited $" + InputValidator.formatDecimals(amount));
			} catch (SQLException e) {
				logger.info(e.getMessage());
			} 
		} else {
			this.deposit(id);
		}
		
	}
	@Override
	public void withdraw(int id) {
		double currBalance = viewBalanceByAccountId(id);
		double amount = 0;
		logger.info("Enter a valid positive number");
		String input = sc.nextLine();
		amount = Double.parseDouble(input);
		
		if(currBalance - amount >= 0.00) {
			String sql = "{call withdraw(?, ?)}";
			try(Connection c = ConnectionUtil.getConnection();
					CallableStatement cs = c.prepareCall(sql)) {
				cs.setInt(1, id);
				cs.setDouble(2, amount);
				cs.execute();
				
				
				logger.info("You withdrew $" + InputValidator.formatDecimals(amount));
			} catch (SQLException e) {
				logger.info(e.getMessage());
			} 
		} else {
			logger.info("Cannot withdraw more than account balance. \n");
		}
		
		
	}
	@Override
	public void transfer(int fromId) {
		// TODO Auto-generated method stub
		
	}
	
	public double getBalanceByClientId(int id) {
		System.out.println(id);
		String sql = "select balance from account where client_id = ?";
		ResultSet rs = null;
		double balance = 0;
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				balance = rs.getDouble("balance");
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
		
		return balance;
	}
	
	public double getPositiveAmount() {
		double amount = 0;
		do {
			try {
				logger.info("Enter a valid positive number");
				String input = sc.nextLine();
				amount = Double.parseDouble(input);
			} catch (Exception e) {
				logger.info("stop");
				logger.info("");
			}
		} while (amount > 0);
		
		
		return amount;
	}
	
	
	public double viewBalanceByAccountId(int id) {
		String sql = "select balance from account where account_id = ?";
		ResultSet rs = null;
		double balance = 0;
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				balance = rs.getDouble("balance");
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
		
		return balance;
	}
	
	
}
