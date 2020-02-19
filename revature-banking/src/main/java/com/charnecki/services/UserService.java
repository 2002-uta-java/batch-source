package com.charnecki.services;

import java.util.List;

import com.charnecki.daos.UserDao;
import com.charnecki.daos.UserDaoImpl;
import com.charnecki.models.User;

public class UserService {
	
	private UserDao ud = new UserDaoImpl();
	
	public List<User> getAllUsers() {
		return ud.getAllUsers();
	}
	
	public User getUserById(int id) {
		return ud.getUserById(id);
	}
	
	public User getUserByUsername(String username) {
		return ud.getUserByUsername(username);
	}
	
	public boolean checkUniqueUser(User u) {
		return ud.checkUniqueUser(u);
	}
	
	public int createUser(User u) { // change to bool later
		return ud.createUser(u);
	}
	
	public int updateUser(User u) {
		return ud.updateUser(u);
	}
	
	public int deleteUser(User u) {
		return ud.deleteUser(u);
	}
	
	
}
