package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewServlet {

	public void resolveView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case "/static/":
			req.getRequestDispatcher("/static/loginPage.html").forward(req, res);
			break;
		case "/static/employeehome":
			req.getRequestDispatcher("/static/employeePage.html").forward(req, res);
			break;
		case "/static/managerhome":
			req.getRequestDispatcher("/static/managerPage.html").forward(req, res);
		case "/static/profile":
			req.getRequestDispatcher("/static/profile.html").forward(req, res);
		default:
			res.sendError(404, "Resource not found");
		}
	}
	
}
