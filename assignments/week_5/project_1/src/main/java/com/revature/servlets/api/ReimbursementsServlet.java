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
		String isManagerString = request.getParameter("manager");
		
		List<Reimbursement> reimList = new ArrayList<>();
		
		if (employeeIdString == null) {
			
			reimList = rs.getAllReimbursements(null);
			
			
		} else if (employeeIdString.matches("^\\d+$") && isManagerString == null) {
			
			int employeeId = Integer.parseInt(employeeIdString);
			
			Employee e = new Employee();
			e.setId(employeeId);
			
			reimList = (rs.getAllReimbursements(e));
			
		} else if (employeeIdString.matches("^\\d+$") && isManagerString.contentEquals("duncan")) {
			
			int employee0Id = Integer.parseInt(employeeIdString);
			Employee e0 = new Employee();
			e0.setId(employee0Id);
			
			reimList = rs.getAllReimbursementsWithId(e0);
			
		}  else {
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
		String description = request.getParameter("desc");
		
		System.out.println(description);
		
		Reimbursement reim = new Reimbursement();
		
		reim.setEmployee(Integer.parseInt(empIdStr));
		reim.setCategoryById(Integer.parseInt(category));
		reim.setAmount(Double.parseDouble(amountStr));
		reim.setDiscussion(description);
		
		rs.createReimbursement(reim);
		
		if (reim.getId() > 0) {
			response.setStatus(200);
		} else {
			response.sendError(400, "Mistakes were made...");
		}
	}
	
	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		String status = request.getParameter("status");
		String reimIdStr = request.getParameter("reim");
		String managerIdStr = request.getParameter("manager");
		
		int statusId = Integer.parseInt(status);
		int reimId = Integer.parseInt(reimIdStr);
		int managerId = Integer.parseInt(managerIdStr);
				
		Reimbursement reim = new Reimbursement();
		reim.setId(reimId);
		reim.setStatusById(statusId);
		reim.setApprovedBy(managerId);
		
		int didItRun = rs.updateReimbursement(reim);
		
		if(didItRun == 1) {
			response.setStatus(200);
		} else {
			response.sendError(400, "Mistakes were made...");
		}	
	}

}
