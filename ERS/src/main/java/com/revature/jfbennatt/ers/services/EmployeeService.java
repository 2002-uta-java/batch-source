package com.revature.jfbennatt.ers.services;

import com.revature.jfbennatt.ers.models.Employee;

public class EmployeeService {

	/**
	 * Get/authenticate an employee from their session token.
	 * 
	 * @param token Given session token.
	 * @return The authenticated Employee object (all fields will be set except for
	 *         the password).
	 */
	public Employee getEmployeeByToken(String token) {
		// TODO need to use dao to actually authenticate token
		return new Employee();

	}

}
