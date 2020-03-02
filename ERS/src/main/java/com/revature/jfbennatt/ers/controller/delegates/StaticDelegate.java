package com.revature.jfbennatt.ers.controller.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.jfbennatt.ers.controller.FrontController;
import com.revature.jfbennatt.ers.models.Employee;

public class StaticDelegate extends Delegate {

	public static final String SCRIPT_PREFIX = "/static/scripts";
	private FrontController frontController = null;

	@Override
	public void processRequest(final String path, final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {

		Logger.getRootLogger().debug("Requesting: " + path);
		System.out.println("Requesting: " + path);

		// check to see if this is any of my scripts, if so, just go ahead and give it
		// to them
		if (path.startsWith(SCRIPT_PREFIX)) {
			processRequest(null, path, request, response);
			return;
		}

		// This is copied from the Delegate class, the only difference here is what I do
		// when the employee is not logged in (without this method we'd end up in a loop
		// where, because the user isn't logged in, they can't access the login page).
		// So instead of forwarding to the login page, I need to check to see whether or
		// not it already IS the login page, if so, forward to front controller.

		final Employee employee = authenticateEmployee(request, response);
		if (employee == null) {
			// check the path, if it's the login page, pass it along to the abstract method
			// (where it just loads the static resource)
			if (path.equals(LOGIN_PAGE)) {
				processRequest(null, path, request, response);
			} else {
				// forward to the login page
				request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
			}
		} else {
			// the employee was authenticated, send this along to actually do something
			processRequest(employee, path, request, response);
		}

	}

	@Override
	protected void processRequest(Employee employee, String path, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		frontController.staticGet(request, response);
	}

	public void setFrontController(FrontController frontController) {
		this.frontController = frontController;
	}

}
