package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldServlet extends HttpServlet {

	/**
	 * default from eclipse
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) {
		System.out.println("init was called");
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("service method was called: " + request.getMethod() + " to " + request.getRequestURI());
	}

	@Override
	public void destroy() {
		System.out.println("destroy was called");
	}
}
