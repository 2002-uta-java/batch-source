package com.dean.managers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dean.daos.EmployeeDao;
import com.dean.daos.EmployeeDaoImpl;
import com.dean.models.Employee;

public class SessionManager {

	public void createSessionForManagerAccounts(String username, HttpSession session) {
		EmployeeDao ed = new EmployeeDaoImpl();
		Employee employee = ed.getEmployeeByUsername(username);
		
		session.setAttribute("isManager", employee.getIsManager());
		session.setAttribute("id", employee.getId());
		session.setAttribute("name", employee.getName());
		session.setAttribute("position", employee.getPosition());
	}

	public boolean validateUserExistence(HttpServletRequest request) {
		boolean userExists = false;
		HttpSession session = request.getSession(false);

		if (session != null && session.getAttribute("username") != null) {
			userExists = true;
		}

		return userExists;
	}
}
