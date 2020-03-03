package com.revature.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/")
public class FrontController extends DefaultServlet {
	private static final long serialVersionUID = 1L;
	
	private RequestHelper requestHelper = new RequestHelper();
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() {
		super();
	}
	
	/**
	 * @throws ServletException 
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		if(path.startsWith("/static/"))
			super.doGet(request, response);
		else
			requestHelper.processGet(request, response);  //route GET request to appropriate delegate
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestHelper.processPost(request, response);
	}
	
	
}
