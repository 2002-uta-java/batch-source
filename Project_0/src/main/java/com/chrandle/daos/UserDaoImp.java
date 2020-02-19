package com.chrandle.daos;

import java.util.List;

import com.chrandle.models.Account;
import com.chrandle.models.User;
import com.chrandle.util.ConnectionUtil;

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
	public User getUserByCred(String uname, String pw) {
		String query = "select * from users where (username = ? and password = ?)";
		ResultSet result  = null;
		
		try(Connection userConn = ConnectionUtil.getConnection()){
			PreparedStatement pstatement =  userConn.prepareStatement(query);
			pstatement.setString(1, uname);
			pstatement.setString(2, pw);
			result = pstatement.executeQuery();
			
			if(result.next()) {
				long userid = result.getLong("UserID");
				String username = result.getString("UserName");
				String password = result.getString("password");
				String email = result.getString("UserEmail");
				User u = new User(username,userid,password, email);
				return u;
			} else return null;
		} catch (SQLException e) {
			//TODO: send exception message to log?
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public User getUserById(long id) {
			String query = "select * from users where userid = ?";
			User users = null;;
			ResultSet result  = null;
			
			try(Connection userConn = ConnectionUtil.getConnection()){
				PreparedStatement pstatement =  userConn.prepareStatement(query);
				pstatement.setLong(1, id);
				result = pstatement.executeQuery();
				
				if(result.next()) {
					long userid = result.getLong("UserID");
					String username = result.getString("UserName");
					String password = result.getString("password");
					String email = result.getString("UserEmail");
					User u = new User(username,userid,password, email);
					return u;
				} else return null;
			} catch (SQLException e) {
				//TODO: send exception message to log?
				System.out.println(e.getMessage());
				e.printStackTrace();
				return null;
			}
	}
	

	@Override
	public long createUser(String uname, String em, String pw) {
		String sql = "{call create_user(?, ?, ?)}";
		ResultSet result = null;
		try(Connection userConn = ConnectionUtil.getConnection()){
			CallableStatement pcall =  userConn.prepareCall(sql);
			pcall.setString(1, uname);
			pcall.setString(2, em);
			pcall.setString(3, pw);
			pcall.execute();
			result = pcall.getResultSet();
			if (result.next()) {
				return result.getLong(1);
			} else return 0;
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateUser(User u) {
		String sql = "{select create_user(?, ?, ?)}";
		ResultSet results = null;
		return -1;
	}

	@Override
	public int deleteUser(long uid) {
		String sql = "{call delete_user(?)}";
		ResultSet result = null;
		try(Connection userConn = ConnectionUtil.getConnection()){
			CallableStatement pcall =  userConn.prepareCall(sql);
			pcall.setLong(1, uid);
			pcall.execute();
			return 0;
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public List<Account> getUserAccounts(long id) {
		String sql = "select * from accounts a3 where accountid  in " + 
				"(select accountid from users  u1 " + 
				"join Authorizations  au2 " + 
				"on (u1.userid  = au2.userid and u1.userid = ?))";
		List<Account> accounts = new ArrayList<>();
		ResultSet result = null;
		try(Connection userConn = ConnectionUtil.getConnection()){
			PreparedStatement pstatement =  userConn.prepareStatement(sql);
			pstatement.setLong(1, id);
			result = pstatement.executeQuery();
			Account a = null;
			while(result.next()) {
				a = new Account();
				a.setAccountid(result.getLong("AccountID"));
				a.setBalance(result.getDouble("Balance"));
				a.setType(result.getString("Type"));
				accounts.add(a);
			}
			return accounts;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}

	}




}
