package com.revature.delegates;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;
import com.revature.models.Employee;

public class AuthDelegate {

	private EmployeeDao employeeDao = new EmployeeDaoImpl();

	public void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username + " " + password);

		Employee e = employeeDao.getEmployeeByUsernameAndPassword(username, password);

		if (e != null) {
			String token = e.getEmployeeId() + "%" + e.getTitle();
			response.setStatus(200);
			response.setHeader("Authorization", token);
		} else {
			response.sendError(401);
		}
	}

	public boolean isAuthorized(HttpServletRequest request) {
		String authToken = request.getHeader("Authorization");
		// check to see if there is an auth header
		if (authToken != null) {
			String[] tokenArr = authToken.split("%");
			// if the token is formatted the way we expect, we can take the id from it and
			// query for that user
			if (tokenArr.length == 2) {
				String idStr = tokenArr[0];
				String employeeRoleStr = tokenArr[1];
				if (idStr.matches("^\\d+$")) {
					// check to see if there is a valid user and if that user is the appropriate
					// role in their token
					Employee e = employeeDao.getEmployeeDetailsById(Integer.parseInt(idStr));
					if (e != null && e.getEmployeeId().equals(employeeRoleStr)) {
						return true;
					}
				}
			}
		}
		return false;

	}

}