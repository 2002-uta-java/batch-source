package com.project1.delegates;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.project1.daos.EmployeeDao;
import com.project1.daos.EmployeeDaoImpl;
import com.project1.models.Employee;

public class AuthDelegate {
	
	private static final Logger LOGGER = LogManager.getLogger(AuthDelegate.class.getName());
	private EmployeeDao empDao = new EmployeeDaoImpl();
	
	public void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String identifier = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(identifier + " " + password);
		
		Employee emp = empDao.credential(identifier, password);
		System.out.println("my employee hoang1: "+emp);
		if(emp.getEmpId() != 0) {
			String token = emp.getEmpId()+":"+emp.getPosition();
	        LOGGER.info("Logging in with credential token: " + token);
			response.setStatus(200);
			response.setHeader("Authorization", token);
		} else {
			LOGGER.info("Logging in credential is invalid");
			response.sendError(401);
		}
	}
	
}
