package com.revature.delegates;

import java.io.BufferedReader;
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
		
		Employee eUpdate = readUserJson(request);
		
		Employee e = eDao.getEmployeeById(Integer.parseInt(idStr));
		
		if (e == null) {
			response.sendError(404, "No user with given ID");
		} else {
			// Update employee with proper validation.
			
			// Values that will not get updated if...
			// id = 0 (should've been null)
			// *email = ""
			// position = null
			// *firstName = ""
			// *lastName = ""
			// *gender = (must always have value)
			// password = null
			// Note: * means can be changed.
			
			String email = eUpdate.getEmail();
			String firstName = eUpdate.getFirstName();
			String lastName = eUpdate.getLastName();
			String gender = eUpdate.getGender();
			
			if (!email.isEmpty()) {
				e.setEmail(email);
			}
			if (!firstName.isEmpty()) {
				e.setFirstName(firstName);
			}
			if (!lastName.isEmpty()) {
				e.setLastName(lastName);
			}
			if (gender != e.getGender()) {
				e.setGender(gender);
			}
			
			// Send updated user information to database. (probably need try200/catch500s if Dao goes wrong)
			eDao.updateEmployee(e);
			response.setStatus(200);
			System.out.println("User successfully updated!");
		}
	}
	
	public Employee readUserJson(HttpServletRequest request) throws IOException {
		// Read the request payload into a String
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
		    buffer.append(line);
		}
		String data = buffer.toString();
		
		System.out.println(data);
		
		// If the String is not empty, parses the payload into a map
		Employee e = null;
		if (!data.isEmpty()) {
		    ObjectMapper mapper = new ObjectMapper();
		    e = mapper.readValue(data, Employee.class);
		}
		
		// Debugging.
//		System.out.println(e.getId());
//		System.out.println(e.getEmail());
//		System.out.println(e.getPosition());
//		System.out.println(e.getFirstName());
//		System.out.println(e.getLastName());
//		System.out.println(e.getGender());
//		System.out.println(e.getPassword());
		
		return e;
	}
	
}
