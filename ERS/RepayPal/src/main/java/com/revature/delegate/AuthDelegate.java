package com.revature.delegate;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.model.User;
import com.revature.service.UserService;

public class AuthDelegate {
	
	private UserService us = new UserService();
	
	private static Logger log = Logger.getRootLogger();
	
	public void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		log.debug(username + " " + password);
		
		User u = us.validateUser(username, password);
		log.debug(u);
		if(u!=null) {
			String token = u.getUsername()+":"+u.isManager();
			response.setStatus(200);
			response.setHeader("Authorization", token);
		} else {
			response.sendError(401);
		}
	}
	
	public boolean isAuthorized(HttpServletRequest request) {
		String authToken = request.getHeader("Authorization");
		return (authToken != null);
	}

}
