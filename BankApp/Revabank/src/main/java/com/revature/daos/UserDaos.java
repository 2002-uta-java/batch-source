package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDaos {
	public List<User> getUsers();
	public User getUserById(int id);
	public boolean createUser(User u);
	public boolean deleteUser(User u);
}
