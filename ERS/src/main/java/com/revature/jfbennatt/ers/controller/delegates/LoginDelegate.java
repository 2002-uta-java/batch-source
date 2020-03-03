package com.revature.jfbennatt.ers.controller.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.jfbennatt.ers.models.Employee;

/**
 * Delegate used to set the cookie used by the client for authentication.
 * 
 * @author Jared F Bennatt
 *
 */
public class LoginDelegate extends Delegate {

	/**
	 * Sets the cookies necessary for authentication and session management.
	 * 
	 * @see Delegate#processRequest(Employee, String, HttpServletRequest,
	 *      HttpServletResponse)
	 */
	@Override
	protected void processRequest(Employee employee, String path, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// The Delegate class has already retrieved a valid employee, so this employee
		// SHOULD be able to login. This just needs to set up the response for the
		// client.
		setAuthorizationCookie(employee, response);
		setNameCookies(employee, response);
		response.setStatus(200);
	}

}
