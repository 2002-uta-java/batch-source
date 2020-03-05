package com.revature.delegate;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.models.Employee;
import com.revature.services.EmployeeService;

public class AuthDelegate {

	private EmployeeService es = new EmployeeService();
	
	public void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Employee e = es.login(username, password);
		if(e != null) {
			String token = e.getEid() + ":"+ e.getTitle();
			response.setStatus(200);
			response.setHeader("Authorization", token);
		} else {
			response.sendError(401);
		}
	}

	public boolean isAuthorized(HttpServletRequest request) {
		String authToken = request.getHeader("Authorization");
		if(authToken != null) {
			String[] tokenArr = authToken.split(":");
			if(tokenArr.length == 2) {
				String idStr = tokenArr[0];
				String titleStr = tokenArr[1];
				if(idStr.matches("^\\d+$")) {
					Employee e = es.myInfo(Integer.parseInt(idStr));
					if(e != null && e.getTitle().equals(titleStr)) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
