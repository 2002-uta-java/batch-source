package com.revature.dao;

import java.util.List;

import com.revature.model.Customer;

public interface CustomerDao {
	
	public List<Customer> getCustomers();
	public int getCustomerById (int id);
	public int getCustomerByUsername (String username);
	public String getCustomerByPassword (String password);
	public int createCustomer(Customer c1);
	public int updateCustomer(Customer c1);
	public int deleteCustomer(Customer c1);
	public Customer createCustomerByFunction(Customer c1);
	public String vaildationPassword(int id);
	

}
