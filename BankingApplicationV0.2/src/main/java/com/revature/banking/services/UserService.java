package com.revature.banking.services;

import com.revature.banking.dao.UserDao;

public class UserService {
	private UserDao ud = null;

	public UserService() {
		super();
	}

	public UserService(final UserDao ud) {
		super();
		this.setDao(ud);
	}

	public void setDao(final UserDao ud) {
		this.ud = ud;
	}
}
