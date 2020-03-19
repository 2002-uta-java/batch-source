package com.revature.intercom;

import java.util.List;

import org.springframework.stereotype.Component;

import com.revature.models.Account;

@Component
public class AccountClientIimpl implements AccountClient {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Account> getAccountsByCustomerId(int id) {
		return null;
	}

}
