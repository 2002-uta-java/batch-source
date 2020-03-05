package com.dean.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dean.daos.EmployeeDao;
import com.dean.daos.EmployeeDaoImpl;

public class EditProfileServlet extends HttpServlet {

private static final long serialVersionUID = 1L;
	
    public EditProfileServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			request.getRequestDispatcher("Views/editProfile.html").forward(request, response);
		} else {
			response.sendRedirect("login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		int id = (Integer) session.getAttribute("id");
		
		System.out.println(request);
		System.out.println(response);
		int empId = id;
		System.out.println(empId);
		String empName = request.getParameter("empName");
		String empUsername = request.getParameter("empUsername");
		String empPosition = request.getParameter("empPosition");
		
		EmployeeDao ed = new EmployeeDaoImpl();
		int updatedEmployee = ed.updateEmployeeById(empId, empName, empUsername, empPosition);
		System.out.println(updatedEmployee);
		
		if (updatedEmployee == 1) {
			response.sendRedirect("employeehome");
		} else {
			response.sendRedirect("profile");
		}
	}
	
}
