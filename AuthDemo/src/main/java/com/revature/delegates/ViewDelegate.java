package com.revature.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewDelegate {
	public void resolveView(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		final String path = request.getServletPath();

		switch (path) {
		case "/login":
			request.getRequestDispatcher("/static/Login.html").forward(request, response);
			break;
		case "/home":
			request.getRequestDispatcher("/static/Home.html").forward(request, response);
			break;
		default:
			response.sendError(404, "static resource not found");
		}
	}
}
