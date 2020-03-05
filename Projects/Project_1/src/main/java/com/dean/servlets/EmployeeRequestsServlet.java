package com.dean.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dean.daos.ReimbursementDao;
import com.dean.daos.ReimbursementDaoImpl;
import com.dean.managers.ReimbursementManager;
import com.dean.managers.SessionManager;
import com.dean.models.Reimbursement;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EmployeeRequestsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
    public EmployeeRequestsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		SessionManager sm = new SessionManager();
		int id = (Integer) session.getAttribute("id");
		
		ReimbursementDao emp = new ReimbursementDaoImpl();
		
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		
		
		List<Reimbursement> reimbursementsList = emp.getReimbursementsByEmployeeId(id);
		System.out.println(reimbursementsList);
		boolean userExistsInSession = sm.validateUserExistence(request);
		if (userExistsInSession) {
		ReimbursementManager rm = new ReimbursementManager();
		reimbursementsList = rm.connectEmployeeERB(reimbursementsList);
		String erbsString = om.writeValueAsString(reimbursementsList);
		erbsString = "{\"erbemployee\": " + erbsString + "}";
		pw.write(erbsString);
		} else {
			System.out.println("n/a");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
