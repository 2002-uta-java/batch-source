package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;
import com.revature.models.Employee;

public class UserDelegate {

	private EmployeeDao eDao = new EmployeeDaoImpl();

	public void getUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String requestPath = request.getServletPath();
		
		if (requestPath.length() == "/api/users".length()) {
			System.out.println("Getting all users...");
			List<Employee> employees = eDao.getEmployees();
			
			try (PrintWriter pw = response.getWriter();) { // what the hell is printwriter and objectmapper?
				pw.write(new ObjectMapper().writeValueAsString(employees)); // returns list of employees
			}
			
		} else {
			String idStr = request.getServletPath().substring(11); // shaves off /api/users/ i think.
			System.out.println("Getting ID: " + idStr);
			
			Employee e = eDao.getEmployeeById(Integer.parseInt(idStr));
			
			if (e == null) {
				response.sendError(404, "No user with given ID");
			} else {
				try (PrintWriter pw = response.getWriter()) {
					pw.write(new ObjectMapper().writeValueAsString(e)); // returns specific employee
				}
			}
		}
		
	}

	public void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String requestPath = request.getServletPath();
		String idStr = requestPath.substring(12); // shaves off /updateuser/
		System.out.println("Posting to ID: " + idStr);
		
		Employee e = eDao.getEmployeeById(Integer.parseInt(idStr));
		
		if (e == null) {
			response.sendError(404, "No user with given ID");
		} else {
			// Unpack send data.
			
			
			// Update employee with proper validation.
			
			try { // send to database.
			}
			finally {
				System.out.println("placeholder");
			}
		}
	}
	
}
