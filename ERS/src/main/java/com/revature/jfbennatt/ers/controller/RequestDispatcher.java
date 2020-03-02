package com.revature.jfbennatt.ers.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.jfbennatt.ers.controller.delegates.Delegate;
import com.revature.jfbennatt.ers.controller.delegates.LoginDelegate;
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
	public static final String API = "/api";
	public static final String LOGIN = "/login";

	private final Delegate viewDelegate;
	private final EmployeeService empService;
	private final Delegate loginDelegate;

	public RequestDispatcher() {
		super();
		this.empService = new EmployeeService();
		this.viewDelegate = new ViewDelegate();
		this.loginDelegate = new LoginDelegate();

		this.empService.setEmployeeDao(new EmployeeDaoPostgres());
		this.viewDelegate.setEmployeeService(empService);
		this.loginDelegate.setEmployeeService(empService);
	}

	public void dispatch(final String path, final HttpServletRequest request, final HttpServletResponse response)
			throws IOException, ServletException {
		if (path.startsWith(API)) {
			switch (path.substring(API.length())) {
			case LOGIN:
				loginDelegate.processRequest(path, request, response);
				break;
			default:
				response.sendError(404);
			}
		} else {
			viewDelegate.processRequest(path, request, response);
		}
	}

}
