package com.revature.project1.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewDelegate {

	public void getEmployeeView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setStatus(200);
		request.getRequestDispatcher("Views/NewBird.html").forward(request, response);
		
	}

}
