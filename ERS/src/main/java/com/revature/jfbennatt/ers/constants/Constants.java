package com.revature.jfbennatt.ers.constants;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Field;

import org.apache.log4j.Logger;

import com.revature.jfbennatt.ers.controller.RequestDispatcher;
import com.revature.jfbennatt.ers.controller.delegates.ApprovalDelegate;
import com.revature.jfbennatt.ers.controller.delegates.ChangeProfileDelegate;
import com.revature.jfbennatt.ers.controller.delegates.Delegate;
import com.revature.jfbennatt.ers.controller.delegates.SubmitReimbursementDelegate;
import com.revature.jfbennatt.ers.controller.delegates.ViewDelegate;
import com.revature.jfbennatt.ers.controller.delegates.ViewReimbursementsDelegate;
import com.revature.jfbennatt.ers.models.Employee;
import com.revature.jfbennatt.ers.models.Reimbursement;

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
	public static final String CONSTANTS_FILE = "src/main/webapp/static/scripts/constants.js";

	private Constants() {
		super();
	}

	/**
	 * This method creates the {@link #CONSTANTS_FILE} javascript file.
	 * 
	 * @param args no arguments are used.
	 */
	public static void main(String[] args) {
		try (final PrintStream ps = new PrintStream(new File(CONSTANTS_FILE))) {
			printOpeningComment(ps);

			printVariable(ps, "HOME", RequestDispatcher.CONTEXT_ROOT, "URI to home page");
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
			printVariable(ps, "LOGOUT_API",
					RequestDispatcher.CONTEXT_ROOT + RequestDispatcher.API + RequestDispatcher.LOGOUT,
					"URI for logging out a user");
			printVariable(ps, "SUBMIT_REIMBURSEMENT_PAGE",
					RequestDispatcher.CONTEXT_ROOT + ViewDelegate.SUBMIT_REIMBURSEMENT,
					"resource for displaying the submit reimbursement page");
			printVariable(ps, "SUBMIT_REIMBURSEMENT_API",
					RequestDispatcher.CONTEXT_ROOT + RequestDispatcher.API + RequestDispatcher.SUBMIT_REIMBURSEMENT,
					"URI to actually submit a reimbursement request");
			printVariable(ps, "MAX_LENGTH_DESC", "" + SubmitReimbursementDelegate.MAX_DESCRIPTION_LENGTH,
					"the maximum length accepted for the description field when submitting a reimbursement request");
			printVariable(ps, "DESCRIPTION_ID", SubmitReimbursementDelegate.DESCRIPTION_ID,
					"name used for form submission for the description when requesting a reimbursement");
			printVariable(ps, "AMOUNT_ID", SubmitReimbursementDelegate.AMOUNT_ID,
					"name for the amount field when submitting a reimbursement request");
			printVariable(ps, "DATE_ID", SubmitReimbursementDelegate.DATE_ID,
					"name for the date field when submitting a reimbursement request");
			printVariable(ps, "SUCCESS_COOKIE", Delegate.SUCCESS_COOKIE,
					"name of cookie that holds the message (for home page alert displays)");
			printVariable(ps, "FAIL_VALUE", ViewDelegate.FAIL, "value signaling a failure (value of SUCCESS_COOKIE)");
			printVariable(ps, "SUCCESS_VALUE", ViewDelegate.SUCCESS,
					"value signaling a success (value of SUCCESS_COKKIE)");
			printVariable(ps, "VIEW_PENDING_PAGE", RequestDispatcher.CONTEXT_ROOT + ViewDelegate.VIEW_PENDING,
					"URI of view reimbursement resource");
			printVariable(ps, "VIEW_PROCESSED_PAGE", RequestDispatcher.CONTEXT_ROOT + ViewDelegate.VIEW_PROCESSED,
					"URI of view reimbursement resource");
			printVariable(
					ps, "VIEW_PENDING_BY_EMP", RequestDispatcher.CONTEXT_ROOT + RequestDispatcher.API
							+ RequestDispatcher.VIEW_REIMBURSEMENT_ROOT + ViewReimbursementsDelegate.PENDING,
					"API call to view pending requests by employee");
			printVariable(
					ps, "VIEW_PROCESSED_BY_EMP", RequestDispatcher.CONTEXT_ROOT + RequestDispatcher.API
							+ RequestDispatcher.VIEW_REIMBURSEMENT_ROOT + ViewReimbursementsDelegate.PROCESSED,
					"API call to view processed requests by employee");
			printVariable(ps, "VIEW_ALL_REIMBURSEMENTS_BY_EMP",
					RequestDispatcher.CONTEXT_ROOT + RequestDispatcher.API + RequestDispatcher.VIEW_REIMBURSEMENT_ROOT,
					"URI for returning all reimbursements for an employee");
			printVariable(ps, "PROFILE_PAGE", RequestDispatcher.CONTEXT_ROOT + ViewDelegate.PROFILE,
					"URI of resource for getting the profile page");
			printVariable(ps, "EMAIL_COOKIE", Delegate.EMAIL_COOKIE_NAME,
					"name of the cookie which holds the email information");
			printVariable(ps, "CHANGE_FIRST_NAME_ID", ChangeProfileDelegate.CHANGE_FIRST_NAME_ID,
					"value (name attribute) for changing the first name");
			printVariable(ps, "CHANGE_LAST_NAME_ID", ChangeProfileDelegate.CHANGE_LAST_NAME_ID,
					"value (name attribute) for changing the last name");
			printVariable(ps, "CHANGE_EMAIL_ID", ChangeProfileDelegate.CHANGE_EMAIL_ID,
					"value (name attribute) for changing the email");
			printVariable(ps, "CHANGE_PASSWORD_ID", ChangeProfileDelegate.CHANGE_PASSWORD,
					"value (name attribute) for changing the password");
			printVariable(ps, "CHANGE_PROFILE_API",
					RequestDispatcher.CONTEXT_ROOT + RequestDispatcher.API + RequestDispatcher.CHANGE_PROFILE,
					"URI for the api that changes the profile");
			printVariable(ps, "VIEW_EMPLOYEES_PAGE", RequestDispatcher.CONTEXT_ROOT + ViewDelegate.VIEW_EMPLOYEES,
					"URI resource for viewing all employees page");
			printVariable(ps, "ALL_EMP_EXCEPT_ME",
					RequestDispatcher.CONTEXT_ROOT + RequestDispatcher.API + RequestDispatcher.GET_ALL_EMPLOYEES,
					"api call for getting all employees except manager making the request");
			printVariable(ps, "APPROVE_REQUESTS_PAGE", RequestDispatcher.CONTEXT_ROOT + ViewDelegate.APPROVE_REQUESTS,
					"URI for page to approve requests");
			printVariable(ps, "GET_ALL_PENDING_EXCEPT_ME",
					RequestDispatcher.CONTEXT_ROOT + RequestDispatcher.API + RequestDispatcher.VIEW_REIMBURSEMENT_ROOT
							+ ViewReimbursementsDelegate.ALL_PENDING,
					"api call to get all pending requests except specified manager");
			printVariable(ps, "APPROVE_REQUEST",
					RequestDispatcher.CONTEXT_ROOT + RequestDispatcher.API + RequestDispatcher.APPROVALS
							+ ApprovalDelegate.APPROVE,
					"api request for approving requests (needs an /id of the reimbursement being approved after)");
			printVariable(ps, "REJECT_REQUEST",
					RequestDispatcher.CONTEXT_ROOT + RequestDispatcher.API + RequestDispatcher.APPROVALS
							+ ApprovalDelegate.REJECT,
					"api request for rejecting requests (needs an /id of the reimbursement being rejected after)");

			ps.println();
			ps.println();
			printReimbursementFields(ps);
			ps.println();
			printEmployeeFields(ps);
		} catch (FileNotFoundException e) {
			Logger.getRootLogger().error(e.getMessage());
		}
	}

	private static void printEmployeeFields(PrintStream ps) {
		final Class<Employee> reimbClass = Employee.class;
		for (final Field field : reimbClass.getDeclaredFields()) {
			ps.println("// field name for Reimbursement." + field.getName());
			ps.println("const EMPL_" + field.getName().toUpperCase() + " = '" + field.getName() + "';");
		}
	}

	private static void printReimbursementFields(PrintStream ps) {
		final Class<Reimbursement> reimbClass = Reimbursement.class;
		for (final Field field : reimbClass.getDeclaredFields()) {
			ps.println("// field name for Reimbursement." + field.getName());
			ps.println("const REIMB_" + field.getName().toUpperCase() + " = '" + field.getName() + "';");
		}
	}

	/**
	 * Prints the comment at the top of the javascript file.
	 * 
	 * @param ps {@link PrintStream} used to write to the file.
	 */
	private static void printOpeningComment(final PrintStream ps) {
		ps.println("/**");
		ps.println(" * This file holds constants that help keep things centralized between my java");
		ps.println(" * code and my javascript code.");
		ps.println(" */");
	}

	/**
	 * Writes a variable declaration in the javascript file.
	 * 
	 * @param ps       {@link PrintStream} that writes to the file.
	 * @param varName  Name of the variable to declare.
	 * @param varValue The value to give the variable.
	 * @param comment  A comment to explain what this variable is to be used for.
	 */
	private static void printVariable(final PrintStream ps, final String varName, final String varValue,
			final String comment) {
		ps.println();
		ps.println("// " + comment);
		ps.printf("const %s = \"%s\";", varName, varValue);
	}
}
