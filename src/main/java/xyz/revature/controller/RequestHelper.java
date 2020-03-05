package xyz.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.revature.delegates.AuthDelegate;
import xyz.revature.delegates.EmployeeDelegate;
import xyz.revature.delegates.ReimbursementDelegate;
import xyz.revature.delegates.ViewDelegate;

public class RequestHelper {
	private AuthDelegate authDelegate = new AuthDelegate();
	private ViewDelegate viewDelegate = new ViewDelegate();
	private EmployeeDelegate empDelegate = new EmployeeDelegate();
	private ReimbursementDelegate reDelegate = new ReimbursementDelegate();
	
	public void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		System.out.println("GET path: " + path);
		viewDelegate.resolveView(request, response);
	}
	
	
	public void processPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = request.getServletPath();
		System.out.println("POST path: "+path);
		switch(path) {
		case "/approval":
			reDelegate.update(request, response);
			break;
		case "/status":
			reDelegate.status(request, response);
			break;
		case "/directory":
			empDelegate.directory(request, response);
			break;
		case "/expense":
			reDelegate.expenses(request, response);
			break;
		case "/register":
			authDelegate.register(request, response);
			break;
		case "/reimbursement":
			reDelegate.addExpense(request, response);
			break;
		case "/signin":
			authDelegate.authenticate(request, response);
			break;
		default:
			response.sendError(405);
		}
	}
}
