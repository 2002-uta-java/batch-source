package com.hylicmerit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hylicmerit.ConnectionUtil;
import com.hylicmerit.models.User;

public class UserDaoImpl implements UserDao {

	public User getUserById(String username) {
		if(User.getInstance() == null) {
			String sql = "select * from \"user\" where username=?";
			ResultSet rs = null;
			try (Connection c = ConnectionUtil.getConnection(); 
					PreparedStatement ps = c.prepareStatement(sql);) {
				ps.setString(1, username);
				rs = ps.executeQuery();
				while (rs.next()) {
					String usr = rs.getString("username");
					String email = rs.getString("email");
					String password = rs.getString("password");
					String name = rs.getString("name");
					boolean loggedIn = rs.getBoolean("logged_in");

					User.init(name, usr, password, email, loggedIn);
				}
			} catch (SQLException e) {
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
		}
		return User.getInstance();
	}
	
	public int checkUsernameAvailability(String username) {
		String sql = "select * from \"user\" where username=?";
		ResultSet rs = null;
		int numRowsAffected = -1;
		if(username != null) {
			try (Connection c = ConnectionUtil.getConnection();
					PreparedStatement ps = c.prepareStatement(sql,
							ResultSet.TYPE_SCROLL_INSENSITIVE, 
						    ResultSet.CONCUR_READ_ONLY);){
					ps.setString(1, username);
					rs = ps.executeQuery();
					rs.last();
					numRowsAffected = rs.getRow();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return numRowsAffected;
	}

	public int createUser(User u) {
		String sql = "insert into \"user\" (username, email, \"password\", \"name\", logged_in) values(?,?,?,?,true)";
		int numRowsAffected = 0;
		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getPassword());
			ps.setString(4, u.getName());
			numRowsAffected = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numRowsAffected;
	}

	public int updateUser(User u) {
		String sql = "update \"user\" set password = ?, name = ?, logged_in = ? where username = ?";
		int numRowsAffected = 0;
		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, u.getPassword());
			ps.setString(2, u.getName());
			ps.setBoolean(3, u.isLoggedIn());
			ps.setString(4, u.getUsername());
			numRowsAffected = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numRowsAffected;
	}

	public int deleteUser(User u) {
		String sql = "delete from \"user\" where username = ?";
		int numRowsAffected = 0;
		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setString(1, u.getUsername());
			numRowsAffected = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return numRowsAffected;
	}

}
