package com.revature.project1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.project1.delegates.AuthDelegate;
import com.revature.project1.delegates.EmployeeDelegate;
import com.revature.project1.delegates.ManagerDelegate;
import com.revature.project1.delegates.ViewDelegate;

public class RequestHelper {

	private AuthDelegate authDelegate = new AuthDelegate();
	private ViewDelegate viewDelegate = new ViewDelegate();
	private EmployeeDelegate employeeDelegate = new EmployeeDelegate();
	private ManagerDelegate managerDelegate = new ManagerDelegate();

	public void processGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// determine if this is a record based request
		String path = request.getServletPath();
		System.out.println(path + "processget, reqhelper");
		
		if (path.startsWith("/home")) {
			switch (path) {
			case "/employee":
				viewDelegate.getEmployeeView(request, response);
				break;
			case "/manager":
				viewDelegate.getManagerView(request, response);
			default:
				response.sendError(404);
			}
		}

		// viewDelegate.resolveView(request, response);
	}

//	private void login(HttpServletRequest request, HttpServletResponse response) {
//		// TODO Auto-generated method stub
//		System.out.println("attempted to login");
//	}

	public void processPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String path = request.getServletPath();
		System.out.println(path + "processpost, reqhelper");
		
		switch (path) {
		case "/login":
			authDelegate.authenticate(request, response);
			break;
		case "/update":
			System.out.println("updating user");
			employeeDelegate.updateProfile(request, response);
			break;
		default:
			response.sendError(405);
		}
	}

}
