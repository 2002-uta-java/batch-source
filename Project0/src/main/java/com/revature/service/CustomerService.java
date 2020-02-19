package com.revature.service;

import com.revature.dao.CustomerDao;
import com.revature.dao.CustomerDaoImpl;
import com.revature.model.Customer;

public class CustomerService {
	
	private CustomerDao customerdao = new CustomerDaoImpl();
	
	public Customer createCustomer(Customer c1) {
		return customerdao.createCustomerByFunction(c1);
	}
	
	public int getCustomerId(String username) {
	    return customerdao.getCustomerByUsername(username);
	    
	}
	 
	public String validationPasswordCheck(int id) {
		return customerdao.vaildationPassword(id);
	}

}
