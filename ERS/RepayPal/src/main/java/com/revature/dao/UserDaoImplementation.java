package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class UserDaoImplementation implements UserDao{
	
	@Override
	public User getUserByUsername(String username) {
		String sql = "select * from bank_user where username = ?";
		User user = null;
		ResultSet rs = null;
		
		try(Connection c = ConnectionUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("pwd"));
			}
		} 
		catch (SQLException e) {
			//e.printStackTrace();
		} 
		finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					//e.printStackTrace();
				}
			}
		}
		
		return user;
	}

	@Override
	public int createUser(User u) {
		String sql = "insert into bank_user(username, pwd) values(?, ?)";
		int userCreated = 0;
		
		try(Connection c = ConnectionUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
			String username = u.getUsername();
			String pwd = u.getPassword();
			ps.setString(1, username);
			ps.setString(2, pwd);
			userCreated = ps.executeUpdate();
			
		} 
		catch (SQLException e) {
			//e.printStackTrace();
		}
		
		return userCreated;
	}
	
	@Override
	public int updateUser(User u) {
		return 0;
	}

	@Override
	public int deleteUser(User u) {
		return 0;
	}

	@Override
	public List<User> getUsers() {
		String sql = "select * from bank_user";
		List<User> users = new ArrayList<>();
	
	try (Connection c = ConnectionUtil.getConnection();Statement s = c.createStatement();ResultSet rs = s.executeQuery(sql)){
		while(rs.next()) {
			//TODO
		}
		
	} 
	catch (SQLException e) {
		//e.printStackTrace();
	}
	
	return users;
	}
}
