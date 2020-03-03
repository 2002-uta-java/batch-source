package com.revature.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewDelegate {

	public void resolveView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String path = req.getServletPath();
		System.out.println(path);
		switch (path) {
		case "/":
			req.getRequestDispatcher("/static/Views/loginPage.html").forward(req, res);
			break;
		case "/employeehome":
			req.getRequestDispatcher("/static/Views/employeepage.html").forward(req, res);
			break;
		case "/profile":
			req.getRequestDispatcher("/static/Views/profile.html").forward(req, res);
		default:
			res.sendError(404, "Resource not found");
		}
	}
	
}
