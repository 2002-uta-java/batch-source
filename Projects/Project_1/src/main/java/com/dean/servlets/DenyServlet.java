package com.dean.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dean.daos.ReimbursementDao;
import com.dean.daos.ReimbursementDaoImpl;
import com.dean.models.Reimbursement;

public class DenyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

    public DenyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int reimbursementId = Integer.parseInt(request.getParameter("reimbursementId"));
		String reimbursementStatus = request.getParameter("reimbursementStatus");
		
		Reimbursement r = new Reimbursement();
		r.setReimbursementId(reimbursementId);
		r.setReimbursementStatus(reimbursementStatus);
		
		ReimbursementDao rd = new ReimbursementDaoImpl();
		int deniedReimbursements = rd.denyReimbursementByErbId(reimbursementId);

		if (deniedReimbursements==1) {
			response.sendRedirect("managerhome");
		} else {
			response.sendRedirect("create");
		}
		
	}
	
}
