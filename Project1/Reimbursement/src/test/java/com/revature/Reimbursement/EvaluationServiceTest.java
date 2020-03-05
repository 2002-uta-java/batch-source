package com.revature.Reimbursement;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.daos.ManagerDao;
import com.daos.ManagerDaoImpl;
import com.daos.ProfileDao;
import com.daos.ProfileDaoImpl;
import com.daos.ReimbursementDao;
import com.daos.ReimbursementDaoImpl;
import com.models.Manager;
import com.models.Profile;
import com.models.Reimbursement;

public class EvaluationServiceTest {
	ReimbursementDao rdi = new ReimbursementDaoImpl();
	Reimbursement r = rdi.getReimbursementByRId(1);
	ManagerDao mdi = new ManagerDaoImpl();
	Manager m = mdi.getManagerById(2);
	ProfileDao pdi = new ProfileDaoImpl();
	Profile p = pdi.getProfileById(1);
	
	@Test
	public void createReimTestValid() {
		r.setEmpId(2);
		assertEquals(1, rdi.createReimbursement(r));
	}

	@Test
	public void updateReimTest() {
		r.setDescription("Travel Reimbursement for $250");
		assertEquals(1, rdi.updateReimbursement(r));
	}
	@Test 
	public void getProfileTest() {
		System.out.println(r);
		ProfileDao pdi = new ProfileDaoImpl();
		Profile p = pdi.getProfileById(1);
		System.out.println(p);
	}
	@Test
	public void getManagerTest() {
		System.out.println(r);
		ManagerDao mdi = new ManagerDaoImpl();
		Manager m = mdi.getManagerById(2);
		System.out.println(m);
	}
	@Test
	public void getReimbTest() {
		System.out.println(p);
		ReimbursementDao rdi = new ReimbursementDaoImpl();
		Reimbursement r = rdi.getReimbursementByRId(1);
		System.out.println(r);
	}
	@Test
	public void updateProfEmail() {
		p.setEmail("LebronJames@gmail.com");
		assertEquals(1, pdi.updateProfile(p));
	}
	@Test
	public void updateProfFirst() {
		p.setFirstname("Kobe");
		assertEquals(1, pdi.updateProfile(p));
	}
	@Test
	public void updateProfLast() {
		p.setLastname("Bryant");
		assertEquals(1, pdi.updateProfile(p));
	}
}
