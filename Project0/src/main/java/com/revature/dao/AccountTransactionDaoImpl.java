package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.model.AccountTransaction;
import com.revature.utli.ConnectionUtil;

public class AccountTransactionDaoImpl implements AccountTransactionDao {

	@Override
	public int getaccountTransactionById(int id) {
		String sql = "select trans_id from account_transaction where trans_id = ?)";
		
		ResultSet rs = null;
		int a = 0;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
		   ps.setInt(1, id);
		   rs = ps.executeQuery();
		   while(rs.next()) {
			   a = rs.getInt("account_id");
		   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try { if (rs!=null) {
				rs.close();
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return a;
	}

	@Override
	public AccountTransaction getAccountStatement(AccountTransaction t) {
		String sql = "{call state(?)}";
		
		ResultSet rs = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				CallableStatement cs = c.prepareCall(sql)) {
			cs.setInt(1, t.getAccountNumber());
			cs.execute();
			
			rs = cs.getResultSet();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if (rs!=null) {
				rs.close();
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return t;
	}

	@Override
	public AccountTransaction createAccountTransactionByFunction(AccountTransaction t) {
		String sql = "{call withdraw_record(?, ?, ?, ?)}";
		
		ResultSet rs = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				CallableStatement cs = c.prepareCall(sql)) {
			cs.setInt(1, t.getAccountNumber());
		    cs.setString(2, t.getTransactionType());
		    cs.setDouble(3, t.getTransactionAmount());
		    cs.setString(4, t.getTransactionDate());
			cs.execute();
			
			rs = cs.getResultSet();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if (rs!=null) {
				rs.close();
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return t;
	}

}
