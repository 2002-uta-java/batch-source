package xyz.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import xyz.revature.models.Employee;
import xyz.revature.services.EmployeeService;

public class EmployeeDelegate {
	private EmployeeService empServ= new EmployeeService();
	
	public void directory(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Employee> employees = empServ.getAllEmployees();
		System.out.println(employees);
		try (PrintWriter pw = response.getWriter()) {
			System.out.println("printwriting...");
			pw.write(new ObjectMapper().writeValueAsString(employees));
		}
	}
}
