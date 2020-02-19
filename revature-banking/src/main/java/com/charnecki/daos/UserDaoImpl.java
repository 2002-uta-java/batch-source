package com.charnecki.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.charnecki.models.Account;
import com.charnecki.models.User;
import com.charnecki.services.AccountService;
import com.charnecki.util.ConnectionUtil;

public class UserDaoImpl implements UserDao {

	String userTable = "_user"; // Name of user table in DB
	AccountService as = new AccountService();
	private static Logger log = Logger.getRootLogger();
	
	@Override
	public List<User> getAllUsers() {

		List<User> users = new ArrayList<User>();
		String sql = "select * from {oj " + userTable + " u left join user_account ua on (u.id=ua.user_id)}";
		
		try (Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql);){
			
			int prevId = 0;
			
			while(rs.next()) {
				
				User u = null;
				
//				log.info(rs.getInt("id"));
				
				if(rs.getInt("id") == prevId) {
					u = users.get(users.size()-1);
				} else {
					u = new User();
					u.setId(rs.getInt("id"));
					u.setUsername(rs.getString("username"));
					u.setEmail(rs.getString("email"));
					
					users.add(u);
				}
				
				prevId = rs.getInt("id");
				
				if (rs.getInt("account_id") != 0) {
					List<Account> accounts = u.getAccounts();
					accounts.add(as.getAccountById(rs.getInt("account_id")));
					
					u.setAccounts(accounts);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}

	@Override
	public User getUserById(int id) {
		
		User u = null;
		
		String sql = "select * from {oj " + userTable + " u left join user_account ua on (u.id=ua.user_id)} where u.id=?";
		ResultSet rs = null;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				if (u == null) {					
					u = new User();
					u.setId(id);
					u.setUsername(rs.getString("username"));
					u.setEmail(rs.getString("email"));
				}
				
				List<Account> accounts = u.getAccounts();
				Account acct = as.getAccountById(rs.getInt("account_id"));
				if (acct != null) {
					accounts.add(acct);					
				}
				
				u.setAccounts(accounts);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		return u;
	}
	
	@Override
	public boolean checkUniqueUser(User u) {
		
		String sql = "select * from " + userTable + " where username=? OR email=?";
		ResultSet rs = null;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getEmail());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				if (rs.getInt("id") > 0)
				{
					return false;
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		return true;
	}

	@Override
	public int createUser(User u) {

		String sql = "insert into " + userTable + " (username, email, pass) values (?,?,?) ";
		ResultSet rs = null;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getPassword());
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			while (rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		return 0;
	}

	@Override
	public int updateUser(User u) {
		
		String sql = "update " + userTable + " set username=?, email=?, pass=? where id=?";
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getPassword());
			ps.setInt(4, u.getId());
			
			return ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int deleteUser(User u) {
		
		String sql = "delete from " + userTable + " where id=?";
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			
			for(Account a:u.getAccounts()) {
				as.deleteAccount(a);
			}
			
			
			ps.setInt(1, u.getId());
			int affectedRows = ps.executeUpdate();
			
			return affectedRows;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public User getUserByUsername(String username) {
		
		User u = null;
		
		String sql = "select * from _user u left join user_account ua on (u.id=ua.user_id) where u.username=?";
		ResultSet rs = null;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				if (u == null) {					
					u = new User();
					u.setId(rs.getInt("id"));
					u.setUsername(rs.getString("username"));
					u.setEmail(rs.getString("email"));
					u.setPassword(rs.getString("pass"));
				}
				
				List<Account> accounts = u.getAccounts();
				Account acct = as.getAccountById(rs.getInt("account_id"));
				if (acct != null) {
					accounts.add(acct);					
				}
				
				u.setAccounts(accounts);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		return u;
	}

}
