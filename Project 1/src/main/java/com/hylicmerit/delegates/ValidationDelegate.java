package com.hylicmerit.delegates;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hylicmerit.models.Employee;
import com.hylicmerit.service.EmployeeService;

public class ValidationDelegate {
	private EmployeeService es = new EmployeeService();
	public void validateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//check if username and password were provided
		if(request.getParameterMap().containsKey("user") && request.getParameterMap().containsKey("pass")) {
			//get username
			String user = request.getParameter("user");
			//get password
			String pass = request.getParameter("pass");
			//get user based on given username
			Employee e = (Employee) es.getEmployeeById(user);
			if(e != null) {
				if(e.getPassword().equals(pass)) {
					//successful validation
					response.setStatus(200);
					response.setHeader("Authorization", new ObjectMapper().writeValueAsString(e));
				} else {
					//wrong password
					response.sendError(400, "Your password is not correct.");
				}
			} else {
				//user does not exist
				response.sendError(400, "The credentials you provided are not valid.");
			}
			
		} else {
			response.sendError(400, "Please fill in all required fields.");
		}
	}
}
