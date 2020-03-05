package com.revature.project1.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewDelegate {

	public void getEmployeeView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setStatus(200);
		request.getRequestDispatcher("/static/views/employee.html").forward(request, response);
		
	}

	public void getManagerView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setStatus(200);
		request.getRequestDispatcher("/static/views/manager.html").forward(request, response);
	}

	public void getLoginView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setStatus(200);
		request.getRequestDispatcher("/static/views/index.html").forward(request, response);
	}

}
