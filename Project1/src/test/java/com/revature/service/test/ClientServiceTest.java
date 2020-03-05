package com.revature.service.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;



import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import com.revature.dao.ClientDao;
import com.revature.dao.ClientDaoImpl;
import com.revature.model.Client;
import com.revature.service.ClientService;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	@InjectMocks
		private ClientService cs;
	
	@Mock
		private ClientDaoImpl ci;
	@Mock
		private ClientDao cd;
	
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
		public void createClient() {
		Client newClient = new Client(3,"james@gmail.com","password", 1);
		Client expected = new Client(3,"james@gmail.com","password", 1);
		assertEquals(expected, cs.createClientByFunction(newClient));
	}
	
	@Test
		public void getNonClient() {
		assertNull(cs.getClientEmail(0));
		
	}
	
	@Test
		public void getClientEmail() {
		String expected = "james@gmail.com";
		assertEquals(expected, cs.getClientEmail(3));	
		}
	
	@Test
		public void getNonClientPassword() {
		assertNull(cs.getClientPassword(1));
	}
	
	@Test
		public void getClientPassword() {
		String expected = "password";
		assertEquals(expected, cs.getClientPassword(3));
	}
	
	@Test
		public void getClientAuth() {
		assertTrue(cs.clientAuth("jimmybob23@gmail.com","password"));
	}
	
	@Test
	public void getClientAuthFail() {
	assertFalse(cs.clientAuth("1","234w5w5w"));
	}
	
	@Test
	public void verifyNonClient() {
		int expected = 0;
		assertEquals(expected, cs.verfiyClientId(0));
	}
	
	@Test
	public void verifyClient() {
		int expected = 3;
		assertEquals(expected, cs.verfiyClientId(3));
	}
	
	@Test
	public void getPermission() {
		int expected = 1;
		assertEquals(expected, cs.getClientPermission(3));
	}

}
