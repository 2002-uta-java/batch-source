package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.delegate.AuthDelegate;
import com.revature.delegate.EmployeeDelegate;
import com.revature.delegate.ReimbursementDelegate;
import com.revature.delegate.ViewDelegate;

public class RequestHelper {
	
	private ViewDelegate vd = new ViewDelegate();
	private AuthDelegate ad = new AuthDelegate();
	private EmployeeDelegate ed = new EmployeeDelegate();
	private ReimbursementDelegate rd = new ReimbursementDelegate();

	public void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		if(path.startsWith("/api/")) {
			if(!ad.isAuthorized(request)) {
				response.sendError(401);
				return;
			}
			String record = path.substring(5);
			if(record.startsWith("employees")) {
				ed.getEmployees(request, response);
			} else if(record.startsWith("reimbursements")) {
				rd.getReimbursements(request, response);
			}
			else {
				response.sendError(404, "Record not supported");
			}
		} else {
			//requesting a view
			vd.resolveView(request, response);
		}
	}
	
	public void processPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = request.getServletPath();
		if(path.startsWith("/login")) {
			ad.authenticate(request, response);			
		} else if(path.startsWith("/api/employees")) {
			ed.postEmployee(request, response);
		} else if(path.startsWith("/api/reimbursements")) {
			rd.postReimbursement(request, response);
		} else {
			response.sendError(405, "POST not supported");			
		}
	}
}
