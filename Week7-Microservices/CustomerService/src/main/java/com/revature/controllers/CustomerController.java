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
	
	@Autowired
	private AccountClient accountClient;
	
	private List<Customer> customers = new ArrayList<>();
	
	public CustomerController() {
		customers.add(new Customer(1, "Sally Jenkins", "sjenkins@gmail.com"));
		customers.add(new Customer(2, "Paul Newton", "pjnewton@gmail.com"));
		customers.add(new Customer(3, "John Smith", "john.smith@gmail.com"));
		customers.add(new Customer(4, "Molly Roland", "molly@gmail.com"));
	}
	
	@GetMapping
	public List<Customer> getAllCustomers(){
		log.info("GET /customers - getting all customers");
		return new ArrayList<>(customers);
	}
	
	@GetMapping("/{id}")
	public Customer getCustomerById(@PathVariable("id")int customerId) {
		Customer c = null;
		for(Customer customer: customers) {
			if(customer.getId()==customerId) {
				c = customer; 
				List<Account> accounts = accountClient.getAccountByCustomerId(customerId);
				customer.setAccounts(accounts);
			}
		}
		return c;
	}

}
