package com.revature.services;

import com.revature.daos.UserDaos;
import com.revature.daos.UserDaosImplementation;
import com.revature.models.User;

public class UserServices {
	
	private UserDaos userDaos = new UserDaosImplementation();
	
	public User getUserByUsername(String username) {
		return userDaos.getUserByUsername(username);
	}

	public boolean createUser(User u) {
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
