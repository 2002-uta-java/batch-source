package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.servlets.EmployeeServlet;
import com.revature.servlets.LoginServlet;
import com.revature.servlets.ViewServlet;

public class RequestHelper {
	
	private LoginServlet login = new LoginServlet();
	private ViewServlet view = new ViewServlet();
	private EmployeeServlet employee = new EmployeeServlet();
	public void processGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.startsWith("/loginpage")) {
			req.getRequestDispatcher("/static/loginPage.html").forward(req, res);
		} else if (path.startsWith("/api/")) {
			if (!login.isAuthorized(req, res)) {
				res.sendError(401);
				return;
			}
			
			String record = path.substring(5);
			if (record.startsWith("reimbursments"))
				employee.getEmployee(req, res);
			else
				res.sendError(404, "Record(s) Not Found");
		} else
			view.resolveView(req, res);
	}
	
	public void processPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String path = req.getServletPath();
		System.out.println(path);
		switch (path) {
		case "/loginpage":
			login.authenticate(req, res);
			break;
		case "/register":
			login.register(req, res);
			break;
		default:
			res.sendError(405);
		}
	}
}
