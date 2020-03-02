package com.revature.jfbennatt.ers.controller.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.jfbennatt.ers.controller.RequestDispatcher;
import com.revature.jfbennatt.ers.services.EmployeeService;

public class LogoutDelegate {
	private EmployeeService empService = null;

	public void setEmployeeService(EmployeeService empService) {
		this.empService = empService;
	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// need to remove the token from the database and delete the cookies.
		final Cookie[] cookies = request.getCookies();
		final String authToken = getAuthToken(cookies);

		// delete token from database
		empService.deleteSessionToken(authToken);

		if (cookies != null)
			deleteCookies(cookies);

		// redirect to login page
		response.sendRedirect(RequestDispatcher.CONTEXT_ROOT + Delegate.HOME);
	}

	private static void deleteCookies(final Cookie[] cookies) {
		if (cookies != null) {
			Logger.getRootLogger().debug("Deleting cookies");
			System.out.println("Deleting cookies");
			for (final Cookie cookie : cookies) {
				cookie.setMaxAge(0);
			}
		}
	}

	private static String getAuthToken(Cookie[] cookies) {
		for (final Cookie cookie : cookies) {
			Logger.getRootLogger().debug("Found cookie: " + cookie.getName() + ", " + cookie.getValue());
			System.out.println("Found cookie: " + cookie.getName() + ", " + cookie.getValue());
			if (cookie.getName().equals(Delegate.AUTH_TOKEN_HEADER))
				return cookie.getValue();
		}
		// the auth-token wasn't set
		return null;
	}

}
