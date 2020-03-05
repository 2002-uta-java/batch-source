package com.dean.managers;

import javax.servlet.http.HttpServletRequest;

import com.dean.daos.EmployeeDaoImpl;
import com.dean.models.Employee;

public class LoginAccountManager {

	public String determineAccountType(HttpServletRequest request) {

		Employee e = new EmployeeDaoImpl().getEmployeeByUsername(request.getParameter("username"));

		if (e.getId() == 0) {
			return "login";
		}

		String password = request.getParameter("password");

		if (password.equals(e.getPassword())) {
			if (e.getIsManager() == 0) {
				return "employeehome";
			} else if (e.getIsManager() == 1) {
				return "managerhome";
			}

		}

		return "login";

	}
}
