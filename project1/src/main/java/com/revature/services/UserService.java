package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImpl;

public class UserService {
	private UserDAO userDAO = new UserDAOImpl();

	public boolean checkPassword(String username, String password) {
		return userDAO.checkPassword(username, password);
	}

	public boolean isManager(String username) {
		return userDAO.isManager(username);
	}


}
