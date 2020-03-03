package com.revature.servlets.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;

/**
 * Servlet implementation class ReimbursementsServlet
 */
public class ReimbursementsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReimbursementService rs = new ReimbursementService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimbursementsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String employeeIdString = request.getParameter("reimFor");
		
		List<Reimbursement> reimList = new ArrayList<>();
		
		if (employeeIdString == null) {
			
			reimList = rs.getAllReimbursements(null);
			
		} else if (employeeIdString.matches("^\\d+$")) {
			
			int employeeId = Integer.parseInt(employeeIdString);
			
			Employee e = new Employee();
			e.setId(employeeId);
			
			reimList = (rs.getAllReimbursements(e));
			
		} else {
			response.sendError(400, "Mistakes were made...");
		}
		
		ObjectMapper om = new ObjectMapper();
		String reimbursementJson = om.writeValueAsString(reimList);
		
		try (PrintWriter pw = response.getWriter();) {
			pw.write(reimbursementJson);
			response.setStatus(200);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empIdStr = request.getParameter("employee"); 
		String category = request.getParameter("category");
		String amountStr = request.getParameter("amount");
		
		Reimbursement reim = new Reimbursement();
		
		reim.setEmployee(Integer.parseInt(empIdStr));
		reim.setCategoryById(Integer.parseInt(category));
		reim.setAmount(Double.parseDouble(amountStr));
		
		rs.createReimbursement(reim);
		
		if (reim.getId() > 0) {
			response.setStatus(200);
		} else {
			response.sendError(400, "Mistakes were made...");
		}
	}

}
