package com.hylicmerit.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hylicmerit.models.Employee;
import com.hylicmerit.service.EmployeeService;

public class EmployeeDelegate {
	private EmployeeService es = new EmployeeService();
	
	public void getAllEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException{
		List<Employee> employees = es.getAllEmployees();
		try(PrintWriter pw = response.getWriter();){
			pw.write(new ObjectMapper().writeValueAsString(employees));
		}
	}
	
	public void getEmployeeById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("email");
		Employee w = es.getEmployeeById(email);
		try(PrintWriter pw = response.getWriter();){
			pw.write(new ObjectMapper().writeValueAsString(w));
		}
	}
	
	public void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//get email from request parameter
		String email = request.getParameter("email");
		//get birthday from request parameter
		String birthday = request.getParameter("birthday");
		//get bio from request parameter
		String bio = request.getParameter("bio");
		//get employee based on given email
		Employee e = es.getEmployeeById(email);
		//set new bio
		e.setBio(bio);
		//set new birthday
		e.setBirthday(birthday);
		//update employee
		if(es.updateEmployee(e)) {
			response.setStatus(200);
			response.setHeader("Data", new ObjectMapper().writeValueAsString(e));
		} else {
			response.sendError(500, "We were unable to update this employee's profile at this time.");
		}
	}
	
}
