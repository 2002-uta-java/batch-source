package com.revature.delegate;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.User;
import com.revature.service.UserService;

public class UserDelegate {
	
	private UserService us = new UserService();

	public void getUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String requestPath = request.getServletPath();
		System.out.println(requestPath);
		if (requestPath.length() == "/api/users".length()) {
			List<User> users = us.getUsers();
			System.out.println(users);
			try (PrintWriter pw = response.getWriter();) {
				pw.write(new ObjectMapper().writeValueAsString(users));
			}
		} else {
			String username = request.getServletPath().substring(11);
			System.out.println(username);
			if (username.matches("\\w+")) {
				User u = us.getUserByUsername(username);
				if (u == null) {
					response.sendError(404, "No user with given Username");
				} else {
					try (PrintWriter pw = response.getWriter()) {
						pw.write(new ObjectMapper().writeValueAsString(u));
					}
				}
			} else {
				response.sendError(400, "Invalid ID param");
			}
		}
	}

	public void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		System.out.println(username + " " + " " + firstName + " " + lastName + " " + password);
		
		User u = us.getUserByUsername(username);
		u.setFirstName(firstName);
		u.setLastName(lastName);
		u.setPassword(password);
		System.out.println(u);
		if(us.updateUser(u)) {
			response.setStatus(200);
		} else {
			response.sendError(401);
		}
	}

	
}
