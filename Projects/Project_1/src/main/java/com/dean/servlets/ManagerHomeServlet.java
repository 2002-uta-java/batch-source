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

public class ManagerHomeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    public ManagerHomeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			request.getRequestDispatcher("Views/managerHome.html").forward(request, response);
		} else {
			response.sendRedirect("login");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		int reimbursementId = Integer.parseInt(request.getParameter("reimbursementId"));
		String reimbursementStatus = request.getParameter("reimbursementStatus");

		Reimbursement r = new Reimbursement();
		r.setReimbursementId(reimbursementId);
		r.setReimbursementStatus(reimbursementStatus);
		
		ReimbursementDao rd = new ReimbursementDaoImpl();
		int approvedReimbursements = rd.approveReimbursementByErbId(reimbursementId);
		System.out.println(approvedReimbursements);

		if (approvedReimbursements==1) {
			response.sendRedirect("managerhome");
		} else {
			response.sendRedirect("create");
		}
		
		

	}
	
}
