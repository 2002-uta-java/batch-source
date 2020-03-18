package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
		super();
		accounts.add(new Account(1, 348.24, 1));
		accounts.add(new Account(2, 348.24, 1));
		accounts.add(new Account(3, 348.24, 2));
		accounts.add(new Account(4, 348.24, 3));
		accounts.add(new Account(5, 348.24, 3));
		accounts.add(new Account(6, 348.24, 4));
	}

	@GetMapping
	public List<Account> getAllAccount() {
		log.info("Getting all accounts");
		return new ArrayList<>(accounts);
	}

	@GetMapping("/{id}")
	public Account getAccountBId(@PathVariable("id") final int accountId) {
		log.info("Getting Acount: " + accountId);

		for (final Account a : accounts) {
			if (a.getAccountId() == accountId)
				return a;
		}

		return null;
	}

	@GetMapping("/customer/{id}")
	public List<Account> getAccountsByCusomterId(@PathVariable("id") final int id) {
		log.info("GET /accounts/customer/{" + id + "}");

		return accounts.stream().filter(acct -> acct.getCustomerId() == id).collect(Collectors.toList());
	}

}
