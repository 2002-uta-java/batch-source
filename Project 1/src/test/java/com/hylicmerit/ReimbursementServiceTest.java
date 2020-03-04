package com.hylicmerit;

import static org.junit.Assert.assertEquals;
import java.util.List;

import org.junit.Test;

import com.hylicmerit.models.Reimbursement;
import com.hylicmerit.service.ReimbursementService;

public class ReimbursementServiceTest {
	ReimbursementService rs = new ReimbursementService();

	//GET ALL BY EMPLOYEE TESTS
	//TODO: check if null in employee delegate
	@Test
	public void testGetAllByEmployeeBlankEmail() {
		List<Reimbursement> expected = null;
		String email = "";
		assertEquals(expected, rs.getAllByEmployee(email));
	}
	@Test
	public void testGetAllByEmployeeNullEmail() {
		List<Reimbursement> expected = null;
		String email = null;
		assertEquals(expected, rs.getAllByEmployee(email));
	}
	@Test
	public void testGetAllByEmployeeInvalidEmail() {
		List<Reimbursement> expected = null;
		String email = "none@gmail.com";
		assertEquals(expected, rs.getAllByEmployee(email));
	}
	@Test
	public void testGetAllByEmployeeUsingManagerEmail() {
		List<Reimbursement> expected = null;
		String email = "mila@gmail.com";
		assertEquals(expected, rs.getAllByEmployee(email));
	}

	//GET ALL BY MANAGER TESTS
	//TODO: check if null in employee delegate
	@Test
	public void testGetAllByManagerBlankEmail() {
		List<Reimbursement> expected = null;
		String email = "";
		assertEquals(expected, rs.getAllByManager(email));
	}
	@Test
	public void testGetAllByManagerNullEmail() {
		List<Reimbursement> expected = null;
		String email = null;
		assertEquals(expected, rs.getAllByManager(email));
	}
	@Test
	public void testGetAllByManagerInvalidEmail() {
		List<Reimbursement> expected = null;
		String email = "none@gmail.com";
		assertEquals(expected, rs.getAllByManager(email));
	}
	@Test
	public void testGetAllByManagerUsingEmployeeEmail() {
		List<Reimbursement> expected = null;
		String email = "hechi@gmail.com";
		assertEquals(expected, rs.getAllByManager(email));
	}
	
	//GET BY ID TESTS
	public void testGetByIdInvalid() {
		Reimbursement expected = null;
		int id = -1;
		assertEquals(expected, rs.getById(id));
	}
	public void testGetByIdValid() {
		Reimbursement expected = new Reimbursement(9, "mila@gmail.com", "saul@gmail.com", 
													"approved", "Out of State Training", "03/02/2020 12:35", 134.6);
		int id = 9;
		assertEquals(expected, rs.getById(id));
	}
}
