package com.revature.banking.services;

import com.revature.banking.security.SecurityService;

public class Service {
	protected SecurityService ss = null;

	public void setSecurityService(final SecurityService ss) {
		this.ss = ss;
	}
}
