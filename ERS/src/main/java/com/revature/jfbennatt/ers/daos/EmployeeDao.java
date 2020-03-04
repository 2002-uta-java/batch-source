package com.revature.jfbennatt.ers.daos;

import com.revature.jfbennatt.ers.models.Employee;
import com.revature.jfbennatt.ers.models.Reimbursement;

/**
 * DAO interface for interacting with the database.
 * 
 * @author Jared F Bennatt
 *
 */
public interface EmployeeDao {

	/**
	 * Attempts to delete the plain text token from the database.
	 * 
	 * @param token Session token of the user that will be deleted (the token, not
	 *              the user).
	 * @return Whether or not the token was actually deleted
	 */
	public boolean deleteSessionToken(String token);

	/**
	 * Gets an employee from their email. This is intended to be used to log in an
	 * employee and thus this method should set (at a minimum) the password, id, and
	 * first and last name of the {@link Employee} object being returned.
	 * 
	 * @param email Email of the employee.
	 * @return An {@link Employee} object representing the employee or
	 *         <code>null</code> if the employee doesn't exist.
	 */
	public Employee getEmployeeByEmail(String email);

	/**
	 * Finds an employee based on their session token. This method should set every
	 * field in the returned {@link Employee} object except the password.
	 * 
	 * @param token Session token of the employee (theoretically the employee would
	 *              have already logged in and received a session token).
	 * @return {@link Employee} object representing the employee or
	 *         <code>null</code> if the session token is invalid.
	 */
	public Employee getEmployeeByToken(String token);

	/**
	 * Returns the length (in chars) that this implementation uses for the session
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
	 * Returns the date pattern expected for the database (e.g. mm/dd/yyyy)
	 * 
	 * @return String representation of the date pattern held in this database.
	 */
	public String datePattern();

	/**
	 * Attempts to submit (add) a reimbursement to the database.
	 * 
	 * @param newReimb {@link Reimbursement} object representing this request. At a
	 *                 minimum, the employee id, description, amount, date of
	 *                 expense (reimbDate), and the submit date must be present in
	 *                 this object.
	 * @return Whether or not the submission was successfully added to the database.
	 */
	public boolean submitRequest(Reimbursement newReimb);

	/**
	 * Gets the status from the {@link Reimbursement} object (since the status is
	 * stored as an integer).
	 * 
	 * @param reimb {@link Reimbursement} object to find the status of.
	 * @return String representation of the status of this reimbursement (e.g.
	 *         pending, approved, or denied).
	 */
	public String getStatus(Reimbursement reimb);

	/**
	 * Sets the status of this reimbursement to pending.
	 * 
	 * @param reimb {@link Reimbursement} object to set to pending.
	 */
	public void setPending(Reimbursement reimb);

	/**
	 * Sets the status of this reimbursement to approved.
	 * 
	 * @param reimb {@link Reimbursement} object to set to approved.
	 */
	public void setApproved(Reimbursement reimb);

	/**
	 * Sets the status of this reimbursement to denied.
	 * 
	 * @param reimb {@link Reimbursement} object to set to denied.
	 */
	public void setDenied(Reimbursement reimb);

}
