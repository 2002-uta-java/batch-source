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
		String sql = "select * from employee where username = ?";
		User user = null;
		ResultSet rs = null;
		
		try(Connection c = ConnectionUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				user = new User();
				user.setUsername(rs.getString("username"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setPassword(rs.getString("pwd"));
				user.setManager((rs.getInt("isManager") == 1));
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
		String sql = "insert into employee(username, first_name, last_name, pwd, isManager) values(?, ?, ?, ?, ?)";
		int userCreated = 0;
		int x = 0;
		try(Connection c = ConnectionUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getFirstName());
			ps.setString(3, u.getLastName());
			ps.setString(4, u.getPassword());
			if(u.isManager())
				x = 1;
			ps.setInt(5, x);
			userCreated = ps.executeUpdate();
		} 
		catch (SQLException e) {
			//e.printStackTrace();
		}
		
		return userCreated;
	}
	
	@Override
	public int updateUser(User u) {
		String sql = "update employee set pwd = ?, first_name = ?, last_name = ?, isManager = ? where username = ?";
		int userUpdated = 0;
		int x = 0;
		try(Connection c = ConnectionUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, u.getPassword());
			ps.setString(2, u.getFirstName());
			ps.setString(3, u.getLastName());
			if(u.isManager())
				x = 1;
			ps.setInt(4, x);
			ps.setString(5, u.getUsername());
			
			userUpdated = ps.executeUpdate();
		} 
		catch (SQLException e) {
			//e.printStackTrace();
		}
		
		return userUpdated;
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
