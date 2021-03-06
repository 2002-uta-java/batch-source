package com.revature.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewDelegate {
	
	public void resolveView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getServletPath();
		System.out.println("in our view delegate: "+ uri);
		
		switch(uri) {
		case "/new":
			request.getRequestDispatcher("/static/Views/NewBird.html").forward(request, response);
			break;
		case "/directory":
			request.getRequestDispatcher("/static/Views/BirdDirectory.html").forward(request, response);
			break;
		default:
			response.sendError(404, "Static Resource Not Found");
		}
	}

}
