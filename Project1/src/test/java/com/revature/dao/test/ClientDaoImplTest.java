package com.revature.dao.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.dao.ClientDao;
import com.revature.dao.ClientDaoImpl;
import com.revature.model.Client;

@RunWith(MockitoJUnitRunner.class)
public class ClientDaoImplTest {
	public ClientDao cd = new ClientDaoImpl();
@InjectMocks
@Mock
	public String clientEmail;
@Mock
	public String clientPassword;
@Mock
	public List<Client> mockList;

//@Before
//public void setup() {
//	MockitoAnnotations.initMocks(this);
//}
//	@Test
//	public void getClient() {
//		when(mockList.get(0)).thenReturn();
//		Client result = mockList.get(0);
//		assertEquals("Should have correct result", "hi", result);
//		verify(mockList).get(0);	
//	}
//	
//	@Test 
//	public void getClientId(String email) {
//		when()
//	}

}
