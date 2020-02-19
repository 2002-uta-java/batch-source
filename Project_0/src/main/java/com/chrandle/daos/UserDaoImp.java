package com.chrandle.daos;

import java.util.List;

import com.chrandle.models.Account;
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
		String query = "select * from users";
		List<User> users = new ArrayList<>();
		
		try(Connection userConn = ConnectionUtil.getConnection()){
			Statement userStmnt = userConn.createStatement();
			ResultSet userRsltSt = userStmnt.executeQuery(query);
			
			while(userRsltSt.next()) {
				long userid = userRsltSt.getLong("UserID");
				String username = userRsltSt.getString("UserName");
				String password = userRsltSt.getString("password");
				String email = userRsltSt.getString("UserEmail");
				User u = new User(username,userid,password, email);
				users.add(u);
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
		return null;
	}

	@Override
	public int createUser(User u) {

			return -1;
	}

	@Override
	public int updateUser(User u) {

		return -1;
	}

	@Override
	public int deleteUser(User u) {
		// TODO Auto-generated method stub
		return 0;
	}


}
