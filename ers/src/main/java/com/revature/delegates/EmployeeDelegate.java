package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.model.Employee;

public class EmployeeDelegate {

	private EmployeeDAO edao = new EmployeeDaoImpl();

	public void getEmployee(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String requestPath = req.getServletPath();
		if (requestPath.length() == "/api/employees".length()) {
			List<Employee> ems = edao.getAllEmployees();
			try (PrintWriter pw = res.getWriter()) {
				pw.write(new ObjectMapper().writeValueAsString(ems));
			}
		} else {
			String idStr = req.getServletPath().substring(15);
			if (idStr.matches("^\\d+$")) {
				Employee em = edao.getEmployee(Integer.parseInt(idStr));
				if (em == null)
					res.sendError(404, "No Employee with given id");
				else {
					try (PrintWriter pw = res.getWriter()) {
						pw.write(new ObjectMapper().writeValueAsString(em));
					}
				}
			} else if (idStr.contains("@")) {
				Employee em = edao.getEmployeeByUsername(idStr);
				if (em == null)
					res.sendError(404, "No Employee with given email");
				else
					try (PrintWriter pw = res.getWriter()) {
						pw.write(new ObjectMapper().writeValueAsString(em));
					}
			} else
				res.sendError(400, "Invalid ID");
		}
	}

	public void updateEmployee(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		String id = req.getParameter("id");
		Employee em = edao.getEmployee(Integer.parseInt(id));
		String firstname = req.getParameter("firstname");
		String lastname  = req.getParameter("lastname");
		String email     = req.getParameter("email");
		String phone     = req.getParameter("phone");
		String password  = req.getParameter("password");
		String isManager = req.getParameter("isManger");
		
		//System.out.println("Email " + email == null + " Phone " + phone + " Password " + password);
		
		Employee tempem = new Employee();
		
		tempem.setId(em.getId());
		if (firstname != "")
			tempem.setfName(firstname);
		else
			tempem.setfName(em.getfName());
		if (lastname != "")
			tempem.setlName(lastname);
		else
			tempem.setlName(em.getlName());
		if (email != "")
			tempem.setEmail(email);
		else
			tempem.setEmail(em.getEmail());
		if (phone != "")
			tempem.setPhone(phone);
		else
			tempem.setPhone(em.getPhone());
		if (password != "")
			tempem.setPass(password);
		else
			tempem.setPass(em.getPass());
		if (isManager != null)
			tempem.setIsManager(Boolean.getBoolean(isManager));
		else
			tempem.setIsManager(em.getIsManager());
		
		edao.updateEmployee(tempem);
	}
}
