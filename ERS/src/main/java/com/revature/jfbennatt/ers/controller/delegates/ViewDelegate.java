package com.revature.jfbennatt.ers.controller.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.jfbennatt.ers.controller.RequestDispatcher;
import com.revature.jfbennatt.ers.models.Employee;

/**
 * This routes requests for pages (e.g. /) to the appropriate static page. This
 * needs to communicate with the {@link RequestDispatcher} so should be
 * explicitly declared (i.e. not as a {@link Delegate}).
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
	 * URI of the static submit reimbursement page.
	 */
	public static final String SUBMIT_REIMBURSEMENT_PAGE = "/static/Employee/Submit-Reimbursement.html";
	/**
	 * URI of the static view reimbursement page
	 */
	public static final String VIEW_REIMBURSEMENT_PAGE = "/static/Employee/View-Reimbursement.html";

	/**
	 * URI of the submit reimbursement resource
	 */
	public static final String SUBMIT_REIMBURSEMENT = "/submit";
	/**
	 * URI of the view reimbursement resource
	 */
	public static final String VIEW_REIMBURSEMENT = "view";

	/**
	 * Cookie message (value) representing a failure
	 */
	public static final String FAIL = "fail";
	/**
	 * Cookie message (value) representing a successful action
	 */
	public static final String SUCCESS = "success";

	private RequestDispatcher dispatcher = null;

	/**
	 * Default constructor.
	 */
	public ViewDelegate() {
		super();
	}

	public void setRequestDispatcher(final RequestDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	/**
	 * Routes the client to the employee page or manager page (depending on the
	 * {@link Employee} object).
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
			setMessageCookie(response);
			request.getRequestDispatcher(EMPLOYEE_HOME_PAGE).forward(request, response);
			break;
		case SUBMIT_REIMBURSEMENT:
			request.getRequestDispatcher(SUBMIT_REIMBURSEMENT_PAGE).forward(request, response);
			break;
		default:
			response.sendError(404);
		}
	}

	private void setMessageCookie(final HttpServletResponse response) {
		final String message = dispatcher.consumeMessage();
		Logger.getRootLogger().debug("getting message from dispatcher");
		if (message != null) {
			Logger.getRootLogger().debug("Setting cookie to :" + message + " and max age = 5");
			Logger.getRootLogger().debug("Request dispatcher's message: " + dispatcher.consumeMessage());
			final Cookie messageCookie = new Cookie(Delegate.SUCCESS_COOKIE, message);
			// don't store this cookie, it's a one and done thing
			messageCookie.setMaxAge(5);
			response.addCookie(messageCookie);
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
