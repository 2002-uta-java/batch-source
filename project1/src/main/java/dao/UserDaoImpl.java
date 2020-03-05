package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.User;
import util.ConnectionUtility;

public class UserDaoImpl implements UserDao{

	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public User getUserByUserNameAndPassword(String n, String p) {
		String sql = "select * from employee where userName = ? and userPassword = ?";
		User u = null;
		ResultSet rs =null;
		
		try (Connection c = ConnectionUtility.getConnection(); 
			 PreparedStatement ps = c.prepareStatement(sql);){
		ps.setString(1, n);
		ps.setString(2, p);
		rs = ps.executeQuery();
		
		while(rs.next()) {
			
			int userID = rs.getInt("userID");
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("lastName");
			String userName = rs.getString("userName");
			String userPassword = rs.getString("userPassword");
			String userRole = rs.getString("userRole");

			u = new User(userID,firstName,lastName,userName,userPassword,userRole);
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return u;
		
	}

	@Override
	public User getUserByUserID(int i) {
		String sql = "select * from employee where userID = ?";
		User u = null;
		ResultSet rs =null;
		
		try (Connection c = ConnectionUtility.getConnection(); 
			 PreparedStatement ps = c.prepareStatement(sql);){
		ps.setInt(1, i);
		rs = ps.executeQuery();
		
		while(rs.next()) {
			
			int userID = rs.getInt("userID");
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("lastName");
			String userName = rs.getString("userName");
			String userPassword = rs.getString("userPassword");
			String userRole = rs.getString("userRole");

			u = new User(userID,firstName,lastName,userName,userPassword,userRole);
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return u;
	}

	@Override
	public User getManager(String n, String p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getManagers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
