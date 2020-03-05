package com.dean.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dean.daos.EmployeeDao;
import com.dean.daos.EmployeeDaoImpl;
import com.dean.managers.SessionManager;
import com.dean.models.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AllEmployeesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
    public AllEmployeesServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SessionManager sm = new SessionManager();

		String employeeId = request.getParameter("id");
		String action = request.getParameter("action");

		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();

		EmployeeDao ed = new EmployeeDaoImpl();
		List<Employee> employees = new ArrayList<Employee>();

		if (action != null && employeeId != null) {

			try {
				int employeeIdNumber = Integer.parseInt(employeeId);
				Employee e = ed.getEmployeeById(employeeIdNumber);
			} catch (Exception ex) {
				pw.write("none");
			}
		}
		
		employees = ed.getAllEmployees();
		boolean userExistsInSession = sm.validateUserExistence(request);

		if (userExistsInSession) {
			String employeesStr = om.writeValueAsString(employees);
			employeesStr = "{\"employees\": " + employeesStr + "}";
			pw.write(employeesStr);
		} else {
			response.sendRedirect("login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
}
