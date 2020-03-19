package com.revature.intercom;

import java.util.List;

import org.springframework.stereotype.Component;

import com.revature.models.Account;

@Component
public class AccountClientImpl implements AccountClient{

	@Override
	public List<Account> getAccountByCustomerId(int customerId) {
		return null;
	}

}
