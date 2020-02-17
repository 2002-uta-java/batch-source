package com.chrandle.daos;

import java.util.List;

import com.chrandle.models.User;
import com.chrandle.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImp implements UserDao {

	@Override
	//Won't need this in final!
	public List<User> getUsers() {
		String query = "select * from Users";
		List<User> users = new ArrayList<>();
		
		try(Connection userConn = ConnectionUtil.GetConnection()){
			Statement userStmnt = userConn.createStatement();
			ResultSet userRsltSt = userStmnt.executeQuery(query);
			
			while(userRsltSt.next()) {
				long userID = userRsltSt.getLong("UserID");
				String userName = userRsltSt.getString("UserName");
				String password = userRsltSt.getString("password");
				
				User u = new User(userName,password,userID);
			}
		} catch (SQLException e) {
			//TODO: send exception message to log?
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return users;
	}

	@Override
	public User getUserById(long id) {
		// TODO Auto-generated method stub
		String query = "select * from Users";
		
		try(Connection userConn = ConnectionUtil.GetConnection()){
			Statement userStmnt = userConn.createStatement();
			ResultSet userRsltSt = userStmnt.executeQuery(query);
			
			while(userRsltSt.next()) {

				
			}
		} catch (SQLException e) {
			//TODO: send exception message to log?
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public int createUser(User u) {
		// TODO Auto-generated method stub
		String query = "select * from Users";
		
		try(Connection userConn = ConnectionUtil.GetConnection()){
			Statement userStmnt = userConn.createStatement();
			ResultSet userRsltSt = userStmnt.executeQuery(query);
			
			while(userRsltSt.next()) {

				
			}
		} catch (SQLException e) {
			//TODO: send exception message to log?
			System.out.println(e.getMessage());
			e.printStackTrace();
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

	@Override
	public int createJointAccount(User u) {
		// TODO Auto-generated method stub
		return 0;
	}

}
