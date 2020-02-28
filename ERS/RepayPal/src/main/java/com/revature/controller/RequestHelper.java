package com.revature.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	public void processGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String path = request.getRequestURI().substring(request.getContextPath().length());
		if(path.startsWith("/api/")) {
			//evaluate the record in the url and send req/resp to a resource based delegate
			String record = path.substring(5);
			switch(record) {
			case "home-info":
				// process request with user delegate
				break;
			case "profile-info":
				// process request with profile delegate
				break;
			default:
				response.sendError(404, "Record not supported");
			}
			
		} else {
			//requesting a view
			viewDelegate.resolveView(request, response);
		}
	}
}
