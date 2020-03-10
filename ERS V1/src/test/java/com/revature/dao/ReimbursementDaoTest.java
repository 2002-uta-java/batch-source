package com.revature.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.List;

import org.junit.Test;

import com.revature.daos.ReimbursementDaoImpl;
import com.revature.models.Reimbursement;

public class ReimbursementDaoTest {
	// private int reimbursementId;

//	@Test
//	public void testGetAllReimbursements() {
//		ReimbursementDaoImpl reimImpl = new ReimbursementDaoImpl();
//		List result = reimImpl.getAllReimbursements();
//		System.out.println("Number of Reimbursements in ERS system  are: " + result.size());
//
//		assertEquals(3, result.size());
//	}
//
//	@Test
//	public void testViewReimbursementByDetails() {
//		ReimbursementDaoImpl reimImpl = new ReimbursementDaoImpl();
//		Reimbursement reimbursementDetail = reimImpl.viewReimbursementDetails(1);
//		assertEquals("relocation expenses", reimbursementDetail.getDescription());
//	}

	@Test
	public void testCreateReimbursement() {
		ReimbursementDaoImpl reimImpl = new ReimbursementDaoImpl();
		Reimbursement newReim = new Reimbursement(new Date(System.currentTimeMillis()), "electronic purchases", "office",
				"600", "Submitted", "Company startup", 24);
		try {
			boolean result = reimImpl.createReimbursement(newReim);
			System.out.println("Created new reimbursement : " + result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	@Test
//	public void testUpdateReimbursement() {
//		ReimbursementDaoImpl reimImpl = new ReimbursementDaoImpl();
//		Reimbursement updateReim = new Reimbursement(new Date(System.currentTimeMillis()), "office supplies", "office",
//				"250", "Resubmitted", "Ran out of office supplies", 3, 3);
//		String result = reimImpl.updateReimbursement(updateReim);
//		System.out.println("Updated existing reimbursement : " + result);
//		assertEquals("Reimbursement updated successfully", result);
//
//	}
}
