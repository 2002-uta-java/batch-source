package com.revature.project0.services;

import java.util.List;

import com.revature.project0.daos.CustomerAccountDao;
import com.revature.project0.daos.CustomerAccountDaoImpl;
import com.revature.project0.models.CustomerAccount;

public class CustomerAccountService {
	
	private CustomerAccountDao customerAccountDao = new CustomerAccountDaoImpl();
	
	public List<CustomerAccount> getCustomerAccounts(){
		return customerAccountDao.getCustomerAccounts();
	}
	
	public CustomerAccount getCustomerAccountById(int aId) {
		return customerAccountDao.getCustomerAccountById(aId);
	}
	
	public double getCustomerAccountBalance(int aId) {
		return customerAccountDao.getCustomerAccountBalance(aId);
	}
	
	public boolean checkCustomerAccountUsername(String username) {
		return customerAccountDao.checkCustomerAccountUsername(username);
	}
	
	public String getCustomerAccountByPassword(String pass) {
		return customerAccountDao.getCustomerAccountByPassword(pass);
	}
	
	public int updateCustomerAccount(CustomerAccount ca) {
		return customerAccountDao.updateCustomerAccount(ca);
	}
	
	public CustomerAccount addNewCustomerAccount(CustomerAccount ca) {
		return customerAccountDao.addNewCustomerAccount(ca);
	}

}
