package com.dean.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dean.daos.ReimbursementDao;
import com.dean.daos.ReimbursementDaoImpl;
import com.dean.models.Reimbursement;

public class CreateRequestServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    public CreateRequestServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);

		if (session != null) {
			request.getRequestDispatcher(
					"Views/createRequest.html").forward(request, response);
		} else {
			response.sendRedirect("login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		int id = (Integer) session.getAttribute("id");
		String reimbursementDescription = request.getParameter("reimbursementDescription");
		String reimbursementStatus = "PENDING";
		float reimbursementAmount = Float.parseFloat(request.getParameter("reimbursementAmount"));
		int employeeId = id;
		
		Reimbursement r = new Reimbursement();
		r.setReimbursementDescription(reimbursementDescription);
		r.setReimbursementStatus(reimbursementStatus);
		r.setReimbursementAmount(reimbursementAmount);
		r.setEmployeeId(employeeId);
		
		ReimbursementDao rd = new ReimbursementDaoImpl();
		int numCreated = rd.createReimbursement(r);
		
		if (numCreated==1) {
			response.sendRedirect("employeehome");
		} else {
			response.sendRedirect("create");
		}
		
		
	}
}
