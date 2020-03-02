package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.delegates.EmployeeDelegate;
import com.revature.delegates.LoginDelegate;
import com.revature.delegates.ViewDelegate;

public class RequestHelper {
	
	private LoginDelegate login = new LoginDelegate();
	private ViewDelegate view = new ViewDelegate();
	private EmployeeDelegate employee = new EmployeeDelegate();
	
	public void processGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String path = req.getRequestURI().substring(req.getContextPath().length());//req.getServletPath();
		System.out.println(path);
		if (path.startsWith("/")) {
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
		switch (path) {
		case "/":
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
