package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.delegates.AuthDelegate;
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
	
	public void processGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		if(path.startsWith("/api/")) {
			// Authenticate token here.
			if(!authDelegate.isAuthorized(request)) {
				response.sendError(401);
				return;
			}
			
			String record = path.substring(5); // what does this do?
			switch(record) {
			case "birds":
				// process request with bird delegate
				break;
			default:
				response.sendError(404, "Record not supported");
			}
		} else {
			// requesting a view
			viewDelegate.resolveView(request, response);
		}
	}
	
	public void processPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = request.getServletPath();
		switch(path) {
		case "/login" :
			authDelegate.authenticate(request, response);
			break;
		default:
			response.sendError(405);
		}
	}
	
}
