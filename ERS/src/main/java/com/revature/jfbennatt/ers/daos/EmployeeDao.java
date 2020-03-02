package com.revature.jfbennatt.ers.daos;

import com.revature.jfbennatt.ers.models.Employee;

public interface EmployeeDao {

	/**
	 * Finds an employee based on their session token. At a minimum, the employee id
	 * should be set by this method.
	 * 
	 * @param token Session token of the employee (theoretically the employee would
	 *              have already logged in and received a session token).
	 * @return {@link Employee} object representing the employee or null if the
	 *         session token is invalid.
	 */
	public Employee getEmployeeByToken(String token);

	/**
	 * Gets an employee from their email. This is intended to be used to log in an
	 * employee and thus this method should set the password of the {@link Employee}
	 * object being returned.
	 * 
	 * @param email Email of the employee.
	 * @return An {@link Employee} object representing the employee or null if the
	 *         employee doesn't exist.
	 */
	public Employee getEmployeeByEmail(String email);

	/**
	 * Returns the length (of chars) that this implementation uses for the session
	 * token.
	 * 
	 * @return length (in chars) of the session token.
	 */
	public int getTokenLength();

	/**
	 * Sets the token for this employee.
	 * 
	 * @param empId Employee id of the employee to set the token for.
	 * @param token Session token for this employee
	 * @return Whether or not this was successful.
	 */
	public boolean setTokenById(int empId, String token);

	/**
	 * Attempts to delete the plain text token from the database.
	 * 
	 * @param token Session token of the user that will be deleted (the token, not
	 *              the user).
	 * @return Whether or not the token was actually deleted
	 */
	public boolean deleteSessionToken(String token);

}
