package com.revature.delegate;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.service.EmployeeService;

public class EmployeeDelegate {
	private ObjectMapper om = new ObjectMapper();
	private EmployeeService employeeService = new EmployeeService();
	private String firstName;
	private String lastName;
	private int managerId;
	private String title;
	private int reportsToId;
	private String email;
	private int id;
	
	public EmployeeDelegate() {
		super();
	}

	public void getAllEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Employee> employees = employeeService.getEmployee();
		
		try(PrintWriter pw = response.getWriter()){
			pw.write(om.writeValueAsString(employees));
		}
	}
	
	public void getEmployeeId(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String emailParam = request.getParameter("email");
		int id = employeeService.getEmployeeId(emailParam);
		
		try(PrintWriter pw = response.getWriter()) {
			pw.write(om.writeValueAsString(id));
		}		
	}
	
	public void getFirstName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try (BufferedReader requestReader = request.getReader();) {
			String newEmpIdJson = requestReader.readLine();	
			String b = om.readValue(newEmpIdJson, String.class);
			int id = Integer.parseInt(b);
			String firstName = employeeService.getFirstName(id);
			this.firstName = firstName;
		}
		
		try(PrintWriter pw = response.getWriter()){
			pw.write(om.writeValueAsString(firstName));
		}
		
	}
	
	public void getLastName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try (BufferedReader requestReader = request.getReader();) {
			String newEmpIdJson = requestReader.readLine();	
			String b = om.readValue(newEmpIdJson, String.class);
			int id = Integer.parseInt(b);
			String lastName = employeeService.getLastname(id);
			this.lastName = lastName;
			}
	
	try(PrintWriter pw = response.getWriter()){
		pw.write(om.writeValueAsString(lastName));
		}
	}
	
	public void getManagerId(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try (BufferedReader requestReader = request.getReader();) {
			String newEmpIdJson = requestReader.readLine();
			String b = om.readValue(newEmpIdJson, String.class);
			int id = Integer.parseInt(b);
			int managerId = employeeService.getManagerId(id);
			this.managerId = managerId;
			}
		try(PrintWriter pw = response.getWriter()) {
			pw.write(om.writeValueAsString(managerId));
		}
	}
	
	public void getTitle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try (BufferedReader requestReader = request.getReader();) {
			String newEmpIdJson = requestReader.readLine();	
			String b = om.readValue(newEmpIdJson, String.class);
			int id = Integer.parseInt(b);
			String title = employeeService.getTitle(id);
			this.title = title;
			}
	
		try(PrintWriter pw = response.getWriter()){
			pw.write(om.writeValueAsString(title));
		}
	}
	
	public void getReportsTo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try (BufferedReader requestReader = request.getReader();) {
			String newEmpIdJson = requestReader.readLine();
			String b = om.readValue(newEmpIdJson, String.class);
			int id = Integer.parseInt(b);
			int reportsToId = employeeService.getReportsTo(id);
			this.reportsToId = reportsToId;
			}
		try(PrintWriter pw = response.getWriter()) {
			pw.write(om.writeValueAsString(reportsToId));
		}
	}
	
	public void getEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try (BufferedReader requestReader = request.getReader();) {
			String newEmpIdJson = requestReader.readLine();	
			String b = om.readValue(newEmpIdJson, String.class);
			String email = employeeService.getEmail(b);
			this.email = email;
			}
	
		try(PrintWriter pw = response.getWriter()){
		pw.write(om.writeValueAsString(email));
		}
	}
	
	public void createEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try (BufferedReader requestReader = request.getReader();) {
			String newEmpIdJson = requestReader.readLine();	
			JSONArray jsonArr = new JSONArray(newEmpIdJson);
		    for (int i = 0; i < jsonArr.length(); i++) {
		        JSONObject jsonObj = jsonArr.getJSONObject(i);
		        Employee newEmployee = new Employee(firstName, lastName, email, title, managerId, reportsToId);
		        firstName = newEmployee.setFirstName(jsonObj.getString("firstName"));
		        lastName = newEmployee.setLastName(jsonObj.getString("lastName"));
		        email = newEmployee.setEmail(jsonObj.getString("email"));
		        title = newEmployee.setTitle(jsonObj.getString("title"));
		        managerId = newEmployee.setManagerId(jsonObj.getInt("managerId"));
		        reportsToId = newEmployee.setReportsToId(jsonObj.getInt("reportsTo"));
		        employeeService.createEmployeeByFunction(newEmployee);
		    }		
			int id = employeeService.getEmployeeId(email);
			if(lastName.equals(employeeService.getLastname(id))) {
				try(PrintWriter pw = response.getWriter()) {
				pw.write(om.writeValueAsString("Success" + "new employee # " + id)); }
			}
			 else {
				try(PrintWriter pw = response.getWriter()) {
					pw.write(om.writeValueAsString("Failed to add")); }
			}
		}
		
	}

	public void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try (BufferedReader requestReader = request.getReader();) {
			String newEmpIdJson = requestReader.readLine();	
			JSONArray jsonArr = new JSONArray(newEmpIdJson);
		    for (int i = 0; i < jsonArr.length(); i++) {
		        JSONObject jsonObj = jsonArr.getJSONObject(i);
		        Employee newEmployee = new Employee(managerId, reportsToId);
		        managerId = newEmployee.setManagerId(jsonObj.getInt("managerId"));
		        reportsToId = newEmployee.setReportsToId(jsonObj.getInt("reportsTo"));
		        employeeService.updateEmployeeManagerByFunction(newEmployee);
		    	}		

				try(PrintWriter pw = response.getWriter()) {
				pw.write(om.writeValueAsString("Update complete"));
			
				}
		
			}
	}
	
	public void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try (BufferedReader requestReader = request.getReader();) {
			String newEmpIdJson = requestReader.readLine();	
			String b = om.readValue(newEmpIdJson, String.class);
			int id = Integer.parseInt(b);
			this.id = id;
			employeeService.deleteEmployee(id);
			}
	
		if(id != 0) {
			try(PrintWriter pw = response.getWriter()) {
			pw.write(om.writeValueAsString("Failed to remove employee")); }
		}
		 else {
			try(PrintWriter pw = response.getWriter()) {
				pw.write(om.writeValueAsString("Employee Deleted")); }
		}
	}

}