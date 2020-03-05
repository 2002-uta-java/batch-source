package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/")
public class FrontController extends DefaultServlet{
	private static final long serialVersionUID = 1L;
	
	private RequestHelper requestHelper = new RequestHelper();
	private static Logger log = Logger.getRootLogger();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
	public FrontController() {
		super();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		log.info("GET" + path);
		
		if(path.startsWith("/static")) {
			super.doGet(request, response);
		} else {
			requestHelper.processGet(request, response);
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("POST");
		requestHelper.processPost(request, response);
	}
	
}
