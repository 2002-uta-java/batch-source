package com.revature.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImplementation;
import com.revature.model.User;

public class UserService {

	private  UserDao userDao = new UserDaoImplementation();
	
	private static Logger log = Logger.getLogger(UserService.class);
	
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

	public User validateUser(String username, String password) {
		User user = userDao.getUserByUsername(username);
		if(user != null && !(password.equals("")||password.equals(" "))) {
			if(password.equals(user.getPassword()))
				return user;
			else {
				log.error("\nInvalid username or password.\n");
				return null;
			}
		}
		log.error("Username not found\n");
		return null;
	}
	
	
	public boolean createUser(User u) {
		if(u == null)
			return false;
		List<User> users = userDao.getUsers();
		for(User user: users) {
			if(u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
				log.error("User already exists, please sign in...");
				return false;
			}	
		}
		int userCreated = userDao.createUser(u);
		return (userCreated != 0);
	}
	
	public boolean updateUser(User u) {
		int userUpdated = userDao.updateUser(u);
		return (userUpdated != 0);
	}
	
	public List<User> getUsers(){
		List<User> users = userDao.getUsers();
		log.debug(users);
		return users;
	}
}
