package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.UserAccount;
import com.revature.util.ConnectionUtil;

public class UserAccountDaoImpl implements UserAccountDao{
	
	public UserAccount createUserAccount(UserAccount ua) {
		
		String sql = "{call add_user_account(?,?,?,?)}";
		
		ResultSet rs = null;
		
		try (Connection con = ConnectionUtil.getConnection();
			CallableStatement cs = con.prepareCall(sql)){
			
			cs.setInt(1, ua.getClientId());
			cs.setString(2, ua.getUserName());
			cs.setString(3, ua.getEmail());
			cs.setString(4, ua.getPassword());
			
			cs.execute();
			
			rs = cs.getResultSet();
			
			while (rs.next()) {
				int newId = rs.getInt("id");
				ua.setId(newId);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return ua;
	}

	@Override
	public UserAccount getUserAccount(String identifier, String password) {
				
		UserAccount ua = new UserAccount();
		
		String sql = "select * from user_account ua where ((ua.username = ? and ua.client_password = ?) or (ua.email = ? and ua.client_password = ?))";
		
		ResultSet rs = null;
		
		try (Connection con = ConnectionUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1, identifier);
			ps.setString(2, password);
			ps.setString(3, identifier);
			ps.setString(4, password);
			
			ps.execute();
			
			rs = ps.getResultSet();
			
			while (rs.next()) {
				ua.setId(rs.getInt("id"));
				ua.setClientId(rs.getInt("client_id"));
				ua.setUserName(rs.getString("username"));
				ua.setEmail(rs.getString("email"));
				ua.setPassword(rs.getString("client_password"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return ua;
	}

}
