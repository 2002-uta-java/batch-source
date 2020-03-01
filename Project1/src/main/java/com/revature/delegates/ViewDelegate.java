package com.revature.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewDelegate {
	
	public void resolveView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getServletPath();
		System.out.println("in our view delegate: " + uri);
		
		switch(uri) {
		case "/login":
			request.getRequestDispatcher("static/Views/Login.html").forward(request, response);
			break;
		case "/home":
			request.getRequestDispatcher("/static/Views/Home.html").forward(request, response);
			break;
		case "/homemanager":
			request.getRequestDispatcher("/static/Views/HomeManager.html").forward(request, response);
		case "/homeemployee":
			request.getRequestDispatcher("/static/Views/HomeEmployee.html").forward(request, response);
		
		default:
			response.sendError(404, "Static Resource Not Found");
		}
		
	}
}
