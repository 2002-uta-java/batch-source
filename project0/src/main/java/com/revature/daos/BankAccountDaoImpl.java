package com.revature.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.BankAccount;
import com.revature.models.UserAccount;
import com.revature.util.ConnectionUtil;

public class BankAccountDaoImpl implements BankAccountDao {
	
	private static Logger log = Logger.getRootLogger();

	@Override
	public BankAccount createBankAccount(BankAccount ba) {
		String sql = "{call add_bank_account(?, ?)}";
		ResultSet rs = null;
		BankAccount createdBankAccount = null;
		try(Connection c = ConnectionUtil.getConnection();
				CallableStatement cs = c.prepareCall(sql)) {
			cs.setInt(1, ba.getUa().getUaid());
			cs.setString(2, ba.getAccountType());
			cs.execute();
			rs = cs.getResultSet();
			
			while(rs.next()) {
				createdBankAccount = new BankAccount();
				createdBankAccount.setBaid(rs.getInt("baid"));
				createdBankAccount.setUa(ba.getUa());
				createdBankAccount.setAccountType(rs.getString("account_type"));
				createdBankAccount.setBalance(rs.getDouble("balance"));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null) {
					rs.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
				log.error("SQLException when creating bank account\n");
			}
		}
		return createdBankAccount;
	}

	@Override
	public List<BankAccount> getAllBankAccounts(UserAccount ua) {
		String sql = "select * from bank_account where uaid = ?";	
		
		ResultSet rs = null;
		List<BankAccount> bankAccounts = new ArrayList<>();
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, ua.getUaid());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BankAccount ba = new BankAccount();
				ba.setBaid(rs.getInt("baid"));
				ba.setUa(ua);
				ba.setAccountType(rs.getString("account_type"));
				ba.setBalance(rs.getDouble("balance"));
				bankAccounts.add(ba);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
			log.error("SQLException when getting all bank accounts from a user account\n");
		}
		finally {
			if(rs != null) {
				try {
					rs.close();
				}
				catch(SQLException e) {
					e.printStackTrace();
					log.error("ResultSet couldn't close after getting all bank accoutn from a user\n");
				}
			}
		}
		return bankAccounts;
	}

	@Override
	public BankAccount getBankAccountById(int baid_input) {
		String sql = "select * from {oj bank_account left outer join user_account on (bank_account.uaid = user_account.uaid)} where baid = ?";
		BankAccount ba = null;
		ResultSet rs = null;
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, baid_input);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ba = new BankAccount();
				UserAccount ua = new UserAccount();
				ba.setBaid(rs.getInt("baid"));
				ba.setAccountType(rs.getString("account_type"));
				ba.setBalance(rs.getDouble("balance"));
				ua.setUaid(rs.getInt("uaid"));
				ua.setUsername(rs.getString("username"));
				ua.setEmail(rs.getString("email"));
				ua.setPassword(rs.getString("pw"));
				ba.setUa(ua);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
			log.error("SQLException when getting a bank account by its bank account id\n");
		}
		finally {
			if(rs != null) {
				try {
					rs.close();
				}
				catch(SQLException e) {
					e.printStackTrace();
					log.error("ResultSet couldn't close after getting a bank account with its bank account id\n");
				}
			}
		}
		return ba;
	}

	@Override
	public int updateBankAccountBalance(BankAccount ba) {
		String sql = "update bank_account set balance = ? where baid = ?";
		int bankAccountsUpdated = 0;
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setDouble(1, ba.getBalance());
			ps.setInt(2, ba.getBaid());
			bankAccountsUpdated = ps.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			log.error("SQLException when updating a bank account\n");
		}
		return bankAccountsUpdated;
	}

	@Override
	public int deleteBankAccount(BankAccount ba) {
		// TODO Auto-generated method stub
		return 0;
	}

}
