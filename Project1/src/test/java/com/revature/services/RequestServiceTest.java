package com.revature.services;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.daos.RequestDao;
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
		
		assertTrue(rs.reviewRequest(r));
		
	}
	
}
