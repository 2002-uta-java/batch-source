package com.revature.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewDelegate {
	public void resolveView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String uri = request.getServletPath();
		System.out.println("from view delegate: " + uri);

		switch (uri) {
		case "/new":
			request.getRequestDispatcher("static/Views/NewBird.html").forward(request, response);
			break;
		case "/directory":
			request.getRequestDispatcher("static/views/BirdDirectory.html").forward(request, response);
			break;
		default:
			response.sendError(404, "static resource not found");
		}
	}
}
