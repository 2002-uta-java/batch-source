package com.chrandle.daos;

import java.util.List;

import com.chrandle.models.Account;
import com.chrandle.models.User;

public interface UserDao {
	public List<User> getUsers();
	public User getUserByCred(String uname, String pw); 
	public User getUserById(long id);
	public long createUser(String uname, String em, String pw);
	public int updateUser(User u);
	public int deleteUser(long uid);
	public List<Account> getUserAccounts(long id);

}
