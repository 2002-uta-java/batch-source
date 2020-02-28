package com.revature.jfbennatt.ers.controller.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.jfbennatt.ers.models.Employee;

public class EmployeeViewDelegate extends Delegate {
	public static final String EMPLOYEE_HOME_PAGE = "/static/Employee/Home.html";

	public EmployeeViewDelegate() {
		super();
	}

	@Override
	protected void processRequest(final Employee employee, final HttpServletRequest request,
			final HttpServletResponse response) throws IOException, ServletException {
		final String path = request.getServletPath();

		switch (path) {
		case HOME:
			resolveHomePage(request, response);
			break;
		default:
			response.sendError(404);
		}
	}

	private void resolveHomePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(EMPLOYEE_HOME_PAGE).forward(request, response);
	}

}
