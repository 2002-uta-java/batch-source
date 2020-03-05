package com.revature.delegate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewDelegate {

	public void resolveView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getServletPath();
		System.out.println("in our view delegate: "+ uri);
		
		response.setHeader("Authorization", request.getHeader("Authorization"));
		System.out.println("vd head" +request.getHeader("Authorization"));
		
		switch(uri) {
		case "/login":
			request.getRequestDispatcher("/static/Views/Login.html").forward(request, response);
			break;
		case "/employee-homepage":
			request.getRequestDispatcher("/static/Views/EmployeeHomepage.html").forward(request, response);
			break;
		case "/employee-submit-reimbursement":
			request.getRequestDispatcher("/static/Views/EmployeeSubmitReimbursement.html").forward(request, response);
			break;
		case "/employee-pending-reimbursements":
			request.getRequestDispatcher("/static/Views/EmployeePendingReimbursements.html").forward(request, response);
			break;
		case "/employee-resolved-reimbursements":
			request.getRequestDispatcher("/static/Views/EmployeeResolvedReimbursements.html").forward(request, response);
			break;
		case "/employee-profile":
			request.getRequestDispatcher("/static/Views/EmployeeProfile.html").forward(request, response);
			break;
		case "/manager-homepage":
			request.getRequestDispatcher("static/Views/ManagerHomepage.html").forward(request, response);
			break;
		case "/manager-pending-reimbursements":
			request.getRequestDispatcher("static/Views/ManagerPendingReimbursements.html").forward(request, response);
			break;
		case "/manager-resolved-reimbursements":
			request.getRequestDispatcher("static/Views/ManagerResolvedReimbursements.html").forward(request, response);
			break;
		case "/manager-view-employees":
			request.getRequestDispatcher("static/Views/ManagerViewEmployees.html").forward(request, response);
			break;
		default:
			response.sendError(404, "Static Resource Not Found");
		}
	}
}
