package com.revature.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * view delegate this will tell the sever which page to go to
 * 
 * @author erpac
 *
 */
public class ViewDelegate {
	public void resolveView(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String path = request.getServletPath();

		// Checks the cases and forwards the specific html file to send and render in
		// the browser.
		switch (path) {
		case "/login":
			request.getRequestDispatcher("/static/Views/login.html").forward(request, response);
			break;
		case "/employee":
			request.getRequestDispatcher("/static/Views/employee.html").forward(request, response);
			break;
		case "/manager":
			request.getRequestDispatcher("/static/Views/manager.html").forward(request, response);
			break;
		default:
			request.getRequestDispatcher("/static/Views/login.html").forward(request, response);
			break;

		}
	}
}
