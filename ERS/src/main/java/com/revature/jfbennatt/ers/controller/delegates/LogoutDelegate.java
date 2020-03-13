package com.revature.jfbennatt.ers.controller.delegates;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.jfbennatt.ers.controller.RequestDispatcher;
import com.revature.jfbennatt.ers.services.EmployeeService;

/**
 * Delegate used to log a user out and redirect to the login page. This class
 * does not {@link Delegate} because it handles redirection a little
 * differently.
 * 
 * @author Jared F Bennatt
 *
 */
public class LogoutDelegate {
	/**
	 * Attempts to delete the client's cookies by setting the expire time to 0.
	 * 
	 * @param cookies Cookies send from the client to be deleted.
	 */
	private static void deleteCookies(final Cookie[] cookies) {
		if (cookies != null) {
			Logger.getRootLogger().debug("Deleting cookies");
			for (final Cookie cookie : cookies) {
				cookie.setMaxAge(0);
			}
		}
	}

	/**
	 * Returns the session token from the cookie recieved from the client (this is
	 * needed to find and delete it via the employee service).
	 * 
	 * @param cookies Cookies recieved from with the request.
	 * @return The (encrypted) session token from the client.
	 */
	private static String getAuthToken(Cookie[] cookies) {
		for (final Cookie cookie : cookies) {
			Logger.getRootLogger().debug("Found cookie: " + cookie.getName() + ", " + cookie.getValue());
			if (cookie.getName().equals(Delegate.AUTH_TOKEN_HEADER))
				return cookie.getValue();
		}
		// the auth-token wasn't set--this better not happen!!!
		return null;
	}

	private EmployeeService empService = null;

	/**
	 * Default constructor.
	 */
	public LogoutDelegate() {
		super();
	}

	/**
	 * Logs a user out: attempts to delete the user's cookies (sets expire time to
	 * 0&#8212;so the browser may or may not respect that), deletes the session
	 * token in the database (so even if the client attempts to reuse their cookie,
	 * the authentication will fail), and finally redirects the client to the login
	 * page.
	 * 
	 * @param request  HTTP request.
	 * @param response HTTP response
	 * @throws IOException
	 */
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// need to remove the token from the database and delete the cookies.
		final Cookie[] cookies = request.getCookies();
		final String authToken = getAuthToken(cookies);

		// delete token from database
		empService.deleteSessionTokenByToken(authToken);

		if (cookies != null)
			deleteCookies(cookies);

		// redirect to login page
		response.sendRedirect(RequestDispatcher.CONTEXT_ROOT + Delegate.HOME);
	}

	/**
	 * Sets the {@link EmployeeService} for this delegate.
	 * 
	 * @param empService {@link EmployeeService} to be used by this delegate.
	 */
	public void setEmployeeService(EmployeeService empService) {
		this.empService = empService;
	}

}
