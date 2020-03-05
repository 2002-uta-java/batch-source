package com.project1.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.project1.delegates.AuthDelegate;
import com.project1.delegates.EmployeeDelegate;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/")
public class FrontController extends DefaultServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger LOGGER = LogManager.getLogger(FrontController.class.getName());
	private RequestHelper requestHelper = new RequestHelper();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println(path);
		if(path.startsWith("/static/")) {
			super.doGet(request, response);
			LOGGER.info("doGet, process static file");
		} else {
			// route GET request to appropriate delegate 
			requestHelper.processGet(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("doPost, process static file");
		requestHelper.processPost(request, response);
	}
	
	/**
	 * @see HttpServlet#doPut(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("doPut, process static file");
		requestHelper.processPut(request, response);
	}

}




