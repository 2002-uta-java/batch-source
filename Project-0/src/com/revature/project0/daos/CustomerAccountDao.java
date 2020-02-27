package com.revature.project0.daos;

import java.util.List;

import com.revature.project0.models.CustomerAccount;

public interface CustomerAccountDao {
	
	public List<CustomerAccount> getCustomerAccounts();
	public CustomerAccount getCustomerAccountById(int aId);
	public double getCustomerAccountBalance(int aId); //get balance
	public boolean checkCustomerAccountUsername(String username); // get username for validation
	public String getCustomerAccountByPassword(String pass); // get password for validation
	public int updateCustomerAccount(CustomerAccount ca);
	public CustomerAccount addNewCustomerAccount(CustomerAccount ca); // using function
	
}
