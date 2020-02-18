package com.revature.bankingapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.bankingapp.model.UserAccount;

public class UserAccountDaoImpl implements UserAccountDAO {

	public List<UserAccount> getUserAccount() {
		String selectAll = "select * from UserAccount";
		List<UserAccount> UserAccount = new ArrayList<>();
		
		try (Connection databaseConnection = ConnectionUtil.getConnection();
			Statement returnAll = databaseConnection.createStatement();
			ResultSet rs = returnAll.executeQuery(selectAll)) {
			
			while (rs.next()) {
				// For each entry in rs, add to bankAccounts
				int uaId = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email    = rs.getString("email");
				String phone    = rs.getString("phoneNumber");
				UserAccount ua = new UserAccount(uaId, username, password, email, phone);
				UserAccount.add(ua);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return UserAccount;
	}

	public UserAccount getUserAccountById(int id) {
		String selectOneAccount = "select * from UserAccount where id = ?";
		UserAccount ua = null;
		ResultSet rs = null;
		
		try (Connection databaseConnection = ConnectionUtil.getConnection();
			PreparedStatement returnAll = databaseConnection.prepareStatement(selectOneAccount)) {
			returnAll.setInt(1, id);
			rs = returnAll.executeQuery();
			
			while (rs.next()) {
				int uaId = rs.getInt("id");
				String uname = rs.getString("username");
				String pass  = rs.getString("password");
				String email = rs.getString("email");
				String phone = rs.getString("phoneNumber");
				ua = new UserAccount(uaId, uname, pass, email, phone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ua;
	}
	
	public UserAccount getUserAccountByUsername(String username) {
		String selectOneAccount = "select * from UserAccount where username = ?";
		UserAccount ua = null;
		ResultSet rs = null;
		
		try (Connection databaseConnection = ConnectionUtil.getConnection();
			PreparedStatement returnAll = databaseConnection.prepareStatement(selectOneAccount)) {
			returnAll.setString(1, username);
			rs = returnAll.executeQuery();
			
			while (rs.next()) {
				int uaId        = rs.getInt("id");
				String username1 = rs.getString("username");
				String password = rs.getString("password");
				String email    = rs.getString("email");
				String phone    = rs.getString("phoneNumber");
				ua = new UserAccount(uaId, username1, password, email, phone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ua;
	}
	

	public int createUserAccount(UserAccount ua) {
		String addUserAccount = "insert into UserAccount (id, username, password, email, phoneNumber) values (?, ?, ?, ?, ?)";
		int userAccountAdded = 0;
		
		try (Connection databaseConnection = ConnectionUtil.getConnection();
				PreparedStatement createAccount = databaseConnection.prepareStatement(addUserAccount)) {
			createAccount.setInt(1, ua.getId());
			createAccount.setString(2, ua.getuName());
			createAccount.setString(3, ua.getPassword());
			createAccount.setString(4, ua.getEmail());
			createAccount.setString(5, ua.getPhoneNumber());

			userAccountAdded = createAccount.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userAccountAdded;
	}

	public int updateUserAccount(UserAccount ua) {
		String updateUserAccount = "update UserAccount (username, password, email, phoneNumber) values (?, ?, ?, ?) where id = ?";
		int userAccounUpdated = 0;
		
		try (Connection databaseConnection = ConnectionUtil.getConnection();
				 PreparedStatement sendUpdate = databaseConnection.prepareCall(updateUserAccount)) {
			sendUpdate.setString(1, ua.getuName());
			sendUpdate.setString(2, ua.getPassword());
			sendUpdate.setString(3, ua.getEmail());
			sendUpdate.setString(4, ua.getPhoneNumber());
			sendUpdate.setInt(5, ua.getId());

			userAccounUpdated = sendUpdate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userAccounUpdated;
	}

	public int deleteUserAccount(UserAccount ua) {
		String deleteUserAccount = "delete from UserAccount where id = ?";
		int userAccountDeleted = 0;
		
		try (Connection databaseConnection = ConnectionUtil.getConnection();
				PreparedStatement sendDelete = databaseConnection.prepareStatement(deleteUserAccount)) {
			sendDelete.setInt(1, ua.getId());
			
			userAccountDeleted = sendDelete.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userAccountDeleted;
	}

}
