package com.hylicmerit.dao;

import com.hylicmerit.models.User;

public interface UserDao {

	public User getUserById(String username);
	
	public int checkUsernameAvailability(String username);

	public int createUser(User u);

	public int updateUser(User u);

	public int deleteUser(User u);
}
