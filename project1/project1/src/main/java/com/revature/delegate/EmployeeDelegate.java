package com.revature.delegate;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.services.EmployeeService;

public class EmployeeDelegate {

	private Logger log = Logger.getRootLogger();
	private EmployeeService es = new EmployeeService();
	
	public void getEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = request.getServletPath();
		if("/api/employees".equals(path)) {
			List<Employee> employees = es.getAllEmployees();
			ObjectMapper om = new ObjectMapper();
			String employeeJson = om.writeValueAsString(employees);
			try(PrintWriter pw = response.getWriter();){
				pw.write(employeeJson);
			}
			response.setStatus(200);
		} else if(path.substring(15).startsWith("id")) {
			String idStr = path.substring(18);
			if (idStr.matches("^\\d+$")) {
				Employee e = es.myInfo(Integer.parseInt(idStr));
				if (e == null) {
					log.error("No user with given ID\n");
					response.sendError(404, "No user with given ID");
				} else {
					try (PrintWriter pw = response.getWriter()) {
						pw.write(new ObjectMapper().writeValueAsString(e));
					}
					response.setStatus(200);
				}
			} else {
				log.error("Invalid ID param\n");
				response.sendError(400, "Invalid ID param");
			}
		}
	}
	
	public void postEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String eidStr = request.getParameter("eidStr");
		if(eidStr.matches("^\\d+$")) {
			int eid = Integer.parseInt(eidStr);
			String login = request.getParameter("username");
			String password = request.getParameter("password");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			es.updateInfo(eid, login, password, firstName, lastName, email);	
			response.setStatus(200);
		} else {
			log.error("Invalid ID param\n");
			response.sendError(400, "Invalid ID param");
		}
	}
}
