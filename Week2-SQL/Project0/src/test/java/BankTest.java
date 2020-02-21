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
	public void shortUsernameInvalid() {
		boolean isUsernameValid = InputValidator.validUsername("dean");
		assertFalse(isUsernameValid);
	}
	
	@Test
	public void usernameWithSpecialCharactersInvalid() {
		boolean isUsernameValid = InputValidator.validUsername("dean!!!");
		assertFalse(isUsernameValid);
	}
	
	@Test
	public void usernameWithProperLengthIsValid() {
		boolean isUsernameValid = InputValidator.validUsername("deanna");
		assertTrue(isUsernameValid);
	}
	
	@Test
	public void shortPasswordIsInvalid() {
		boolean isValidPassword = InputValidator.validPassword("some");
		assertFalse(isValidPassword);
	}
	
	@Test
	public void shortPasswordWithSymbolsInvalid() {
		boolean isValidPassword = InputValidator.validPassword("some@");
		assertFalse(isValidPassword);
	}
	
	@Test
	public void longPasswordWithUpperNumSymbolIsValid() {
		boolean isValidPassword = InputValidator.validPassword("Something1!");
		assertTrue(isValidPassword);
	}
	
	
	@Test
	public void properlyFormattedInput() {
		assertEquals("88.00", InputValidator.formatDecimals(88f));
	}
	@Test
	public void properlyFormatted2Precision() {
		assertEquals("88.33", InputValidator.formatDecimals(88.33f));
	}
	@Test
	public void properlyFormatted3Precision() {
		assertEquals("888.33", InputValidator.formatDecimals(888.33f));
	}

}
