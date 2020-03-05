package com.revature.project1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;
import org.apache.log4j.Logger;

public class FrontController extends DefaultServlet {

	private static final long serialVersionUID = 1L;
	
	static final Logger logger = Logger.getLogger(FrontController.class);
	private RequestHelper requestHelper = new RequestHelper();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() {
		super();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		
		System.out.println(path +" do get front controller");
		logger.info("doGet front contoller: "+path);
		
		if(path.startsWith("/static")) {
			super.doGet(request, response);
		} else {
			System.out.println(path);
			logger.info(path);

			requestHelper.processGet(request, response);
		}
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		
		System.out.println(path + " do post fc");
		logger.info("doPost front controller: "+path);
		
		requestHelper.processPost(request, response);
	}

}
