package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.delegates.AuthDelegate;
import com.revature.delegates.ReimbDelegate;
import com.revature.delegates.UserDelegate;
import com.revature.delegates.ViewDelegate;

public class RequestHelper {

	/*
	 *  /login -> returns Login.html view
	 *  
	 *  examples:
	 *  /new -> returns NewBird.html view
	 *  /directory -> returns Birdirectory.html
	 *  /api/birds -> returns bird data
	 *  /api/habitats -> returns habitat data
	 *  is there a point to api stuff?
	 */
	
	// All delegates here.
	private ViewDelegate viewDelegate = new ViewDelegate();
	private AuthDelegate authDelegate = new AuthDelegate();
	private UserDelegate userDelegate = new UserDelegate();
	private ReimbDelegate reimbDelegate = new ReimbDelegate();
	
	public void processGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		
		if(path.startsWith("/api/")) {
			// Authenticate token here.
			if(!authDelegate.isAuthorized(request)) {
				System.out.println("Failed authentication.");
				response.sendError(401);
				return;
			}
			
			String record = path.substring(5); // i think this shaves off the /api/ part.
			
			if(record.startsWith("users")) {
				userDelegate.getUsers(request, response);
			} 
			else if(record.startsWith("reimb")) {
				reimbDelegate.getReimbursements(request, response);
			}
			else {
				response.sendError(404, "Request Record(s) Not Found");	
			}
		} else {
			// requesting a view
			viewDelegate.resolveView(request, response);
		}
	}
	
	public void processPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = request.getServletPath();
		System.out.println("POST Path requested:" + path);
		
		if (path.startsWith("/login")) {
			authDelegate.authenticate(request, response);
		}
		else if (path.startsWith("/updateuser/")) {
			userDelegate.updateUser(request, response);
		}
		else {
			response.sendError(405);
		}
	}
	
}
