package com.revature.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewDelegate {

	public void resolveView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case "/":
			req.getRequestDispatcher("/static/loginPage.html").forward(req, res);
			break;
		case "/employeehome":
			req.getRequestDispatcher("/static/employeePage.html").forward(req, res);
			break;
		case "/managerhome":
			req.getRequestDispatcher("/static/managerPage.html").forward(req, res);
		case "/profile":
			req.getRequestDispatcher("/static/profile.html").forward(req, res);
		default:
			res.sendError(404, "Resource not found");
		}
	}
	
}
