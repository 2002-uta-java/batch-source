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

	public static final String STATIC_PREFIX = "/static";
	public static final String CONTEXT_ROOT = "/ERS";

	public static final String API = "/api";
	public static final String LOGIN = "/login";
	public static final String LOGOUT = "/logout";

	private final Delegate viewDelegate;
	private final EmployeeService empService;
	private final Delegate loginDelegate;
	private final StaticDelegate staticDelegate;
	private final LogoutDelegate logoutDelegate;

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

	public void dispatch(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException, ServletException {
		final String path = request.getServletPath();

		if (path.startsWith(STATIC_PREFIX)) {
			staticDelegate.processRequest(path, request, response);
		} else if (path.startsWith(API)) {
			switch (path.substring(API.length())) {
			case LOGIN:
				loginDelegate.processRequest(path, request, response);
				break;
			case LOGOUT:
				logoutDelegate.processRequest(request, response);
			default:
				response.sendError(404);
			}
		} else {
			viewDelegate.processRequest(path, request, response);
		}
	}

	public void setFrontController(FrontController frontController) {
		this.staticDelegate.setFrontController(frontController);
	}

}
