package com.revature.jfbennatt.ers.controller.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;
import org.apache.log4j.Logger;

import com.revature.jfbennatt.ers.controller.FrontController;
import com.revature.jfbennatt.ers.controller.RequestDispatcher;
import com.revature.jfbennatt.ers.models.Employee;

/**
 * This class is necessary to retrieve static resources while also preventing
 * the client from accessing unauthorized pages (e.g. the user isn't logged in)
 * if the user attempts to go directly to a static resource's URI (scripts
 * aren't blocked, so the user can look at all the javascript they want). The
 * default behavior of {@link Delegate} is to redirect the client to the login
 * page if they aren't logged in which is what normally should
 * happen&#8212;unless I'm trying to access the actual login page. Therefore
 * this class essentially re-writes the behavior of {@link Delegate} to avoid an
 * infinite loop of redirecting a non-logged-in user to the login page.
 * 
 * @author Jared F Bennatt
 *
 */
public class StaticDelegate extends Delegate {

	/**
	 * Prefix for my javascript files
	 */
	public static final String SCRIPT_PREFIX = "/scripts";

	/**
	 * necessary to have an entry point to the {@link DefaultServlet} to actually
	 * load static resources.
	 */
	private FrontController frontController = null;

	/**
	 * Default constructor.
	 */
	public StaticDelegate() {
		super();
	}

	/**
	 * This method uses {@link FrontController} to get static resources.
	 * 
	 * @param employee not used.
	 * @param path     not used
	 * @param request  HTTP request
	 * @param response HTTP response.
	 */
	@Override
	protected void processRequest(final Employee employee, final String path, final HttpServletRequest request,
			final HttpServletResponse response) throws IOException, ServletException {
		frontController.staticGet(request, response);
	}

	/**
	 * Overrides {@link Delegate#processRequest} to allow an "out" when the client
	 * attempts to access the login page (and also for loading scripts).
	 * 
	 * @param path     path of this request.
	 * @param request  HTTP request.
	 * @param response HTTP response.
	 */
	@Override
	public void processRequest(final String path, final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {

		Logger.getRootLogger().debug("Requesting: " + path);

		// check to see if this is any of my scripts, if so, just go ahead and give it
		// to them
		if (path.startsWith(RequestDispatcher.STATIC_PREFIX + SCRIPT_PREFIX)) {
			processRequest(null, path, request, response);
			return;
		}

		// This is copied from the Delegate class, the only difference here is what I do
		// when the employee is not logged in (without this method we'd end up in a loop
		// where, because the user isn't logged in, they can't access the login page).
		// So instead of forwarding to the login page, I need to check to see whether or
		// not it already IS the login page, if so, forward to front controller.

		final Employee employee = authenticateEmployee(request);
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

	/**
	 * Sets the {@link FrontController} object to be used to actually fetch the
	 * static resources.
	 * 
	 * @param frontController Front controller to fetch static resources.
	 */
	public void setFrontController(FrontController frontController) {
		this.frontController = frontController;
	}

}
