package com.chrandle.daos;

import java.util.List;

import com.chrandle.models.User;

public interface UserDao {
	public List<User> getUsers();
	public User getUserById(long id);
	public int createUser(User u);
	public int updateUser(User u);
	public int deleteUser(User u);
	public int createJointAccount(User u);
}
