import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.dean.dao.AccountDaoImpl;
import com.dean.dao.ClientDaoImpl;
import com.dean.models.Account;
import com.dean.models.Client;
import com.dean.util.ConnectionUtil;
import com.dean.util.InputValidator;

public class BankTest {
	
	static final Logger logger = Logger.getRootLogger();

	@Test
	public void shortUsernameNotValid() {
		boolean isValidUsername = InputValidator.validUsername("mit");
		assertFalse(isValidUsername);
	}
	
	@Test
	public void usernameWithSpecialCharactersNotValid() {
		boolean isValidUsername = InputValidator.validUsername("mit@!!!");
		assertFalse(isValidUsername);
	}
	
	@Test
	public void usernameWithProperLengthIsValid() {
		boolean isValidUsername = InputValidator.validUsername("mitchup");
		assertTrue(isValidUsername);
	}
	
	@Test
	public void shortPasswordIsInvalid() {
		boolean isValidPassword = InputValidator.validPassword("123");
		assertFalse(isValidPassword);
	}
	
	@Test
	public void shortComplexPasswordIsInvalid() {
		boolean isValidPassword = InputValidator.validPassword("ac@1s");
		assertFalse(isValidPassword);
	}
	
	@Test
	public void longComplexPasswordIsValid() {
		boolean isValidPassword = InputValidator.validPassword("RevaturE1!");
		assertTrue(isValidPassword);
	}
	
	
	@Test
	public void properlyFormatted() {
		assertEquals("10.00", InputValidator.formatDecimals(10f));
	}
	@Test
	public void properlyFormatted2() {
		assertEquals("10.33", InputValidator.formatDecimals(10.33f));
	}
	@Test
	public void properlyFormatted3() {
		assertEquals("100.33", InputValidator.formatDecimals(100.33f));
	}

}
