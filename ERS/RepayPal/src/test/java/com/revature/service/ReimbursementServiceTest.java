package com.revature.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.dao.ReimbursementDao;
import com.revature.model.Reimbursement;

@RunWith(MockitoJUnitRunner.class)
public class ReimbursementServiceTest {
	
	@InjectMocks
	ReimbursementService rs = new ReimbursementService();
	
	@Mock
	ReimbursementDao rd;
	
	@Test
	public void getReimbursementWithValidUsername() {
		when(rs.getReimbursementById(0)).thenReturn(new Reimbursement(0, "ingarcia","Approved", 30, "Werk", "Approved by: mana"));
		Reimbursement a = new Reimbursement(0, "ingarcia","Approved", 30, "Werk", "Approved by: mana");
		
		assertEquals(a,rs.getReimbursementById(0));
	}
	
	@Test
	public void getAccountWithInvalidId() {
		when(rs.getReimbursementById(30)).thenReturn(null);
		assertNull(rs.getReimbursementById(30));
	}
	
	@Test
	public void insertNegativeAmount() {
		when(rd.updateReimbursement(new Reimbursement(30, "ingarcia","Pending", -30, "Work", "Not Resolved"), "JohnTheManager")).thenReturn(0);
		assertEquals(false ,rs.updateReimbursement(new Reimbursement(30, "ingarcia","Pending", -30, "Work", "Not Resolved"), "JohnTheManager"));
	}
}
