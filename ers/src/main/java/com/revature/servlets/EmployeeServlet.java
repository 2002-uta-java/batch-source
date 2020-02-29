package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.model.Employee;

public class EmployeeServlet {
	
	private EmployeeDAO edao = new EmployeeDaoImpl();
	
	public void getEmployee(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String requestPath = req.getServletPath();
		if (requestPath.length() =="/api/employees".length()) {
			List<Employee> ems = edao.getAllEmployees();
			try (PrintWriter pw = res.getWriter()) {
				pw.write(new ObjectMapper().writeValueAsString(ems));
			}
		} else {
			String idStr = req.getServletPath().substring(11);
			if (idStr.matches("^\\d+$")) {
				Employee em = edao.getEmployee(idStr);
				if (em == null)
					res.sendError(404, "No Employee with given id");
				else {
					try (PrintWriter pw = res.getWriter()) {
						pw.write(new ObjectMapper().writeValueAsString(em));
					}
				}
			} else
				res.sendError(400, "Invalid ID");
		}
	}
}
