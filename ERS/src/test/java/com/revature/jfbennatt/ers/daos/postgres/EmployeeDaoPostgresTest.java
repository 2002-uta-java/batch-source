package com.revature.jfbennatt.ers.daos.postgres;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.h2.tools.RunScript;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.jfbennatt.connections.ConnectionUtil;
import com.revature.jfbennatt.ers.daos.EmployeeDao;
import com.revature.jfbennatt.ers.models.Employee;

/**
 * JUnit tests for {@link EmployeeDaoPostgres}.
 * 
 * @author Jared F Bennatt
 *
 */
public class EmployeeDaoPostgresTest {
	private EmployeeDao empDao = new EmployeeDaoPostgres();

	@BeforeClass
	public static void setup() throws FileNotFoundException, SQLException {
		try (final Connection c = ConnectionUtil.getH2Connection()) {
			RunScript.execute(c, new FileReader("setup.sql"));
		}
	}

	@AfterClass
	public static void tearDown() throws FileNotFoundException, SQLException {
		try (final Connection c = ConnectionUtil.getH2Connection()) {
			RunScript.execute(c, new FileReader("teardown.sql"));
		}
	}

	@Test
	public void testGetEmployeeByToken() {
		final String token = "8i72G30cj1EyX45G71n1w779W0113r29pPo97l6I7792901U8r0a1Hk1Ek666Q15";
		final Employee actual = empDao.getEmployeeByToken(token);
		// (1, 'ecisneros0@guardian.co.uk', 'Erika', 'Cisneros',
		// '8i72G30cj1EyX45G71n1w779W0113r29pPo97l6I7792901U8r0a1Hk1Ek666Q15', false,
		// 'xStNIt');
		final Employee expected = new Employee();
		expected.setEmail("ecisneros0@guardian.co.uk");
		expected.setEmpId(1);
		expected.setFirstName("Erika");
		expected.setLastName("Cisneros");
		expected.setManager(false);
		expected.setToken(token);

		assertEquals(expected, actual);
	}

	@Test
	public void testGetEmployeeByInvalidToken() {
		final String token = "1234567812345678123456781234567812345678123456781234567812345678";
		final Employee e = empDao.getEmployeeByToken(token);

		assertNull(e);
	}

	@Test
	public void testGetEmployeeByEmail() {
		final String email = "tandre3@seattletimes.com";
		final Employee actual = empDao.getEmployeeByEmail(email);

		final Employee expected = new Employee();
		// (4, 'tandre3@seattletimes.com', 'Tana', 'Andre', null, false,
		// '1QE3yNmARj8U');
		expected.setEmpId(4);
		expected.setEmail(email);
		expected.setFirstName("Tana");
		expected.setLastName("Andre");
		expected.setManager(false);
		expected.setPassword("1QE3yNmARj8U");

		assertEquals(expected, actual);
	}

	@Test
	public void testGetEmployeeByInvalidEmail() {
		final String email = "fake@notreal.com";
		final Employee actual = empDao.getEmployeeByEmail(email);

		assertNull(actual);
	}

	@Test
	public void testSetTokenByIdFromNull() {
		final int emplId = 2;
		final String newToken = "0972856109728561097285610972856109728561097285610972856109728561";
		empDao.setTokenById(emplId, newToken);
		// (2, 'jdeath1@gravatar.com', 'Julius', 'De''Ath', null, false, 'SF6ytDkUhs');
		final String email = "jdeath1@gravatar.com";
		final Employee actual = empDao.getEmployeeByEmail(email);

		assertEquals(newToken, actual.getToken());
	}

	@Test
	public void testSetTokenByIdChange() {
		// (5, 'jwillmont4@timesonline.co.uk', 'Johnathon', 'Willmont',
		// 'fm6916688B53cL297zuuI7Ke4zJ102dOg9LA27QyG965iesM51B05771Y29447d6', true,
		// 'RPfslKu7PN9');
		final int empId = 5;
		final String newToken = "25854fku25854fku25854fku25854fku25854fku25854fku25854fku25854fku";
		empDao.setTokenById(empId, newToken);
		final String email = "jwillmont4@timesonline.co.uk";
		final Employee actual = empDao.getEmployeeByEmail(email);

		assertEquals(newToken, actual.getToken());
	}

	@Test
	public void testSetTokenByInvalidId() {
		final int empId = 234;
		final String newToken = "j6edhuhrj6edhuhrj6edhuhrj6edhuhrj6edhuhrj6edhuhrj6edhuhrj6edhuhr";

		assertFalse(empDao.setTokenById(empId, newToken));
	}
}
