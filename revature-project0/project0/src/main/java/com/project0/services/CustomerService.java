package com.project0.services;

import java.util.List;

import com.project0.daos.CustomerDao;
import com.project0.daos.CustomerDaoImpl;
import com.project0.models.Customer;

public class CustomerService {
	
	private CustomerDao customerDao = new CustomerDaoImpl();
	
	public Customer credentialValidation(String identifier, String password) {
		return customerDao.credential(identifier, password);
	}
	
	public List<Customer> getAllCustomers(){
		return customerDao.getCustomers();
	}
	
	public Customer getCustomerById(int id) {
		return customerDao.getCustomerById(id);
	}
	

	public Customer createCustomer(Customer c) {
//		int custCreated = customerDao.createCustomer(c);
//		if(custCreated != 0 ) {
//			return true;
//		}
//		return false;
		return customerDao.createCustomerWithFunction(c);
	}
	
	public boolean updateCustomer(Customer c) {
		int custUpdated = customerDao.updateCustomer(c);
		if(custUpdated != 0) {
			return true;
		}
		return false;
	}
	
	public boolean deleteCustomer(Customer c) {
		int custDeleted = customerDao.deleteCustomer(c);
		if(custDeleted != 0) {
			return true;
		}
		return false;
	} 
	
	
	
}

