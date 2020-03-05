package com.revature.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.h2.tools.RunScript;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ReimbursementDaoImplTest {

	public static ReimbursementDao rd = new ReimbursementDaoImpl();
	
	@BeforeClass
	public static void setUp() throws SQLException, FileNotFoundException {
		try(Connection c = ConnectionUtil.getConnection()){
			RunScript.execute(c, new FileReader("setup.sql"));
		}
	}
	
	@Test
	public void getAllReimbursementsByStatusPendingTest() {
		Set<Reimbursement> expected = new HashSet<>();
		Employee e2 = new Employee(2, "lolo", "pw", "employee", "jimmy", "johns", "jj@email.com");
		Employee e3 = new Employee(3, "jack", "bequick", "employee", "jack", "black", "jb@gmail.com");
		expected.add(new Reimbursement(2, e2, "pending", 300, "", "pizza"));
		expected.add(new Reimbursement(3, e2, "pending", 32, "", "general"));
		expected.add(new Reimbursement(4, e3, "pending", 60, "", "general"));
		assertEquals(expected, new HashSet<>(rd.getAllReimbursements("pending")));
	}
	
	@Test
	public void getAllReimbursementsByStatusResolvedTest() {
		Set<Reimbursement> expected = new HashSet<>();
		Employee e2 = new Employee(2, "lolo", "pw", "employee", "jimmy", "johns", "jj@email.com");
		Employee e3 = new Employee(3, "jack", "bequick", "employee", "jack", "black", "jb@gmail.com");
		expected.add(new Reimbursement(1, e2, "approved", 18, "santa clause", "cookies"));
		expected.add(new Reimbursement(5, e2, "approved", 12, "santa clause", "bacon"));
		expected.add(new Reimbursement(6, e3, "denied", 1, "santa clause", "iced tea"));

		assertEquals(expected, new HashSet<>(rd.getAllReimbursements("resolved")));
	}
	
	@Test
	public void getAllReimbursementsByEmployeeSuccessTest() {
		Set<Reimbursement> expected = new HashSet<>();
		Employee e2 = new Employee(2, "lolo", "pw", "employee", "jimmy", "johns", "jj@email.com");
		expected.add(new Reimbursement(1, e2, "approved", 18, "santa clause", "cookies"));
		expected.add(new Reimbursement(2, e2, "pending", 300, "", "pizza"));
		expected.add(new Reimbursement(3, e2, "pending", 32, "", "general"));
		expected.add(new Reimbursement(5, e2, "approved", 12, "santa clause", "bacon"));
		assertEquals(expected, new HashSet<>(rd.getAllReimbursements(e2)));
	}
	
	@Test
	public void getAllReimbursementsByEmployeeFailureTest() {
		List<Reimbursement> expected = new ArrayList<>();
		assertEquals(expected, rd.getAllReimbursements(new Employee(4, "jack", "bequick", "employee", "jack", "black", "jb@gmail.com")));
	}	
	
	@Test
	public void getAllReimbursementsFromEmployeeByStatusPendingTest() {
		List<Reimbursement> expected = new ArrayList<>();
		Employee e = new Employee(2, "lolo", "pw", "employee", "jimmy", "johns", "jj@email.com");
		expected.add(new Reimbursement(2, e, "pending", 300, "", "pizza"));
		expected.add(new Reimbursement(3, e, "pending", 32, "", "general"));
		assertEquals(expected, rd.getAllReimbursements(e, "pending"));
	}
	
	@Test
	public void getAllReimbursementsFromEmployeeByStatusResolvedTest() {
		List<Reimbursement> expected = new ArrayList<>();
		Employee e = new Employee(2, "lolo", "pw", "employee", "jimmy", "johns", "jj@email.com");
		expected.add(new Reimbursement(1, e, "approved", 18, "santa clause", "cookies"));
		expected.add(new Reimbursement(5, e, "approved", 12, "santa clause", "bacon"));
		assertEquals(expected, rd.getAllReimbursements(e, "resolved"));
	}
	

	@Test
	public void getReimbursementByIdSuccessTest() {
		Reimbursement expected = new Reimbursement(1, new Employee(2, "lolo", "pw", "employee", "jimmy", "johns", "jj@email.com"), "approved", 18, "santa clause", "cookies");
		assertEquals(expected, rd.getReimbursementById(1));
	}
	
	@Test
	public void getReimbursementByIdFailureTest() {
		assertNull(rd.getReimbursementById(9));
	}
	
	@AfterClass
	public static void tearDown() throws SQLException, FileNotFoundException {
		try(Connection c = ConnectionUtil.getConnection()){
			RunScript.execute(c, new FileReader("teardown.sql"));
		}
	}
}
