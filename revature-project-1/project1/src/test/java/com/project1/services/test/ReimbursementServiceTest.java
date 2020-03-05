package com.project1.services.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.project1.models.Reimbursement;
import com.project1.services.ReimbursementService;

public class ReimbursementServiceTest {
	private static final ReimbursementService rService = new ReimbursementService();
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void testGetReimbursementById() {
		Reimbursement expected = new Reimbursement(1, 2, 1, 500, "Relocation", "Approve");
		Reimbursement r = rService.getReimbByID(1);
		assertEquals(expected, r);
	}
	
	@Test
	public void testupdateReimbursement() {
		Reimbursement expected = rService.getReimbByID(1);
		Reimbursement r = rService.getReimbByID(1);
		rService.updateReimbursement(r);
		assertEquals(expected, r);
	}
	
	
	@Test
	public void testGetReimbursementByStatus() {
		List<Reimbursement> expected = rService.getAllReimbByStatus("TYU");
		List<Reimbursement> rList = rService.getAllReimbByStatus("ABC");
		assertEquals(expected, rList);
	}
	
	@Test
	public void testGetReimbursementBySameStatus() {
		List<Reimbursement> expected = rService.getAllReimbByStatus("ABC");
		List<Reimbursement> rList = rService.getAllReimbByStatus("ABC");
		assertEquals(expected, rList);
	}
	
	@Test
	public void testGetReimbursementByIdPending() {
		List<Reimbursement> expected = rService.getEmpReimbByIdPending(1);
		List<Reimbursement> rList = rService.getEmpReimbByIdPending(1);
		assertEquals(expected, rList);
	}
	
	@Test
	public void testGetReimbursementByIdResolve() {
		List<Reimbursement> expected = rService.getEmpReimbByIdResolve(1);
		List<Reimbursement> rList = rService.getEmpReimbByIdResolve(1);
		assertEquals(expected, rList);
	}
	
	
	
	
}
