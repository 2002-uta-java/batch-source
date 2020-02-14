package com.revature.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.h2.tools.RunScript;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.models.Department;
import com.revature.util.ConnectionUtil;

public class DepartmentDaoImplTest {
	
	private DepartmentDao dd = new DepartmentDaoImpl();
	
	@BeforeClass
	public static void setUp() throws SQLException, FileNotFoundException {
		try(Connection c = ConnectionUtil.getConnection()){
			RunScript.execute(c, new FileReader("setup.sql"));
		}
	}
	
	@Test
	public void testGetDepartmentById() {
		Department d = new Department(2, "Marketing", 9200);
		assertEquals(d, dd.getDepartmentById(2));
	}
	
	@Test
	public void testGetDepartmentByInvalidId() {
		assertNull(dd.getDepartmentById(25));
	}
	
	
	@AfterClass
	public static void tearDown() throws SQLException, FileNotFoundException {
		try(Connection c = ConnectionUtil.getConnection()){
			RunScript.execute(c, new FileReader("teardown.sql"));
		}
	}

}
