package com.revature.daos;

import java.util.List;

import com.revature.User;

public interface UserDao {
	
	public List<User> getUsers();
	public User getUserByID(int id);
	public int createUser(User u);
	public int updateUser(User u);
	public int deleteUser(User u);
}
