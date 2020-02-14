package com.revature.services;

import java.util.List;

import com.revature.daos.UserDaos;
import com.revature.daos.UserDaosImplementation;
import com.revature.models.User;

public class UserServices {
	
	private UserDaos userDaos = new UserDaosImplementation();
	
	public List<User> getUsers() {
		return userDaos.getUsers();
	}

	public User getUserById(int id) {
		return userDaos.getUserById(id);
	}

	public boolean createUser(User u) {
		return userDaos.createUser(u);
	}

	public boolean deleteUser(User u) {
		return userDaos.deleteUser(u);
	}

}
