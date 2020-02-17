package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDaosImplementation implements UserDaos{

	@Override
	public User getUserByUsername(String username) {
		String sql = "select * from bank_user where username = ?";
		User user = null;
		ResultSet rs = null;
		
		try(Connection c = ConnectionUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("pwd"));
				user.setUser_id(rs.getInt("user_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
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
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userCreated;
	}
	
	@Override
	public int updateUser(User u) {
		String sql = "update bank_user set pwd = ? , username = ? where user_id = ?";
		int userUpdated = 0;
		
		try(Connection c = ConnectionUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, u.getPassword());
			ps.setString(2, u.getUsername());	
			ps.setInt(3, u.getUser_id());
			userUpdated = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userUpdated;
	}

	@Override
	public int deleteUser(User u) {
		String sql = "delete from bank_user where user_id = ?";
		int rowsDeleted = 0;
		
		try(Connection c = ConnectionUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, u.getUser_id());
			rowsDeleted = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsDeleted;
	}

}
