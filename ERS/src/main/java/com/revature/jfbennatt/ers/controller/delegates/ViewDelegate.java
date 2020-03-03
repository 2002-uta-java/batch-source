package com.revature.jfbennatt.ers.controller.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.jfbennatt.ers.models.Employee;

/**
 * This routes requests for pages (e.g. /) to the appropriate static page.
 * 
 * @author Jared F Bennatt
 *
 */
public class ViewDelegate extends Delegate {
	/**
	 * URI of the static employee home page
	 */
	public static final String EMPLOYEE_HOME_PAGE = "/static/Employee/Home.html";
	/**
	 * URI of the static manager home page
	 */
	public static final String MANAGER_HOME_PAGE = "/static/Manager/Home.html";

	/**
	 * Default constructor.
	 */
	public ViewDelegate() {
		super();
	}

	/**
	 * Routes the client to the employee page or manager page (depending on the {@
	 * Employee} object.
	 * 
	 * @param employee {@link Employee} object that represents the user.
	 * @param path     Path of the request.
	 * @param request  HTTP request.
	 * @param response HTTP response.
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

	/**
	 * Handles employee view requests.
	 * 
	 * @param path     Servlet path of the resource being requested.
	 * @param request  HTTP request.
	 * @param response HTTP response
	 * @throws ServletException
	 * @throws IOException
	 */
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

	/**
	 * Handles manager view requests.
	 * 
	 * @param path     Servlet path of the resource being requested.
	 * @param request  HTTP request.
	 * @param response HTTP response
	 * @throws ServletException
	 * @throws IOException
	 */
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
