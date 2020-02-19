package com.revature.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.UserAccount;
import com.revature.util.ConnectionUtil;

public class UserAccountDaoImpl implements UserAccountDao {
	
	private static Logger log = Logger.getRootLogger();

	@Override
	public UserAccount createUserAccount(UserAccount ua) {
		String sql = "{call add_user_account(?, ?, ?)}";
		ResultSet rs = null;
		UserAccount createdUserAccount = null;
		try(Connection c = ConnectionUtil.getConnection();
				CallableStatement cs = c.prepareCall(sql)) {
			cs.setString(1, ua.getUsername());
			cs.setString(2, ua.getEmail());
			cs.setString(3, ua.getPassword());
			cs.execute();
			rs = cs.getResultSet();
			while(rs.next()) {
				createdUserAccount = new UserAccount();
				createdUserAccount.setUaid(rs.getInt("uaid"));
				createdUserAccount.setUsername(rs.getString("username"));
				createdUserAccount.setEmail(rs.getString("email"));
				createdUserAccount.setPassword(rs.getString("pw"));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if(rs != null)
					rs.close();				
			}
			catch(SQLException e) {
				e.printStackTrace();
				log.error("SQLException when creating user account\n");
			}
		}
		return createdUserAccount;
	}

	@Override
	public List<UserAccount> getAllUserAccounts() {
		String sql = "select * from user_account";
		
		List<UserAccount> users = new ArrayList<>();
		
		try (Connection c = ConnectionUtil.getConnection(); 
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql);) {
			
			while(rs.next()) {
				UserAccount ua = new UserAccount();
				ua.setUaid(rs.getInt("uaid"));
				ua.setUsername(rs.getString("username"));
				ua.setEmail(rs.getString("email"));
				ua.setPassword(rs.getString("pw"));
				users.add(ua);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQLException when getting all user accounts\n");
		}
		return users;
	}

	@Override
	public UserAccount getUserAccountByUsername(String login) {
		String sql = "select * from user_account where username = ?";
		UserAccount ua = null;
		ResultSet rs = null;
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, login);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ua = new UserAccount();
				ua.setUaid(rs.getInt("uaid"));
				ua.setUsername(rs.getString("username"));
				ua.setEmail(rs.getString("email"));
				ua.setPassword(rs.getString("pw"));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
			log.error("SQLException when getting a user account by a username\n");
		}
		finally {
			try {
				if(rs != null) {
					rs.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
				log.error("ResultSet couldn't close after getting user account by its username\n");
			}
		}
		return ua;
	}
	
	@Override
	public UserAccount getUserAccountByEmail(String login) {
		String sql = "select * from user_account where email = ?";
		UserAccount ua = null;
		ResultSet rs = null;
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, login);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ua = new UserAccount();
				ua.setUaid(rs.getInt("uaid"));
				ua.setUsername(rs.getString("username"));
				ua.setEmail(rs.getString("email"));
				ua.setPassword(rs.getString("pw"));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
			log.error("SQLException when getting a user account by an email\n");
		}
		finally {
			try {
				if(rs != null) {
					rs.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
				log.error("ResultSet couldn't close after getting a user account by its email\n");
			}
		}
		return ua;
	}

	@Override
	public int updateUserAccount(UserAccount ua) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteUserAccount(UserAccount ua) {
		// TODO Auto-generated method stub
		return 0;
	}

}
