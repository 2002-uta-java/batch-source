package com.project0.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project0.models.Account;
import com.project0.models.Customer;
import com.project0.util.ConnectionUtil;

public class AccountDaoImpl implements AccountDao {

	@Override
	public List<Account> getAccounts() {
		String sql = "select * from account";
		
		List<Account> accounts = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnection();
				Statement s = conn.createStatement();
				ResultSet rs = s.executeQuery(sql)){
			
			while(rs.next()) {
				
				int accId = rs.getInt("acc_id");
				int custId = rs.getInt("cust_id");
				String accType = rs.getString("acc_type");
				double balance = rs.getDouble("balance");
				Account a = new Account(accId, custId, accType, balance );
				accounts.add(a);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public Account getAccountByAccId(int id) {
		String sql = "select * from account where acc_id = ?";
		Account a = null;
		ResultSet rs = null;
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);){
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int accId = rs.getInt("acc_id");
				int custId = rs.getInt("cust_id");
				String accType = rs.getString("acc_type");
				double balance = rs.getDouble("balance");
				a = new Account(accId, custId, accType, balance);
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
		
		return a;
	}
	
	@Override
	public Account getAccountByCustId(int id) {
		String sql = "select * from account where cust_id = ?";
		Account a = null;
		ResultSet rs = null;
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);){
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int accId = rs.getInt("acc_id");
				int custId = rs.getInt("cust_id");
				String accType = rs.getString("acc_type");
				double balance = rs.getDouble("balance");
				a = new Account(accId, custId, accType, balance);
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
		
		return a;
	}

//	@Override
//	public int createAccount(Account a) {
//		String sql = "insert into account (cust_id, acc_type, balance) values (?, ?, ?)";
//		int accountsCreated = 0;
//		
//		try(Connection conn = ConnectionUtil.getConnection();
//				PreparedStatement ps = conn.prepareStatement(sql)){
//			
//			ps.setInt(1, a.getCustId());
//			ps.setString(2, a.getAccType());
//			ps.setDouble(3, a.getBalance());
//		
//			accountsCreated = ps.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return accountsCreated;
//	}

	@Override
	public int updateAccount(Account a) {
		String sql = "update account set balance = ? where acc_id = ?";
		int accountsUpdated = 0;
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			
			ps.setDouble(1, a.getBalance());
			ps.setInt(2, a.getAccId());
		
			accountsUpdated = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return accountsUpdated;
	}

	@Override
	public int deleteAccount(Account a) {
		String sql = "delete from account where acc_id = ?";
		int rowsDeleted = 0;
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, a.getAccId());
			rowsDeleted = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsDeleted;
	}

	@Override
	public Account createAccountWithFunction(Account a) {
		String sql = "{call add_account(?, ?, ?)}";
		ResultSet rs = null;
		
		try(Connection conn = ConnectionUtil.getConnection();
				CallableStatement cs = conn.prepareCall(sql)){
			cs.setInt(1, a.getCustId());;
			cs.setString(2, a.getAccType());
			cs.setDouble(3, a.getBalance());
		
			cs.execute();
			
			rs = cs.getResultSet();
			
			while(rs.next()) {
				int newId = rs.getInt("acc_id");
				a.setAccId(newId);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
			}
		}
	}
		
		return a;
	}
	
}
