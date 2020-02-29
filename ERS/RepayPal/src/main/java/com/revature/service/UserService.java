package com.revature.service;

import java.util.List;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImplementation;
import com.revature.model.User;

public class UserService {

private  UserDao userDao = new UserDaoImplementation();
	
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

	public User validateUser(String username, String password) {
		User user = userDao.getUserByUsername(username);
		if(user != null && !(password.equals("")||password.equals(" "))) {
			if(password.equals(user.getPassword()))
				return user;
			else {
				System.out.println("\nInvalid username or password.\n");
				return null;
			}
		}
		System.out.println("Username not found\n");
		return null;
	}
	
	
	public boolean createUser(User u) {
		if(u == null)
			return false;
		List<User> users = userDao.getUsers();
		for(User user: users) {
			if(u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
				System.out.println("User already exists, please sign in...");
				return false;
			}	
		}
		int userCreated = userDao.createUser(u);
		if(userCreated != 0) {
			return true;
		}
		return false;
	}
	
	public boolean updateUser(User u) {
		int userUpdated = userDao.updateUser(u);
		if(userUpdated != 0) {
			return true;
		}
		return false;
	}

	public boolean deleteUser(User u) {
		return false;
	}
}
