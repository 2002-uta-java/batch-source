package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.BankAccount;
import com.revature.util.ConnectionUtil;

public class BankAccountDaoImpl implements BankAccountDao{

	@Override
	public BankAccount createBankAccount(BankAccount ba) {
		
		String sql = "{call add_bank_account(?,?)}";
		
		ResultSet rs = null;
		
		try (Connection con = ConnectionUtil.getConnection();
				CallableStatement cs = con.prepareCall(sql)){
			
			cs.setString(1, ba.getAccountType());
			cs.setDouble(2, ba.getBalance());
			
			cs.execute();
			
			rs = cs.getResultSet();
			
			while(rs.next()) {
				int newId = rs.getInt("id");
				ba.setId(newId);
				int newAccountNumber = rs.getInt("account_number");
				ba.setAccountNumber(newAccountNumber);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return ba;
	}

	@Override
	public List<BankAccount> getAccounts(int userAccountId) {
		
		String sql = "select * from bank_account ba join user_to_bank_account utba on ba.id = utba.account_id where utba.user_id = " + userAccountId;
		
		List<BankAccount> bankAccounts = new ArrayList<>();
		
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)){
			
			
			while(rs.next()) {
				BankAccount ba = new BankAccount(rs.getInt("id"), rs.getString("account_type"), rs.getDouble("balance"), rs.getInt("account_number"));
				bankAccounts.add(ba);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bankAccounts;
	}


	@Override
	public int updateBalance(BankAccount ba, double newBalance) {
		
		int amountWasUpdated = 0 ;
		
		String sql = "update bank_account set balance = ? where id = ?";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setDouble(1, newBalance);
			ps.setInt(2, ba.getId());
			
			amountWasUpdated = ps.executeUpdate();
			
			if (amountWasUpdated == 1) {
				ba.setBalance(newBalance);
			} else {
				System.out.println("> THERE WAS AN ERROR...");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return amountWasUpdated;
		
	}

}
