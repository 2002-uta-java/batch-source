package com.revature.jfbennatt.ers.controller.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.jfbennatt.ers.models.Employee;

public class LoginDelegate extends Delegate {

	/**
	 * @see {@link Delegate#processRequest(Employee, String, HttpServletRequest, HttpServletResponse)}
	 */
	@Override
	protected void processRequest(Employee employee, String path, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// The Delegate class has already retrieved a valid employee, so this employee
		// SHOULD be able to login. This just needs to set up the response headers for
		// the client.
		response.setHeader(FIRST_NAME_HEADER, employee.getFirstName());
		response.setHeader(LAST_NAME_HEADER, employee.getLastName());
		response.setHeader(AUTH_TOKEN_HEADER, employee.getToken());
		response.setStatus(200);
	}

}
