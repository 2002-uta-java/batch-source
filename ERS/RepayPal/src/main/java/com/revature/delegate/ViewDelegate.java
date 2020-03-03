package com.revature.delegate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewDelegate {

	public void resolveView(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String path = request.getServletPath();
		switch(path) {
		case "/login":
			request.getRequestDispatcher("/static/Login.html").forward(request, response);
			break;
		case "/home":
			request.getRequestDispatcher("/static/Homepage.html").forward(request, response);
			break;
		case "/logout":
			request.getRequestDispatcher("/static/Logout.html").forward(request, response);
			break;
		default:
			response.sendError(404, "Static Resource Not Found");
		}
	
	}

}
