package com.revature.jfbennatt.ers.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.jfbennatt.ers.controller.delegates.ApprovalDelegate;
import com.revature.jfbennatt.ers.controller.delegates.ChangeProfileDelegate;
import com.revature.jfbennatt.ers.controller.delegates.Delegate;
import com.revature.jfbennatt.ers.controller.delegates.GetAllEmployeesDelegate;
import com.revature.jfbennatt.ers.controller.delegates.LoginDelegate;
import com.revature.jfbennatt.ers.controller.delegates.LogoutDelegate;
import com.revature.jfbennatt.ers.controller.delegates.StaticDelegate;
import com.revature.jfbennatt.ers.controller.delegates.SubmitReimbursementDelegate;
import com.revature.jfbennatt.ers.controller.delegates.ViewDelegate;
import com.revature.jfbennatt.ers.controller.delegates.ViewReimbursementsDelegate;
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
	 * Suffix for logging out.
	 */
	public static final String LOGOUT = "/logout";
	/**
	 * Suffix for submitting a reimbursement request.
	 */
	public static final String SUBMIT_REIMBURSEMENT = "/submit";
	/**
	 * Root URI (after {@link #API} prefix for viewing reimbursements. The default
	 * behavior is to send "all" reimbursements (managers will get more than
	 * employees)
	 */
	public static final String VIEW_REIMBURSEMENT_ROOT = "/view";
	/**
	 * URI for changing the profile.
	 */
	public static final String CHANGE_PROFILE = "/change";

	/**
	 * api call for getting all employees (except manager calling method)
	 */
	public static final String GET_ALL_EMPLOYEES = "/employees";
	public static final String APPROVALS = "/approvals";

	/**
	 * delegate for fetching static resources (from an api call)
	 */
	private final ViewDelegate viewDelegate;
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
	 * Delegate for submitting a reimbursement request.
	 */
	private final Delegate submitDelegate;
	/**
	 * Delegate for viewing reimbursements
	 */
	private final Delegate viewReimbDelegate;
	/**
	 * Delegate for changing the profile
	 */
	private final Delegate changeDelegate;
	/**
	 * Delegate for getting all employees (for manager)
	 */
	private final Delegate getEmployeesDelegate;
	private final Delegate approvalsDelegate;

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
		this.submitDelegate = new SubmitReimbursementDelegate();
		this.viewReimbDelegate = new ViewReimbursementsDelegate();
		this.changeDelegate = new ChangeProfileDelegate();
		this.getEmployeesDelegate = new GetAllEmployeesDelegate();
		this.approvalsDelegate = new ApprovalDelegate();

		this.empService.setEmployeeDao(new EmployeeDaoPostgres());
		this.viewDelegate.setEmployeeService(empService);
		this.loginDelegate.setEmployeeService(empService);
		this.staticDelegate.setEmployeeService(empService);
		this.logoutDelegate.setEmployeeService(empService);
		this.submitDelegate.setEmployeeService(empService);
		this.viewReimbDelegate.setEmployeeService(empService);
		this.changeDelegate.setEmployeeService(empService);
		this.getEmployeesDelegate.setEmployeeService(empService);
		this.approvalsDelegate.setEmployeeService(empService);
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
			final String apiPath = path.substring(API.length());
			// route api calls
			switch (apiPath) {
			case LOGIN:
				loginDelegate.processRequest(path, request, response);
				break;
			case LOGOUT:
				logoutDelegate.processRequest(request, response);
				break;
			case SUBMIT_REIMBURSEMENT:
				submitDelegate.processRequest(path, request, response);
				break;
			case CHANGE_PROFILE:
				changeDelegate.processRequest(path, request, response);
				break;
			case GET_ALL_EMPLOYEES:
				getEmployeesDelegate.processRequest(path, request, response);
				break;
			}

			if (apiPath.startsWith(VIEW_REIMBURSEMENT_ROOT)) {
				viewReimbDelegate.processRequest(apiPath, request, response);
			} else if (apiPath.startsWith(APPROVALS)) {
				approvalsDelegate.processRequest(apiPath.substring(APPROVALS.length()), request, response);
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
