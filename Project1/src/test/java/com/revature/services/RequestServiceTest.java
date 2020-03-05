package com.revature.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.daos.AccountDao;
import com.revature.daos.RequestDao;
import com.revature.models.Account;
import com.revature.models.Request;

@RunWith(MockitoJUnitRunner.class)
public class RequestServiceTest {
	
	@InjectMocks
	private RequestService rs;
	
	@Mock
	private RequestDao rd;
	
	@Test
	public void testValidReviewRequest() {
		
		Request r = new Request();
		
		when(rd.updateRequest(r)).thenReturn(1);
		
		assertEquals(1, rs.reviewRequest(r));
		
	}
	
}
