package com.project0.daos;

import java.util.List;

import com.project0.models.Customer;

public interface CustomerDao {
	
	public Customer credential(String identifier, String password);
	public List<Customer> getCustomers();
	public Customer getCustomerById(int id);
//	public int createCustomer(Customer c);
	public int updateCustomer(Customer c);
	public int deleteCustomer(Customer c);
	public Customer createCustomerWithFunction(Customer c);
}
