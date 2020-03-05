package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.services.EmployeeService;

public class EmployeeDelegate {

	private EmployeeService emp = new EmployeeService();

	/**
	 * sending getrequest from our login page to get our employees
	 */
	public void getEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException {
//here we are getting the name parameter from the form
		String empName = request.getParameter("name");
		
		//if our get parameter is null then we are writing
//		out all the employees
		if (empName == null) {
			List<Employee> employees = emp.getEmployees();

			try (PrintWriter pw = response.getWriter()) {
				pw.write(new ObjectMapper().writeValueAsString(employees));
			}
			//we get the employee and check if we can send a successful code
		} else {
			Employee e = emp.getEmpByName(empName);

			if (e == null) {
				response.sendError(404);
			} else {
				try (PrintWriter pw = response.getWriter()) {
					pw.write(new ObjectMapper().writeValueAsString(e));
				}
			}
		}
	}
public void updateEmployee(HttpServletRequest request) throws IOException {
		
		String name = request.getParameter("name");
		String emp_id = request.getParameter("emp_id");
		
		emp.updateEmployee(name, Integer.parseInt(emp_id));
	}

}
