package com.project1.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ViewDelegate {
	private static final Logger LOGGER = LogManager.getLogger(ViewDelegate.class.getName());
	
	public void resolveView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getServletPath();
		System.out.println("in our view delegate: "+ uri);
		
		switch(uri) {
		case "/login":
			LOGGER.info("login page!");
			request.getRequestDispatcher("/static/Views/login.html").forward(request, response);
			break;
		case "/employee-homepage":
			LOGGER.info("employee homepage!");
			request.getRequestDispatcher("/static/Views/employee-homepage.html").forward(request, response);
			break;
		case "/manager-homepage":
			LOGGER.info("manager homepage!");
			request.getRequestDispatcher("/static/Views/manager-homepage.html").forward(request, response);
			break;
		case "/employee":
			LOGGER.info("view all employees-a testing page!");
			request.getRequestDispatcher("/static/Views/all-employees.html").forward(request, response);
			break;
		default:
			LOGGER.info("Static Resources Not Found!");
			response.sendError(404, "Static Resource Not Found");
		}
	}
}
