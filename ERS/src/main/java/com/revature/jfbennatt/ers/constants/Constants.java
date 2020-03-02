package com.revature.jfbennatt.ers.constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.apache.log4j.Logger;

import com.revature.jfbennatt.ers.controller.FrontController;
import com.revature.jfbennatt.ers.controller.RequestDispatcher;
import com.revature.jfbennatt.ers.controller.delegates.Delegate;

/**
 * This is a helper class which generates the constants.js file to be used by
 * the front end. This is an automated-ish way to make sure the Java side and
 * Javascript side remain synced up. If I make a change on the Java side (e.g.
 * change the value of a header name), this program, after running, will update
 * the constants used by Javascript.
 * 
 * @author Jared F Bennatt
 *
 */
public class Constants {
	public static final String CONSTANTS_FILE = "src/main/webapp/scripts/constants.js";

	public static void main(String[] args) {
		try (final PrintStream ps = new PrintStream(new File(CONSTANTS_FILE))) {
			printOpeningComment(ps);

			printVariable(ps, "AUTH_TOKEN_HEADER", Delegate.AUTH_TOKEN_HEADER, "header for the authorization token");
			printVariable(ps, "FIRST_NAME_HEADER", Delegate.FIRST_NAME_HEADER,
					"header for getting the first name of an employee");
			printVariable(ps, "LAST_NAME_HEADER", Delegate.LAST_NAME_HEADER,
					"header for getting the last name of an employee");
			printVariable(ps, "LOGIN_API",
					RequestDispatcher.CONTEXT_ROOT + RequestDispatcher.API + RequestDispatcher.LOGIN,
					"URI for logging in a user");
			printVariable(ps, "EMAIL_HEADER", Delegate.EMAIL_HEADER, "header for sending the email when logging in");
			printVariable(ps, "PASSWORD_HEADER", Delegate.PASSWORD_HEADER,
					"header for sending the password when logging in");
		} catch (FileNotFoundException e) {
			Logger.getRootLogger().error(e.getMessage());
		}
	}

	private static void printOpeningComment(final PrintStream ps) {
		ps.println("/**");
		ps.println(" * This file holds constants that help keep things centralized between my java");
		ps.println(" * code and my javascript code.");
		ps.println(" */");
	}

	private static void printVariable(final PrintStream ps, final String varName, final String varValue,
			final String comment) {
		ps.println();
		ps.println("// " + comment);
		ps.printf("const %s = \"%s\";", varName, varValue);
	}
}
