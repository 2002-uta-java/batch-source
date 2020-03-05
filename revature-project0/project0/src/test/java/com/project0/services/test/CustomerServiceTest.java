package com.project0.services.test;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.project0.models.Customer;
import com.project0.services.CustomerService;

public class CustomerServiceTest {
	
	private static final CustomerService customerService = new CustomerService();
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
//	@Test
//	public void testCorrectCredentialValidation() {
//		Customer expected = new Customer(1, "hoang1","hoang1@revature.com","hoangpassword");
//		assertEquals(expected, customerService.credentialValidation("hoang1", "hoangpassword"));
//	}
//	
//	@Test
//	public void testCorrectGetCustomerById() {
//		Customer expected = new Customer(1, "hoang1", "hoang1@revature.com", "hoangpassword");
//		assertEquals(expected, customerService.getCustomerById(1));
//	}
//	
//	@Test
//	public void testCorrectCreateCustomer() {
//		Customer expected = new Customer(111, "hoang121", "hoang121@revature.com", "hoangpassword");
//		Customer expected1 = new Customer(111, "hoang121", "hoang121@revature.com", "hoangpassword");	
//		assertEquals(expected, customerService.createCustomer(expected1));
//	}
	

}
