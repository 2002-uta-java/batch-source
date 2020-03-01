package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.ReimbursementDao;
import com.revature.daos.ReimbursementDaoImpl;

public class ReimbDelegate {
	
	private ReimbursementDao rDao = new ReimbursementDaoImpl();
	
	public void getReimbursements(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
}
