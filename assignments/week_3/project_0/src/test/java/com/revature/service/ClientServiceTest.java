package com.revature.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.revature.models.Client;

public class ClientServiceTest {
	
	private static final ClientService cs = new ClientService();
	
	@Test
	public void createClientTest() {
		Client expected = new Client();
		expected.setId(1);
		Client c2 = new Client();
		Client actual = cs.createClient(c2);
		actual.setId(1);
		assertEquals(expected, actual);
	}
	
	@Test
	public void getClientByIdTest() {
		Client c = new Client ("Test", "ClientTwo");
		Client expected = cs.createClient(c);
		Client actual = cs.getClientById(expected.getId());
		assertEquals(expected, actual);
	}
	
}
