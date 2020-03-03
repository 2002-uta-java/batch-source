package com.revature.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewDelegate {
	
	public void resolveView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getServletPath();
		
		switch(uri) {
		case "/login":
			request.getRequestDispatcher("/static/views/login.html").forward(request, response);
			break;
		case "/home":
			request.getRequestDispatcher("/static/views/home.html").forward(request, response);
			break;
		default:
			response.sendError(404, "Static Resource Not Found");
		}
	}

}
