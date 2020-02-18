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
	
	public User login(String un,String pw) {
		return uDao.login);
	}
	
	
	public int createUserAccount(String un, String pw, String em,String t) {
		Random rand = new Random();
		long tempidu = 0;
		long tempida = 0;		
		Account tempa = new Account(0,t);
		User tempu = new User(un,tempidu,pw,em,tempa);
		
		return 0;
	}
}
