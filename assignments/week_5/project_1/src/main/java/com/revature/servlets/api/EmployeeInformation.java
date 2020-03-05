package com.revature.servlets.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.LoggerClass;
import com.revature.models.Employee;
import com.revature.models.Profile;
import com.revature.services.EmployeeService;

/**
 * Servlet implementation class EmployeeInformation
 */
public class EmployeeInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static LoggerClass lc = new LoggerClass();
	
	private EmployeeService es = new EmployeeService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeInformation() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String employeeIdString = request.getParameter("empId");
		
		List<Employee> empList = new ArrayList<>();
		
		if (employeeIdString == null) {
			
			empList = es.getAllEmployees();
						
		} else if (employeeIdString.matches("^\\d+$")) {
									
			int employeeId = Integer.parseInt(employeeIdString);
			
			Employee e = new Employee();
			e.setId(employeeId);
			
			empList.add(es.getEmployee(e));
			
		} else {			
			response.sendError(400, "Mistakes were made...");
			lc.postErrorLog("invalid get employee info request");
		}
		
		ObjectMapper om = new ObjectMapper();
		String employeeJson = om.writeValueAsString(empList);
		
		try (PrintWriter pw = response.getWriter();) {
			pw.write(employeeJson);
			response.setStatus(200);
			lc.postInfoLog("success. employee info retrieved");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		String empIdStr = request.getParameter("employee");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		int empIdInt = Integer.parseInt(empIdStr);
				
		Employee em = new Employee();
		em.setId(empIdInt);
		em.setPhone(phone);
		em.setEmail(email);
		
		System.out.println(em);
		
		int didItRun = es.updateEmployee(em);
		
		if(didItRun == 1) {
			response.setStatus(200);
			lc.postInfoLog("success. elpoyee updated");
		} else {
			response.sendError(400, "Mistakes were made...");
			lc.postErrorLog("error in updating employee");
		}	
	}
	
	
	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		String empIdStr = request.getParameter("employee");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		int empIdInt = Integer.parseInt(empIdStr);
				
		Employee em = new Employee();
		em.setId(empIdInt);
		em.setPhone(phone);
		em.setEmail(email);
		
		int didItRun = es.updateEmployee(em);
		
		if(didItRun == 1) {
			response.setStatus(200);
			lc.postInfoLog("success. employee updated");
		} else {
			response.sendError(400, "Mistakes were made...");
			lc.postErrorLog("error in updating employee");
		}	
	}
}
