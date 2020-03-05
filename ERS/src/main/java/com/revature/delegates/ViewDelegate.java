package com.revature.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewDelegate {

	public void resolveView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getServletPath();
		System.out.println("In View delegate");
		System.out.println(uri);
		switch (uri) {
		case "/":{
			request.getRequestDispatcher("/static/views/Login.html").forward(request, response);
			break;
		}
		case "/employeehome":{
			request.getRequestDispatcher("/static/views/HomePage.html").forward(request, response);
			break;
		}
		case "/reimbursement":{
			request.getRequestDispatcher("/static/views/reimbursement.html").forward(request, response);
			break;
		}
		case "/profile":{
			request.getRequestDispatcher("/static/views/profile.html").forward(request, response);
		}
		default: {
			response.sendError(404, "The static resource you requested does not exist.");
			break;
		}
		}
	}
}