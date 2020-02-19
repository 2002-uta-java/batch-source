package com.revature.userinteractionTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Random;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.model.Account;
import com.revature.model.Customer;
import com.revature.service.AccountService;
import com.revature.service.CustomerService;

import MenuInteraction.UserMenu;

@RunWith(MockitoJUnitRunner.class)
public class UserInterfaceImplTest {
	@InjectMocks
	private Scanner sc = new Scanner(System.in);
	@Mock
	protected int id;
	@Mock
	protected String username;
	@Mock
	protected String password;
	@Mock
	public String name;
	@Mock
	public String lastName;
	@Mock
	public String address;
	@Mock
	public int zipcode;
	@Mock
	public String email;
	@Mock
	protected int accountNumber;
	@Mock
	protected double accountBalance;
	@Mock
	public double moneyAmount;
	@Mock
	AccountService a = new AccountService();
	@Mock
	CustomerService c1 = new CustomerService();
	@Mock
	Account number = new Account();
	
	public UserInterfaceImplTest() {
			
		}
	
	@Test
	public void userValidation() {
		when(username.equals(username)).thenReturn(true);
		boolean expected = true;
		assertEquals(expected, username);
}

	@Test
	public void withdrawFunds() {
      when(accountBalance < moneyAmount).thenReturn(false);
      String expected = "Insufficent Funds";
	  assertEquals(expected, accountBalance);
	}
	@SuppressWarnings("deprecation")
	@Test
	public void accountBalance() {
	 when(a.getBalance(accountNumber)).thenReturn(accountBalance = 100.00);
	 double expected = 100.00;
	 assertEquals(expected, a.getBalance(accountNumber));
	}
	@Test
	public void depositFunds() {
		when(accountBalance < moneyAmount).thenReturn(false);
	      String expected = "Deposit Funds";
		  assertEquals(expected, accountBalance);	
	}
	
	@Test
	public void newUserInput() {	
	}
}	