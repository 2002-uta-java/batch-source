package com.revature.services;

import java.sql.SQLException;
import java.util.List;


public class UserService {
//	public List<User> getAllUsers() throws SQLException {
//		return userDAO.getAllUsers();
//	}
//	
//	public User getUserById(int userId) {
//		return userDAO.getUserById(userId);
//	}
//	
//	public User getUserByUsername(String username) {
//		return userDAO.getUserByUsername(username);
//	}
//	
//	// Take in User as parameter, check email and password, 
//	public User addUser(User newUser) {
//		System.out.println("in addUser()");
//		
//		boolean emailAvailable = true;
//		boolean usernameAvailable = true;
//		
//		System.out.println("in addUser() past booleans" +" email boolean availability " + emailAvailable +" username boolean availability " + usernameAvailable);
//		
//		if(emailAvailable && usernameAvailable) {
//			newUser = userDAO.addUser(newUser);		
//			//for(User user : userDAO.getAllUsers()) System.out.println(user);
//		}
//		else
//			newUser = null;
//		
//		return newUser;
//	}
//	
//	public boolean updateUser(User updatedUser) {
//		
//		
//		return false;
//		
//	}
//	
//	public boolean deleteUser(int userId) {
//		return  false;
//	}
//	
//	
//	public boolean isEmailAvailable(String emailAddress) {
//		System.out.println("it is currently here  in  for email ");
//		
//		User u = userDAO.getUserByEmailAddress(emailAddress);
//		
//		System.out.println("it is currently here  in email " + " " + u.toString());
//		
//		if(u.getLog_username() == null) {
//			return true;
//		}else {
//			return false;
//		}
//	}
//	
//	
//	public boolean isUsernameAvailable(String username) {
//		System.out.println("it is currently here  in service " + username);
//		User u = userDAO.getUserByUsername(username);
//		System.out.println(u);
//		if(u.getLog_username() == null) {
//			return true;
//		}else {
//			return false;
//		}
//	}
//	
//	public User loginUser(String username, String password) throws SQLException {
//		System.out.println("Made it to loginUser");
//		User u = new User();
//		//System.out.println(u.toString());
//		u = userDAO.getUserByCredentials(username, password);
//		//System.out.println(u.toString());
//		return u;
//	}
}
