package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Account;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	private Logger log = Logger.getLogger(AccountController.class);
	
	private List<Account> accounts = new ArrayList<>();

	public AccountController() {
		accounts.add(new Account(1,348.24,1));
		accounts.add(new Account(2,1296.34,1));
		accounts.add(new Account(3,20.65,2));
		accounts.add(new Account(4,4826.24,3));
		accounts.add(new Account(5,1000.24,3));
		accounts.add(new Account(6,3726.99,4));
	}
	
	@GetMapping
	public List<Account> getAllAccounts(){
		log.info("GET /accounts - getting all accounts");
		return new ArrayList<>(accounts);
	}
	
	@GetMapping("/{id}")
	public Account getAccountById(@PathVariable("id")int accountId) {
		log.info("GET /accounts/{id} - getting account with id: "+accountId);
		Account a = null;
		for(Account account: accounts) {
			if(account.getAccountId()==accountId) {
				a = account;
			}
		}
		return a;
	}
	
	
}
