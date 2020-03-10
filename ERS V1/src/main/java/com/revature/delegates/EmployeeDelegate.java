package com.revature.delegates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.services.EmployeeService;

public class EmployeeDelegate {
	private EmployeeService es = new EmployeeService();

	public void getAllEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Employee> employees = es.getAllEmployees();
		try (PrintWriter pw = response.getWriter();) {
			pw.write(new ObjectMapper().writeValueAsString(employees));
		}
	}

	public void viewEmployeeDetails(HttpServletRequest request, HttpServletResponse response, int employeeId) {
		//String username = request.getParameter("username");
		Employee w = es.getEmployeeDetailsById(employeeId);
		try (PrintWriter pw = response.getWriter();) {
			try {
				pw.write(new ObjectMapper().writeValueAsString(w));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void updateEmployee(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		/**
		 * Update JSON request with HTTP PUT { "username": "mHallam1", "phone":
		 * "354-456-7890", "address": "202 Akarad Street", "city": "Dallas",
		 * "state":"TX", "country": "US", "postalCode": "75254" }
		 */
		String jsonString = IOUtils.toString(request.getInputStream());
		Employee updatedEmployeeInfo = new Gson().fromJson(jsonString, Employee.class);

		boolean isEmplAdded = es.updateEmployee(updatedEmployeeInfo);
		String message = "";
		if (isEmplAdded) {
			message = "Updated successfully";
			response.setStatus(200);
			response.setHeader("New Employee", message);
		} else {
			response.sendError(401);
			message = " Update failed";
			response.setHeader("New Employee", message);
		}

	}

	public void createEmployee(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// NOTE: The attributes names in json must match the attribute names in the
		// Employee object
		// All NOT NULL values and valid cross reference key values ( Foreign keys) must
		// be provided
		/**
		 * New Employee Request JSON { "firstName": "Matt", "LastName": "Hallam",
		 * "title": "Manager", "username": "mHallam1", "password": "abc123", "email":
		 * "mHallam1@revature.com", "roleId": 2 }
		 */
		//String jsonString = IOUtils.toString(request.getInputStream());
		//Employee newEmpl = new Gson().fromJson(jsonString, Employee.class);
		
		Employee newEmpl = new Employee();
		newEmpl.setFirstName(request.getParameter("firstname"));
		newEmpl.setLastName(request.getParameter("lastname"));	
		newEmpl.setTitle(request.getParameter("title"));
		newEmpl.setUsername(request.getParameter("username"));
		newEmpl.setPassword(request.getParameter("password"));
		newEmpl.setEmail(request.getParameter("email"));
		newEmpl.setManagerId(2);//default to the only manager in the department for now			
			

		boolean isEmplAdded = es.addEmployee(newEmpl);
		String message = "";
		if (isEmplAdded) {
			message = "Created successfully";
			response.setStatus(200);
			response.setHeader("New Employee", message);
		} else {
			response.sendError(401);
			message = " Creation failed";
			response.setHeader("New Employee", message);
		}

	}

}
