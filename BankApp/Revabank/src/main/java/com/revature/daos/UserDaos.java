package com.revature.daos;

import com.revature.models.User;

public interface UserDaos {
	public User getUserByUsername(String username);
	public int createUser(User u);
	public int updateUser(User u);
	public int deleteUser(User u);
}
