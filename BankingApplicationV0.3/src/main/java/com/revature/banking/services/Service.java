package com.revature.banking.services;

import com.revature.banking.dao.BankAccountDao;
import com.revature.banking.dao.UserDao;
import com.revature.banking.services.security.SecurityService;

public class Service {
	protected SecurityService secService = null;
	protected UserDao uDao = null;
	protected BankAccountDao baDao = null;

	protected Service() {
		super();
	}

	public void setSecurityService(final SecurityService secService) {
		this.secService = secService;
	}

	public void setUserDao(final UserDao uDao) {
		this.uDao = uDao;
	}

	public void setBankAccountDao(final BankAccountDao baDao) {
		this.baDao = baDao;
	}
}
