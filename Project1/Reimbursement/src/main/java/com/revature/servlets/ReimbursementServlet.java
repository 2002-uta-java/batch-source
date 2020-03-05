package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daos.ReimbursementDao;
import com.daos.ReimbursementDaoImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.Reimbursement;

public class ReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReimbursementServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		
		ReimbursementDao rdi = new ReimbursementDaoImpl();
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		
		if(idStr != null) {
			int id = Integer.parseInt(idStr);
			List<Reimbursement> r = rdi.getReimbursementsByEmpId(id);
			
			String reimbursementString = om.writeValueAsString(r);
			
			reimbursementString = "{\"reimbursement\":"+reimbursementString+"}";
			
			pw.println(reimbursementString);
		} else {
			List<Reimbursement> reimbursements = rdi.getReimbursements();
			
			String reimbursementString = om.writeValueAsString(reimbursements);
			
			reimbursementString = "{\"reimbursement\":"+reimbursementString+"}";
			
			pw.println(reimbursementString);
		}
	}

	// post request to update from reimbursement string
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
