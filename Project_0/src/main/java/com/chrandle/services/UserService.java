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
	
}
