package com.revature.daos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.h2.tools.RunScript;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.services.ReimbursementService;
import com.revature.util.ConnectionUtil;

public class DaoImplTest {
	
	private ReimbursementService rs = new ReimbursementService();
	private EmployeeDao eDao = new EmployeeDaoImpl();
	private ReimbursementDao rDao = new ReimbursementDaoImpl();
	
	@BeforeClass
	public static void setUp() throws SQLException, FileNotFoundException {
		try(Connection c = ConnectionUtil.getConnection()){
			RunScript.execute(c, new FileReader("setup.sql"));
		}
	}
	
	@Test
	public void testNewId() {
		int expected = 6;
		assertTrue(rs.getNewId() == expected);
	}
	
	@Test
	public void testValidAmount1() {
		assertFalse(rs.validAmount(-5f));
	}
	
	@Test
	public void testValidAmount2() {
		assertFalse(rs.validAmount(0f));
	}
	
	@Test
	public void testValidAmount3() {
		assertTrue(rs.validAmount(5f));
	}
	
	@Test
	public void testValidAmount4() {
		assertFalse(rs.validAmount(10000f));
	}
	
	@Test
	public void testValidAmount5() {
		assertFalse(rs.validAmount(999999999f));
	}
	
	@AfterClass
	public static void tearDown() throws SQLException, FileNotFoundException {
		try(Connection c = ConnectionUtil.getConnection()){
			RunScript.execute(c, new FileReader("teardown.sql"));
		}
	}

}
