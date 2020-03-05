package com.revature.jfbennatt.ers.controller.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.jfbennatt.ers.controller.RequestDispatcher;
import com.revature.jfbennatt.ers.models.Employee;
import com.revature.jfbennatt.ers.services.EmployeeService;

/**
 * Root class for (most) of the delegates. The only method that should be called
 * is {@link #processRequest} which authenticates the user and then (if
 * authenticated) forwards to the abstract method {@link #processRequest}
 * otherwise redirects to the login page.
 * 
 * @author Jared F Bennatt
 *
 */
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
	private static final int COOKIE_TIME = -1;
	/**
	 * Value used to set the path of the cookie.
	 */
	private static final String COOKIE_PATH = RequestDispatcher.CONTEXT_ROOT;
	/**
	 * Name of cookie that holds the message for the home page
	 */
	public static final String SUCCESS_COOKIE = "success";

	/**
	 * {@link EmployeeService} object used to perform operations for the employee.
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
	 * Checks that this request is an authorized request. Returns <code>null</code>
	 * if the request is unauthorized. If the request is authorized, then this
	 * method will setup the cookie/s so that the client has the employee
	 * information (first and last name and token). The client should always grab
	 * the token because it may change. <br>
	 * This method also handles a login attempt. If the token is not set (or is
	 * incorrect), then this method checks to see if an email and password are
	 * included in the request, if so it attempts to login said employee and returns
	 * an {@link Employee} object that has its fields set (particularly the token
	 * needed for subsequent accesses).
	 * 
	 * @param request the HTTP request being made.
	 * @return The employee making the request or <code>null</code> if the request
	 *         is unauthorized.
	 */
	protected final Employee authenticateEmployee(final HttpServletRequest request) {
		final Cookie[] cookies = request.getCookies();
		final String encryptedToken = getAuthToken(cookies);
		final Employee employee = empService.getEmployeeByToken(encryptedToken);

		Logger.getRootLogger().debug("Employee from authToken: " + employee);
		return employee;
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
		firstName.setMaxAge(COOKIE_TIME);
		lastName.setMaxAge(COOKIE_TIME);

		// need to set path so client always includes the cookie
		firstName.setPath(COOKIE_PATH);
		lastName.setPath(COOKIE_PATH);

		response.addCookie(firstName);
		response.addCookie(lastName);

		Logger.getRootLogger()
				.debug("Setting first and last name cookies: " + firstName.getValue() + " " + lastName.getValue());
	}

	/**
	 * Sets the cookie value (encrypted) for this employee so that the client can
	 * continue to access the server resources. The cookie is set to expire when the
	 * browser is closed.
	 * 
	 * @param employee {@link Employee} object which holds a token to be set.
	 * @param response The HTTP response to add the cookie to.
	 */
	protected static void setAuthorizationCookie(Employee employee, HttpServletResponse response) {
		final Cookie authToken = new Cookie(AUTH_TOKEN_HEADER, employee.getToken());

		// set cookie to be deleted when browser is closed
		authToken.setMaxAge(COOKIE_TIME);
		authToken.setDomain("localhost");
		// need to set the path so that the cookie is always sent
		authToken.setPath(COOKIE_PATH);

		response.addCookie(authToken);

		Logger.getLogger("Setting Authorization cookie: " + employee.getToken());
	}

	/**
	 * Extracts the (encrypted) cookie that holds the session token (if it exists).
	 * 
	 * @param cookies The cookies sent from the client.
	 * @return The (encrypted) session token or null if the cookie doesn't exist.
	 */
	private static String getAuthToken(Cookie[] cookies) {
		if (cookies != null) {
			for (final Cookie cookie : cookies) {
				Logger.getRootLogger().debug("Found cookie: " + cookie.getName() + ", " + cookie.getValue());
				if (cookie.getName().equals(AUTH_TOKEN_HEADER))
					return cookie.getValue();
			}
		} else {
			Logger.getRootLogger().debug("cookies was null");
		}
		// either there were no cookies or the auth-token wasn't set
		return null;
	}

	/**
	 * Authenticates the request by looking at the cookie. If authenticated,
	 * forwards to the {@link #processRequest} method. If not authenticated, it
	 * attempts to see if this request is a login request (by checking the request
	 * headers), if so it validates the user and, if validated, forwards to the
	 * {@link #processRequest}. If the request isn't authenticated and isn't a valid
	 * login attempt then this method redirects to the login page.
	 * 
	 * @param path     URI for this request.
	 * @param request  HTTP request being made.
	 * @param response response to be given for this request.
	 * @throws IOException
	 * @throws ServletException
	 */
	public void processRequest(final String path, final HttpServletRequest request, final HttpServletResponse response)
			throws IOException, ServletException {

		Logger.getRootLogger().debug("Requesting: " + path);

		// attempt to delete success cookie
		deleteSuccessCookie(request, response);

		// get the employee from the cookies sent with the request
		final Employee employee = authenticateEmployee(request);
		if (employee == null) {
			// forward to the login page
			request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
		} else {
			// the employee was authenticated, send this along to actually do something
			processRequest(employee, path, request, response);
		}
	}

	private void deleteSuccessCookie(HttpServletRequest request, HttpServletResponse response) {
//		final Cookie[] cookies = request.getCookies();
//		if(cookies != null) {
//		for(final Cookie cookie:cookies) {
//			if(cookie.getName().equals(ViewDelegate.SUCCESS_COOKIE)) {
//				// set cookie to expire when they recieve it
//				
//			}
//		}
//		}
	}

	/**
	 * This method should be implemented to add functionality to the
	 * {@link #processRequest} method. Sub-classes can assume that the Employee is
	 * non-null and is an authenticated employee (or manager).
	 * 
	 * @param employee {@link Employee} object used to perform data retrievals (if
	 *                 necessary).
	 * @param path     URI for this request.
	 * @param request  HTTP request being made
	 * @param response HTTP response to be returned.
	 * @throws ServletException
	 * @throws IOException
	 * @see #processRequest(String, HttpServletRequest, HttpServletResponse)
	 */
	protected abstract void processRequest(Employee employee, String path, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException;

}
