package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.intercom.AccountClient;
import com.revature.models.Account;
import com.revature.models.Customer;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	private Logger log = Logger.getLogger(CustomerController.class);

	private List<Customer> customers = new ArrayList<>();

	@Autowired
	private AccountClient accountClient;

	public CustomerController() {
		customers.add(new Customer(1, "Sally Jenkins", "sjenkins@gmail.com"));
		customers.add(new Customer(2, "Paul Newton", "pjnewton@gmail.com"));
		customers.add(new Customer(3, "John Smith", "john.smith@gmail.com"));
		customers.add(new Customer(4, "Molly Roland", "molly@gmail.com"));
	}

	@GetMapping
	public List<Customer> getAllCustomers() {
		log.info("Get /customers - getting all customers");

		return new ArrayList<>(customers);
	}

	@GetMapping("/{id}")
	public Customer getCustomerById(@PathVariable("id") final int customerId) {
		log.info("GET /customer/" + customerId);

		for (final Customer c : customers) {
			if (c.getId() == customerId) {
				final List<Account> accounts = accountClient.getAccountsByCustomerId(customerId);
				c.setAccounts(accounts);
				return c;
			}
		}

		return null;
	}
}
