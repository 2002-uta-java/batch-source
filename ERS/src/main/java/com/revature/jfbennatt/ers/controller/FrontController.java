package com.revature.jfbennatt.ers.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.servlets.DefaultServlet;

import com.revature.jfbennatt.ers.controller.delegates.Delegate;
import com.revature.jfbennatt.ers.controller.delegates.ViewDelegate;
import com.revature.jfbennatt.ers.services.EmployeeService;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends DefaultServlet {
	private static final long serialVersionUID = 1L;

	public static final String STATIC = "/static";

	private final Delegate viewDelegate = new ViewDelegate();

	/**
	 * @see DefaultServlet#DefaultServlet()
	 */
	public FrontController() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("adding employee service to viewDelegate");
		viewDelegate.setEmployeeService(new EmployeeService());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String path = request.getServletPath();
		if (path.startsWith(STATIC)) {
			super.doGet(request, response);
		} else {
			viewDelegate.processRequest(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
