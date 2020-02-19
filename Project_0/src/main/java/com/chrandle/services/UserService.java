package com.chrandle.services;

import java.util.List;
import java.util.Random;

import com.chrandle.daos.UserDao;
import com.chrandle.daos.UserDaoImp;
import com.chrandle.models.Account;
import com.chrandle.models.User;

public class UserService {
	private UserDao uDao = new UserDaoImp();
	public List<User> getUsers(){
		return uDao.getUsers();
	}
	
	public User userLogin(String name, String pw) {
		return uDao.getUserByCred(name,pw);
	}
	
	public User getUserById(long id) {
		return uDao.getUserById(id);
	}
	public long createUser(String uname, String em, String pw){
		return uDao.createUser(uname, em, pw);
	}
	
	public List<Account> viewAccounts(long id){
		return uDao.getUserAccounts(id);
	}
	
	public void deleteUser(long uid) {
		 uDao.deleteUser(uid);
		 return;
	}
	

}
