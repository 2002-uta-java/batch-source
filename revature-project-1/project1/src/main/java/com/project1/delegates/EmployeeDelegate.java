package com.project1.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.models.Employee;
import com.project1.services.EmployeeService;

public class EmployeeDelegate {
	private EmployeeService empService = new EmployeeService();
	private static final Logger LOGGER = LogManager.getLogger(EmployeeDelegate.class.getName());
	
	public void getAllEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Employee> emps = empService.getAllEmployee("Employee");
		LOGGER.info("Get all Employees!");
		try(PrintWriter pw = response.getWriter()){
			pw.write(new ObjectMapper().writeValueAsString(emps));
		}
	}
	
	public void getEmployeeById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String empIdParam = request.getParameter("id");
		Employee emp = new Employee();
		emp = empService.getEmployeeById(Integer.parseInt(empIdParam));
		LOGGER.info("Get all employee by employee id!");
		try(PrintWriter pw = response.getWriter()){
			pw.write(new ObjectMapper().writeValueAsString(emp));
		}
	}
	
	public void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String empIdParam = request.getParameter("id");
		String empNameParam = request.getParameter("emp-name");
		String empAgeParam = request.getParameter("emp-age");
		String empEmailParam = request.getParameter("emp-email");
		
		Employee emp = new Employee();
		emp = empService.getEmployeeById(Integer.parseInt(empIdParam));
		LOGGER.info("Employee without update info yet: "+emp);
		emp.setName(empNameParam);
		emp.setAge(Integer.parseInt(empAgeParam));
		emp.setEmail(empEmailParam);

		empService.updateEmployee(emp);
		LOGGER.info("Employee with updated info: "+emp);
	}
	
}
