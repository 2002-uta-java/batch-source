package com.revature.jfbennatt.ers.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.jfbennatt.ers.controller.delegates.Delegate;
import com.revature.jfbennatt.ers.controller.delegates.LoginDelegate;
import com.revature.jfbennatt.ers.controller.delegates.LogoutDelegate;
import com.revature.jfbennatt.ers.controller.delegates.StaticDelegate;
import com.revature.jfbennatt.ers.controller.delegates.ViewDelegate;
import com.revature.jfbennatt.ers.daos.postgres.EmployeeDaoPostgres;
import com.revature.jfbennatt.ers.services.EmployeeService;

/**
 * This class routes requests from the {@link FrontController} to the
 * appropriate {@link Delegate}
 * 
 * @author Jared F Bennatt
 *
 */
public class RequestDispatcher {

	/**
	 * Prefix for static routes
	 */
	public static final String STATIC_PREFIX = "/static";
	/**
	 * Context root for this web app.
	 */
	public static final String CONTEXT_ROOT = "/ERS";

	/**
	 * Prefix for api calls.
	 */
	public static final String API = "/api";
	/**
	 * Suffix for a login attempt.
	 */
	public static final String LOGIN = "/login";
	/**
	 * Suffic for logging out.
	 */
	public static final String LOGOUT = "/logout";

	/**
	 * delegate for fetching static resources (from an api call)
	 */
	private final Delegate viewDelegate;
	/**
	 * Employee service needed for the delegates (initialized in constructor)
	 */
	private final EmployeeService empService;
	/**
	 * Delegate for attempting to login
	 */
	private final Delegate loginDelegate;
	/**
	 * Delegate for handling static page requests.
	 */
	private final StaticDelegate staticDelegate;
	/**
	 * Delegate for logging out.
	 */
	private final LogoutDelegate logoutDelegate;

	/**
	 * Default constructor (sets up all internals).
	 */
	public RequestDispatcher() {
		super();
		this.empService = new EmployeeService();
		this.viewDelegate = new ViewDelegate();
		this.loginDelegate = new LoginDelegate();
		this.staticDelegate = new StaticDelegate();
		this.logoutDelegate = new LogoutDelegate();

		this.empService.setEmployeeDao(new EmployeeDaoPostgres());
		this.viewDelegate.setEmployeeService(empService);
		this.loginDelegate.setEmployeeService(empService);
		this.staticDelegate.setEmployeeService(empService);
		this.logoutDelegate.setEmployeeService(empService);
	}

	/**
	 * Dispatches HTTP requests by looking at the path and determining the proper
	 * delegate to forward the request to.
	 * 
	 * @param request  HTTP request.
	 * @param response HTTP response.
	 * @throws IOException
	 * @throws ServletException
	 */
	public void dispatch(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException, ServletException {
		final String path = request.getServletPath();

		// send static requests to the static delegate
		if (path.startsWith(STATIC_PREFIX)) {
			staticDelegate.processRequest(path, request, response);
		} else if (path.startsWith(API)) {
			// route api calls
			switch (path.substring(API.length())) {
			case LOGIN:
				loginDelegate.processRequest(path, request, response);
				break;
			case LOGOUT:
				logoutDelegate.processRequest(request, response);
				break;
			default:
				response.sendError(404);
			}
		} else {
			// route page requests
			viewDelegate.processRequest(path, request, response);
		}
	}

	/**
	 * Some of the delegate/s need the {@link FrontController} to perform their
	 * operations. This gives the front controller to those delegate/s.
	 * 
	 * @param frontController {@link FrontController} to be used by whatever
	 *                        delegates need it.
	 */
	public void setFrontController(final FrontController frontController) {
		this.staticDelegate.setFrontController(frontController);
	}

}
