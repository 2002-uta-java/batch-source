package com.revature.jfbennatt.reimbursement.dao;

import com.revature.jfbennatt.reimbursement.dao.models.Employee;

/**
 * This interface defines the interactions needed for my service layer to
 * retrieve data from my database.
 * 
 * @author Jared F Bennatt
 *
 */
public interface EmployeeDao {
	/**
	 * Name of email field in database.
	 */
	public static final String EMAIL_FIELD = "empl_email";
	/**
	 * Name of password field in database.
	 */
	public static final String PASSWORD_FIELD = "password";
	/**
	 * Name of first name field in database.
	 */
	public static final String FIRSTNAME_FIELD = "empl_first_name";
	/**
	 * Name of last name field in database.
	 */
	public static final String LASTNAME_FIELD = "empl_last_name";
	/**
	 * Name of session id field in database.
	 */
	public static final String SESSION_ID_FIELD = "session_id";
	/**
	 * Length (in characters) of the session id field in the database.
	 */
	public static final int SESSION_ID_LENGTH = 64;

	/**
	 * Returns an Employee object given their email.
	 * 
	 * @param email Unique email of employee.
	 * @return Employee object representing the data from the db. <code>null</code>
	 *         if no employee record is found.
	 */
	public Employee getEmployee(String email);

	/**
	 * Updates the session Id held by the database for the given user.
	 * 
	 * @param email     email of record to update
	 * @param sessionId session id to store in database (which is what will be used
	 *                  for verification from now on).
	 * @return Whether or not this method was successful.
	 */
	public boolean updateSessionId(String email, String sessionId);

}
