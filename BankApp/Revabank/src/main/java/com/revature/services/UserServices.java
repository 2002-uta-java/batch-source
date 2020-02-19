package com.revature.services;

import java.util.List;

import com.revature.daos.UserDaos;
import com.revature.daos.UserDaosImplementation;
import com.revature.models.User;

public class UserServices {
	
	private UserDaos userDaos = new UserDaosImplementation();
	
	public User getUserByUsername(String username) {
		return userDaos.getUserByUsername(username);
	}

	public User validateUser(String username, String password) {
		User user = userDaos.getUserByUsername(username);
		if(user != null) {
			if(password.equals(user.getPassword()))
				return user;
			else {
				System.out.println("\nInvalid username or password.\n" + user +"\n" + password + username);
				return null;
			}
		}
		System.out.println("Username not found\n");
		return null;
	}
	
	
	public boolean createUser(User u) {
		if(u == null)
			return false;
		List<User> users = userDaos.getUsers();
		for(User user: users) {
			if(u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
				System.out.println("User already exists, please sign in...");
				return false;
			}	
		}
		int userCreated = userDaos.createUser(u);
		if(userCreated != 0) {
			return true;
		}
		return false;
	}
	
	public boolean updateUser(User u) {
		int userUpdated = userDaos.updateUser(u);
		if(userUpdated != 0) {
			return true;
		}
		return false;
	}

	public boolean deleteUser(User u) {
		int deleteCreated = userDaos.deleteUser(u);
		if(deleteCreated != 0) {
			return true;
		}
		return false;
	}

}
