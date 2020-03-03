package com.revature.delegate;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.User;
import com.revature.service.UserService;

public class AuthDelegate {
	
	private UserService us = new UserService();
	
	public void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username + " " + password);
		
		User u = us.validateUser(username, password);
		System.out.println(u);
		if(u!=null) {
			String token = u.getUsername()+":"+u.isManager();
			response.setStatus(200);
			response.setHeader("Authorization", token);
		} else {
			response.sendError(401);
		}
	}
	
	public boolean isAuthorized(HttpServletRequest request) {
		return true;
//		String authToken = request.getHeader("Authorization");
//		// check to see if there is an auth header
//		if(authToken!=null) {
//			String[] tokenArr = authToken.split(":");
//			// if the token is formatted the way we expect, we can take the id from it and query for that user
//			if(tokenArr.length == 2) {
//				String username = tokenArr[0];
//				String isManager = tokenArr[1];
//				if(username.matches("^\\d+$")) {
//					// check to see if there is a valid user and if that user is the appropriate role in their token
//					User u = us.getUserByUsername(username);
//					if(u!=null && (u.isManager()+"").equals(isManager)) {
//						return true;
//					}
//				}
//			}
//		}
//		return false;
	}

}
