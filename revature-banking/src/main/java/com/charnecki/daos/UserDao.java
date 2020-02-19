package com.charnecki.daos;

import java.util.List;

import com.charnecki.models.User;

public interface UserDao {
	
	public List<User> getAllUsers();
	public User getUserById(int id);
	public int createUser(User u);
	public int updateUser(User u);
	public int deleteUser(User u);
	
	public User getUserByUsername(String username);
	public boolean checkUniqueUser(User u);
	
}
