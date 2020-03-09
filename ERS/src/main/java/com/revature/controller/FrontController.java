package com.revature.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;

/**
 * Servlet implementation class FrontController
 */

public class FrontController extends DefaultServlet {
	private static final long serialVersionUID = 1L;

	private RequestHelper requestHelper = new RequestHelper();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("In FrontControler doget()");
		String contextpath = request.getContextPath();
		String path = request.getRequestURI().substring(contextpath.length());
		System.out.println(path);
		if (path.startsWith("/static")) {
			super.doGet(request, response);
		} else {
			// route GET request to appropriate delegate
			requestHelper.processGet(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//System.out.println("In FrontControler doget()");
		// TODO Auto-generated method stub
		//doGet(request, response);
		String path = request.getRequestURI().substring(request.getContextPath().length());
		if (path.startsWith("/static/")) {
			super.doGet(request, response);
		} else {
			// route GET request to appropriate delegate
			requestHelper.processPost(request, response);

		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String path = request.getRequestURI().substring(request.getContextPath().length());
		if (path.startsWith("/static/")) {
			super.doGet(request, response);
		} else {
			// route GET request to appropriate delegate
			requestHelper.processUpdate(request, response);

		}
	}

}
