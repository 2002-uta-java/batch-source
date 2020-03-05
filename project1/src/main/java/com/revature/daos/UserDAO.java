package com.revature.daos;

public interface UserDAO {
	boolean checkPassword(String username, String password);

	boolean isManager(String username);
}
