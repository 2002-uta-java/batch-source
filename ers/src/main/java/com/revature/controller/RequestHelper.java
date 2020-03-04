package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.delegates.EmployeeDelegate;
import com.revature.delegates.LoginDelegate;
import com.revature.delegates.ReimbursementDelegate;
import com.revature.delegates.ViewDelegate;

public class RequestHelper {
	
	private LoginDelegate login = new LoginDelegate();
	private ViewDelegate view = new ViewDelegate();
	private EmployeeDelegate employee = new EmployeeDelegate();
	private ReimbursementDelegate reimbursement = new ReimbursementDelegate();
	
	public void processGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String path = req.getRequestURI().substring(req.getContextPath().length());//req.getServletPath();
		if (path.startsWith("/api/")) {
			if (!login.isAuthorized(req, res)) {
				res.sendError(401);
				return;
			}
			
			String record = path.substring(5);
			if (record.startsWith("reimbursements")) {
				reimbursement.getEmployee(req, res);
			}else if (record.startsWith("employees")) {
				employee.getEmployee(req, res);
			}else
				res.sendError(404, "Record(s) Not Found");
		} else
			view.resolveView(req, res);
	}
	
	public void processPost(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String path = req.getServletPath();
		System.out.println(path);
		switch (path) {
		case "/login":
			login.authenticate(req, res);
			break;
		case "/addReimbursement":
			reimbursement.addReimbursement(req, res);
			break;
		case "/processRequest":
			reimbursement.processReimbursement(req, res);
			break;
		case "/denyRequest":
			reimbursement.denyReimbursement(req, res);
			break;
		case "/updateEmployee":
			employee.updateEmployee(req, res);
			break;
		default:
			res.sendError(405);
		}
	}
}
