package com.revature.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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

import com.revature.models.UserAccount;
import com.revature.util.ConnectionUtil;

public class UserAccountDaoImplTest {

	private UserAccountDao uad = new UserAccountDaoImpl();
	
	@BeforeClass
	public static void setUp() throws SQLException, FileNotFoundException {
		try(Connection c = ConnectionUtil.getConnection()){
			RunScript.execute(c, new FileReader("setup.sql"));
		}
	}
	
	@Test
	public void getAllUserAccountsTest() {
		List<UserAccount> expected = new ArrayList<>();
		expected.add(new UserAccount(1, "username", "email", "pw"));
		expected.add(new UserAccount(2, "apple", "fruits@apple.com", "worm"));
		expected.add(new UserAccount(3, "bobobomb", "gumbo@gmail.com", "8675309"));
		expected.add(new UserAccount(4, "gordon", "chefzeff@yahoo.com", "4bananas"));
		assertEquals(expected, uad.getAllUserAccounts());
	}
	
	@Test
	public void getUserAccountByUsernameSuccessfulTest() {
		UserAccount expected = new UserAccount(1, "username", "email", "pw");
		assertEquals(expected, uad.getUserAccountByUsername("username"));
	}

	@Test
	public void getUserAccountByUsernameUnsuccessfulTest() {
		assertNull(uad.getUserAccountByUsername("usefulname"));
	}
	
	@Test
	public void getUserAccountByEmailSuccessfulTest() {
		UserAccount expected = new UserAccount(1, "username", "email", "pw");
		assertEquals(expected, uad.getUserAccountByEmail("email"));
	}

	@Test
	public void getUserAccountByEmailUnsuccessfulTest() {
		assertNull(uad.getUserAccountByEmail("mail"));
	}

	@AfterClass
	public static void tearDown() throws SQLException, FileNotFoundException {
		try(Connection c = ConnectionUtil.getConnection()){
			RunScript.execute(c, new FileReader("teardown.sql"));
		}
	}
}
