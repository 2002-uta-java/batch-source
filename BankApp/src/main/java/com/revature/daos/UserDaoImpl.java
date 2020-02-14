package com.revature.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.User;
import com.revature.util.ConnectionUtil;

public class UserDaoImpl implements UserDao{

	@Override
	public List<User> getUsers() {
		String sql = "select * from bank_user";
		List<User> users = new ArrayList<>();
		
		try (Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql)){
			
			while(rs.next()) {
				int userID = rs.getInt("account_id");
				String name = rs.getString("firstname") + " " +rs.getString("lastname");
				String email = rs.getString("email");
				String pwd = rs.getString("pwd");
				//User u = new User(userID, name, email, pwd);
				users.add(new User(userID, name, email, pwd));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User getUserByID(int id) {
		String sql = "select * from bank_user where account_id = ?";
		User u = null;
		ResultSet rs = null;
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				int userID = rs.getInt("account_id");
				String name = rs.getString("firstname") + " " +rs.getString("lastname");
				String email = rs.getString("email");
				String pwd = rs.getString("pwd");
				u = new User(userID, name, email, pwd);
			}
			
		}catch(SQLException e) {
			e.getStackTrace();
		}
		
		return u;
	}

	@Override
	public int createUser(User u) {
		String sql = "insert into bank_user (account_id, firstname, lastname, email, pwd) values(?,?,?,?,?)";
		ResultSet rs = null;
		
		try(Connection c = ConnectionUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, u.getId());
			ps.setString(2, u.getName().split(" ")[0]);
			ps.setString(3, u.getName().split(" ")[1]);
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getPassword());
			rs = ps.executeQuery();
			return 1;
			
		}catch(SQLException e) {
			e.getStackTrace();
			}
		return 0;
	}

	@Override
	public int updateUser(User u) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteUser(User u) {
		// TODO Auto-generated method stub
		return 0;
	}
	public User createUserbyFunction(User u) {
		String sql = "{call add_user(?,?)}";
		try(Connection c = ConnectionUtil.getConnection(); CallableStatement cs = c.prepareCall(sql)){
			cs.setString(1, "");
			cs.setString(2, " ");
			cs.execute();
			ResultSet rs = cs.getResultSet();
			while(rs.next()) {
				int newID = rs.getInt("account_id");
				u.setId(newID);
			}
			
		}catch(SQLException e) {
			e.getStackTrace();
		}
		return u;
	}
	
}
