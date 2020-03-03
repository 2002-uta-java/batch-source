package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.delegates.AccountDelegate;
import com.revature.delegates.AuthDelegate;
import com.revature.delegates.RequestDelegate;
import com.revature.delegates.ViewDelegate;

public class RequestHelper {
	
	private final ViewDelegate viewDelegate = new ViewDelegate();
	private final AccountDelegate accountDelegate = new AccountDelegate();
	private final RequestDelegate requestDelegate = new RequestDelegate();
	private final AuthDelegate authDelegate = new AuthDelegate();
	
	public void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		
		if (path.startsWith("/api/")) {
			handleApiRequest(request, response);
		} else {
			viewDelegate.resolveView(request, response);
		}
		
	}
	
	public void processPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String path = request.getServletPath();
		
		if(path.startsWith("/login")) {
			authDelegate.authenticate(request, response);
		} else if(path.startsWith("/api/")) {
			handleApiRequest(request, response);
		}
	}
	
	public void processPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String path = request.getServletPath();
		
		if(path.startsWith("/api/")) {
			handleApiRequest(request, response);
		}
	}
	
	private void handleApiRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String resource = request.getServletPath().substring(5);
		String method = request.getMethod();
		
		if(!authDelegate.isAuthorized(request)) {
			response.sendError(401);
			return;
		}
		
		if(resource.matches("^users(/?|/?.*)$") || resource.matches("^user(/?|/?.*)$")) {
			switch(method) {
			case "GET":
				accountDelegate.getUsers(request, response);
				break;
			case "POST":
				break;
			default:
				response.sendError(401);
				break;
			}
		} else if (resource.matches("^requests(/?|/?.*)$")) {
			switch(method) {
			case "GET":
				requestDelegate.getRequests(request, response);
				break;
			case "POST":
				requestDelegate.postRequests(request, response);
				break;
			case "PUT":
				requestDelegate.putRequests(request, response);
				break;
			default:
				response.sendError(401);
				break;
			}
		} else {
			response.sendError(404, "No Resource Name Given");
		}
	}

}