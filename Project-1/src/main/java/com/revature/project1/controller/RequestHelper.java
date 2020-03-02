package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.project1.delegates.AuthDelegate;
import com.revature.project1.delegates.ViewDelegate;

public class RequestHelper {
	
	private AuthDelegate authDelegate = new AuthDelegate();
	private ViewDelegate viewDelegate = new ViewDelegate();
	
	public void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// determine if this is a record based request
		
		viewDelegate.resolveView(request, response);
	}
	
	
	public void processPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = request.getServletPath();
		switch(path) {
		case "/login":
			authDelegate.authenticate(request, response);
			break;
		default:
			response.sendError(405);
		}
	}

}
