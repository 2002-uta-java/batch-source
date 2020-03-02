package com.revature.jfbennatt.ers.controller.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.jfbennatt.ers.models.Employee;

public class ViewDelegate extends Delegate {
	public static final String EMPLOYEE_HOME_PAGE = "/static/Employee/Home.html";
	public static final String MANAGER_HOME_PAGE = "/static/Manager/Home.html";

	public ViewDelegate() {
		super();
	}

	/**
	 * Processes the given request by fetching the static page for the request.
	 */
	@Override
	protected void processRequest(final Employee employee, final String path, final HttpServletRequest request,
			final HttpServletResponse response) throws IOException, ServletException {
		if (employee.isManager()) {
			resolveManagerView(path, request, response);
		} else {
			resolveEmployeeView(path, request, response);
		}
	}

	private void resolveEmployeeView(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		switch (path) {
		case HOME:
			request.getRequestDispatcher(EMPLOYEE_HOME_PAGE).forward(request, response);
			break;
		default:
			response.sendError(404);
		}
	}

	private void resolveManagerView(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		switch (path) {
		case HOME:
			request.getRequestDispatcher(MANAGER_HOME_PAGE).forward(request, response);
			break;
		default:
			response.sendError(404);
		}
	}
}
