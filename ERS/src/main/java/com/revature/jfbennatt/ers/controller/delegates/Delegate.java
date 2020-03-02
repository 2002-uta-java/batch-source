package com.revature.jfbennatt.ers.controller.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

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
	 * header name for the email when the user attempts to login
	 */
	public static final String EMAIL_HEADER = "email";
	/**
	 * header name for the password when the user attempts to login
	 */
	public static final String PASSWORD_HEADER = "password";
	/**
	 * Value used to set the max age of a cookie.
	 */
	private static final int COOKIE_TIME = 100;

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
	 * This method also handles a login attempt. If the token is not set (or is
	 * incorrect), then this method checks to see if an email and password are
	 * included in the request, if so it attempts to login said employee and returns
	 * an {@link Employee} object that has its fields set (particularly the token
	 * needed to subsequent accesses).
	 * 
	 * @param request the http request being made.
	 * @return The employee making the request or <code>null</code> if the request
	 *         is unauthorized.
	 */
	protected final Employee authenticateEmployee(final HttpServletRequest request,
			final HttpServletResponse response) {
		final Cookie[] cookies = request.getCookies();
		final String encryptedToken = getAuthToken(cookies);
		final Employee employee = empService.getEmployeeByToken(encryptedToken);

		if (employee != null) {
//			setAuthorizationCookie(employee, response);
//			setNameCookies(employee, response);
			return employee;
		} else {
			// try to see if the request is attempting to login
			final String email = request.getHeader(EMAIL_HEADER);
			final String password = request.getHeader(PASSWORD_HEADER);

			Logger.getRootLogger().debug("Trying to login: " + email + " " + password);
			System.out.println("Trying to login: " + email + " " + password);
			if (email != null && password != null) {
				final Employee emp = empService.loginEmployee(email, password);
				Logger.getRootLogger().debug("Trying to login in: " + emp);
				System.out.println("Trying to login in: " + emp);
				return emp;
			}

			return null;
		}
	}

	/**
	 * Sets the first and last name (unencrypted) cookie for the client. The cookies
	 * are set to expire when the browser is closed.
	 * 
	 * @param employee {@link Employee} object to get the name from.
	 * @param response HTTP response to add the cookies to.
	 */
	protected void setNameCookies(Employee employee, HttpServletResponse response) {
		final Cookie firstName = new Cookie(FIRST_NAME_HEADER, employee.getFirstName());
		final Cookie lastName = new Cookie(LAST_NAME_HEADER, employee.getLastName());

		// set lifetime to expire when the browser is closed
//		firstName.setMaxAge(COOKIE_TIME);
//		lastName.setMaxAge(COOKIE_TIME);

		response.addCookie(firstName);
		response.addCookie(lastName);

		Logger.getRootLogger()
				.debug("Setting first and last name cookies: " + firstName.getValue() + " " + lastName.getValue());
		System.out.println("Setting first and last name cookies: " + firstName.getValue() + " " + lastName.getValue());
	}

	/**
	 * Sets the cookie value for this employee so that the client can continue to
	 * access the server resources. The cookie is set to expire when the browser is
	 * closed.
	 * 
	 * @param employee {@link Employee} object which holds a token to be set.
	 * @param response The HTTP response to add the cookie to.
	 */
	protected void setAuthorizationCookie(Employee employee, HttpServletResponse response) {
		final Cookie authToken = new Cookie(AUTH_TOKEN_HEADER, employee.getToken());

		// set cookie to be deleted when browser is closed
//		authToken.setMaxAge(COOKIE_TIME);
		authToken.setDomain("localhost");
		response.addCookie(authToken);
		Logger.getLogger("Setting Authorization cookie: " + employee.getToken());
		System.out.println("Setting Authorization cookie: " + employee.getToken());
	}

	private String getAuthToken(Cookie[] cookies) {
		if (cookies != null) {
			for (final Cookie cookie : cookies) {
				Logger.getRootLogger().debug("Found cookie: " + cookie.getName() + ", " + cookie.getValue());
				System.out.println("Found cookie: " + cookie.getName() + ", " + cookie.getValue());
				if (cookie.getName().equals(AUTH_TOKEN_HEADER))
					return cookie.getValue();
			}
		} else {
			Logger.getRootLogger().debug("cookies was null");
			System.out.println("cookies was null");
		}
		// either there were no cookies or the auth-token wasn't set
		return null;
	}

	/**
	 * Processes the given request by doing something with the response. This method
	 * is the entry-point to this class. It is the only method that should be called
	 * by an outside entity.
	 * 
	 * @param path     URI for this request.
	 * @param request  http request being made.
	 * @param response response to be given for this request.
	 * @throws IOException
	 * @throws ServletException
	 */
	public final void processRequest(final String path, final HttpServletRequest request,
			final HttpServletResponse response) throws IOException, ServletException {
		final Employee employee = authenticateEmployee(request, response);

		if (employee == null) {
			// forward to the login page
			request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
		} else {
			// the employee was authenticated, send this along to actually do something
			processRequest(employee, path, request, response);
		}
	}

	/**
	 * This method should be implemented to add functionality to the processRequest
	 * method. Sub-classes can assume that the Employee is non-null and is an
	 * authenticated employee (or manager).
	 * 
	 * @param employee {@link Employee} object used to perform data retrievals (if
	 *                 necessary).
	 * @param path     URI for this request.
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
	protected abstract void processRequest(Employee employee, String path, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException;

}
