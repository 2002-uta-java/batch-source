package com.revature.jfbennatt.ers.controller.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.jfbennatt.ers.models.Employee;
import com.revature.jfbennatt.ers.services.EmployeeService;

public abstract class Delegate {
	/**
	 * path for the home page (forwards to login upon unauthorized access)
	 */
	public static final String HOME = "/";
	/**
	 * static page for logging in
	 */
	public static final String LOGIN_PAGE = "/static/Login.html";

	/**
	 * Header name for the session token (or authorization token)
	 */
	public static final String AUTH_TOKEN_HEADER = "session-token";

	/**
	 * header name that the first name will be returned in
	 */
	public static final String FIRST_NAME_HEADER = "first-name";
	/**
	 * header name that the last name will be returned in.
	 */
	public static final String LAST_NAME_HEADER = "last-name";

	/**
	 * {@link EmployeeService} object used to perform operations for the employee
	 */
	protected EmployeeService empService;

	/**
	 * Sets the {@link EmployeeService} object used by this delegate.
	 * 
	 * @param empService {@link EmployeeService} to be used by this delegate.
	 */
	public void setEmployeeService(final EmployeeService empService) {
		this.empService = empService;
	}

	/**
	 * Checks that this request is an authorized request. Returns null if the
	 * request is unauthorized. If the request is authorized, then this method will
	 * setup the response headers so that the client has the employee information
	 * (first and last name and token). The client should always grab the token
	 * because it may change.
	 * 
	 * @param request the http request being made.
	 * @return The employee making the request or <code>null</code> if the request
	 *         is unauthorized.
	 */
	protected final Employee authenticateEmployee(final HttpServletRequest request,
			final HttpServletResponse response) {
		final String token = request.getHeader(AUTH_TOKEN_HEADER);
		final Employee employee = empService.getEmployeeByToken(token);

		if (employee != null) {
			setAuthorizationHeader(employee, response);
			setNameHeaders(employee, response);
			return employee;
		} else {
			return null;
		}
	}

	/**
	 * Sets the authorization header for the response.
	 * 
	 * @param employee Employee object to set token for.
	 * @param response http response being returned.
	 */
	protected final void setAuthorizationHeader(final Employee employee, final HttpServletResponse response) {
		response.setHeader(AUTH_TOKEN_HEADER, employee.getToken());
	}

	/**
	 * Sets the first and last name headers for the response.
	 * 
	 * @param employee Employee object to set names for.
	 * @param response http response being returned.
	 */
	protected final void setNameHeaders(final Employee employee, final HttpServletResponse response) {
		response.setHeader(FIRST_NAME_HEADER, employee.getFirstName());
		response.setHeader(LAST_NAME_HEADER, employee.getLastName());
	}

	/**
	 * Processes the given request by doing something with the response. This method
	 * is the entry-point to this class. It is the only method that should be called
	 * by an outside entity.
	 * 
	 * @param request  http request being made.
	 * @param response response to be given for this request.
	 * @throws IOException
	 * @throws ServletException
	 */
	public final void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		final Employee employee = authenticateEmployee(request, response);

		if (employee == null) {
			// forward to the login page
			request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
		} else {
			// the employee was authenticated, send this along to actually do something
			processRequest(employee, request, response);
		}
	}

	/**
	 * This method should be implemented to add functionality to the processRequest
	 * method. Sub-classes can assume that the Employee is non-null and is an
	 * authenticated employee (or manager).
	 * 
	 * @param employee {@link Employee} object used to perform data retrievals (if
	 *                 necessary).
	 * @param request  http request being made
	 * @param response http response to be returned. The Employee's session token
	 *                 and first and last names have already been added to the
	 *                 headers for the response (assuming that
	 *                 {@link #processRequest(HttpServletRequest, HttpServletResponse)}
	 *                 has been called prior).
	 * @throws ServletException
	 * @throws IOException
	 * @see #processRequest(HttpServletRequest, HttpServletResponse)
	 */
	protected abstract void processRequest(Employee employee, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException;

}
