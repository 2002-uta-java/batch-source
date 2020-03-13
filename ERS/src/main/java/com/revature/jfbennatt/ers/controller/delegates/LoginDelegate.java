package com.revature.jfbennatt.ers.controller.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.jfbennatt.ers.controller.RequestDispatcher;
import com.revature.jfbennatt.ers.models.Employee;

/**
 * Delegate used to set the cookie used by the client for authentication.
 * 
 * @author Jared F Bennatt
 *
 */
public class LoginDelegate extends Delegate {

	/**
	 * Default constructor.
	 */
	public LoginDelegate() {
		super();
	}

	/**
	 * Overwrites the public method {@link Delegate#processRequest} so that the user
	 * isn't automatically redirected to the login page when actually attempting to
	 * login. Instead, an error code is sent back to indicate to the client that
	 * they failed to login.
	 * 
	 * @param path     Servlet path of request (will be determined by a
	 *                 concatenation of {@link RequestDispatcher#API} and
	 *                 {@link RequestDispatcher#LOGIN}, e.g. /api/login).
	 * @param request  HTTP request.
	 * @param response HTTP response.
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void processRequest(String path, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// need to try and login an employee or send back a 403 error
		// try to see if the request is attempting to login
		final String email = request.getHeader(EMAIL_HEADER);
		final String password = request.getHeader(PASSWORD_HEADER);

		Logger.getRootLogger().debug("Trying to login: " + email + " " + password);
		Employee emp = null;
		if (email != null && password != null) {
			emp = empService.loginEmployee(email, password);
			Logger.getRootLogger().debug("Trying to login in: " + emp);
		}

		processRequest(emp, path, request, response);
	}

	/**
	 * Sets the cookies necessary for authentication and session management.
	 * 
	 * @see Delegate#processRequest(Employee, String, HttpServletRequest,
	 *      HttpServletResponse)
	 */
	@Override
	protected void processRequest(Employee employee, String path, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// The Delegate class tried to login in the employee, if it's non-null that
		// should be fine
		Logger.getRootLogger().debug("logging in: " + employee);
		if (employee != null) {
			Logger.getRootLogger().debug("success");
			setAuthorizationCookie(employee, response);
			setNameCookies(employee, response);
			addEmailCookie(employee, response);
			response.setStatus(200);
		} else {
			Logger.getRootLogger().debug("sending back 403");
			response.sendError(403);
		}
	}

}
