package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.delegate.AuthDelegate;
import com.revature.delegate.ReimbursementDelegate;
import com.revature.delegate.UserDelegate;
import com.revature.delegate.ViewDelegate;

public class RequestHelper {

	public RequestHelper() {
		super();
	}
	
	/* 
	 *    /login -> returns Login.html view
	 *    /home -> returns Home.html
	 *    /profile -> returns Profile.html
	 *    /api/home-info -> returns home data
	 *    /api/profile-info -> returns profile data
	 */
	
	private ViewDelegate viewDelegate = new ViewDelegate();
	private UserDelegate userDelegate = new UserDelegate();
	private ReimbursementDelegate reimbursementDelegate = new ReimbursementDelegate();
	private AuthDelegate authDelegate = new AuthDelegate();

	public void processGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// determine if this is a record based request
		String path = request.getServletPath();
		if(path.startsWith("/api/")) {
			// we will authenticate the token here
			if(!authDelegate.isAuthorized(request)) {
				response.sendError(401);
				return;
			}
			
			String record = path.substring(5);
			System.out.println(record);
			if(record.startsWith("users")) {
				userDelegate.getUsers(request, response);
			} 
			else if(record.startsWith("reimbursements")) {
				reimbursementDelegate.getReimbursemets(request, response);
			}
			else {
				response.sendError(404, "Request Record(s) Not Found");	
			}
			
		} 
		else {
			viewDelegate.resolveView(request, response);
		}
	}

	public void processPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = request.getServletPath();
		System.out.println(path);
		switch(path) {
		case "/login":
			System.out.println("In log in part");
			authDelegate.authenticate(request, response);
			
			break;
		default:
			response.sendError(405);
		}
	}
}