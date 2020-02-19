package com.charnecki.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.charnecki.daos.TransactionDao;
import com.charnecki.models.Deposit;

import org.mockito.InjectMocks;
import org.mockito.Mock;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {
	
	@InjectMocks
	private TransactionService ts;
	
	@Mock
	private TransactionDao td;
	
	@Test
	public void gettingATransactionByValidId() {
		when(td.getTransactionById(5)).thenReturn(new Deposit(5, 200, 14, "Depositing 200 dollars"));
		
		assertEquals(new Deposit(5, 200, 14, "Depositing 200 dollars"), ts.getUserById(5));
	}
	
	@Test
	public void gettingAnInvalidId() {
		when(td.getTransactionById(6)).thenReturn(null);
		
		assertNull(ts.getUserById(6));
	}

}
