package com.revature.services;

import com.revature.daos.UserDao;
import com.revature.daos.UserDaoImpl;

import java.util.List;

import com.revature.User;

public class UserService {
	private static UserDao userDao = new UserDaoImpl();
	
	public static void main(String[] args) {
		List<User> u = userDao.getUsers();
		for(User user: u) {
			System.out.println(user);
		}
		System.out.println("\n" + userDao.getUserByID(3));
	}
}
