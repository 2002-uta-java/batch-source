package com.revature.project1.controller;

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
		String path = request.getServletPath();
		
		switch(path) {
		case "/employee":
			viewDelegate.getEmployeeView(request, response);
			break;
		default:
			response.sendError(404);
		}
		
		//viewDelegate.resolveView(request, response);
	}
	
	
//	private void login(HttpServletRequest request, HttpServletResponse response) {
//		// TODO Auto-generated method stub
//		System.out.println("attempted to login");
//	}


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
