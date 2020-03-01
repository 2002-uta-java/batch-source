package com.revature.delegate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewDelegate {

	public void resolveView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		switch(path) {
		case "/index":
			request.getRequestDispatcher("/static/Views/index.html").forward(request, response);
			break;
		case "/employeepage":
			request.getRequestDispatcher("/static/Views/employeepage.html").forward(request, response);
			break;
		case "/managerpage":
			request.getRequestDispatcher("/static/Views/managerpage.html").forward(request, response);
			break;
		default:
			response.sendError(404, "Static Resource Not Found");
		}
	}

}
