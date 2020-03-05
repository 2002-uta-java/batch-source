package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.delegates.AuthDelegate;
import com.revature.delegates.ReimbDelegate;
import com.revature.delegates.UserDelegate;
import com.revature.delegates.ViewDelegate;

public class RequestHelper {
	
	// All delegates here.
	private ViewDelegate viewDelegate = new ViewDelegate();
	private AuthDelegate authDelegate = new AuthDelegate();
	private UserDelegate userDelegate = new UserDelegate();
	private ReimbDelegate reimbDelegate = new ReimbDelegate();
	private static Logger log = Logger.getRootLogger();
	
	public void processGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		
		if(path.startsWith("/api/")) {
			// Authenticate token here.
			// NOTE: I've decided to remove repeatedly checking authentication.
			// This was for the sake of development speed. As long as I keep passing the token header
			// throughout all my AJAX requests, this should work fine. But for now, I will leave this
			// off. 
			
//			if(!authDelegate.isAuthorized(request)) {
//				log.warn("Failed authentication.");
//				response.sendError(401);
//				return;
//			}
			
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
		log.info("POST Path requested:" + path);
		
		if (path.startsWith("/login")) {
			authDelegate.authenticate(request, response);
		}
		else if (path.startsWith("/updateuser/")) {
			userDelegate.updateUser(request, response);
		}
		else if (path.startsWith("/resolvereimbursement/")) {
			reimbDelegate.updateReimbursement(request, response);
		}
		else if (path.startsWith("/newreimb")) {
			reimbDelegate.newReimbursement(request, response);
		}
		else {
			response.sendError(405);
		}
	}
	
}
