package com.revature.jfbennatt.ers.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.servlets.DefaultServlet;

/**
 * Front controller that handles all request.
 * 
 * @author Jared F Bennatt
 */
public class FrontController extends DefaultServlet {
	/**
	 * generatd by eclipse
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Used to actually route (non-static) calls to the servlet.
	 */
	private final RequestDispatcher dispatcher = new RequestDispatcher();

	/**
	 * Default constructor.
	 * 
	 * @see DefaultServlet#DefaultServlet()
	 */
	public FrontController() {
		super();
	}

	/**
	 * Sets up any resources needed for this servlet
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		dispatcher.setFrontController(this);
	}

	/**
	 * This calls the super method of the {@link DefaultServlet} class.
	 * 
	 * @param request  Http request.
	 * @param response Https response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void staticGet(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException, ServletException {
		super.doGet(request, response);
	}

	/**
	 * Routes calls for HTTP GET requests.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dispatcher.dispatch(request, response);
	}

	/**
	 * Routes calls for HTTP POST requests.
	 * 
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
