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
	
	private static RequestHelper requestHelper = new RequestHelper();
	
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
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		if(path.startsWith("/static/")) {
			try{
				super.doGet(request, response);
			}
			catch(IOException | ServletException e) {
				e.getStackTrace();
			}
		}
		else {
			try {
				requestHelper.processGet(request, response);  //route GET request to appropriate delegate
			}
			catch(IOException | ServletException e) {
				e.getStackTrace();
			}
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			requestHelper.processPost(request, response);
		}
		catch(IOException e) {
			e.getStackTrace();
		}
	}
	
}
