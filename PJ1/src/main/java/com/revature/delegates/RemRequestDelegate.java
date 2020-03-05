package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.models.Request;
import com.revature.services.EmployeeService;
import com.revature.services.RemReqService;;
public class RemRequestDelegate {
	private RemReqService rRs = new RemReqService();
	private EmployeeService empS = new EmployeeService();
	
public void getReimbursements(HttpServletRequest req, HttpServletResponse response) throws IOException {
		
		// assigns what came with the request.
		String name = req.getParameter("name");
		String status = req.getParameter("status");
		
		boolean bSatus = Boolean.parseBoolean(status);
				
		if (name == null && status == null) {
			List<Request> reimbursements = rRs.getAllRequets();
			
			try(PrintWriter pw = response.getWriter()) {
				pw.write(new ObjectMapper().writeValueAsString(reimbursements));
			}
		} else if (status == null) {
			List<Request> reimbursementsByUsername = rRs.getRequestByEmployeeName(name);
			
			if (reimbursementsByUsername.size() == 0) {
				response.sendError(404);
			} else {
				try(PrintWriter pw = response.getWriter()) {
					pw.write(new ObjectMapper().writeValueAsString(reimbursementsByUsername));
				}
			}
		} else if (name == null) {
			List<Request> reimbursementsByStatus = rRs.getRequestByStatus(bSatus);
			
			if (reimbursementsByStatus.size() == 0) {
				response.sendError(404);
			} else {
				try(PrintWriter pw = response.getWriter()) {
					pw.write(new ObjectMapper().writeValueAsString(reimbursementsByStatus));
				}
			}
		} else {
			List<Request> reimbursementsByStatusAndUsername = rRs.getRequestByStatusAndEmployeeName(bSatus, name);
			
			if (reimbursementsByStatusAndUsername.size() == 0) {
				response.setStatus(400);
			} else {
				try(PrintWriter pw = response.getWriter()) {
					pw.write(new ObjectMapper().writeValueAsString(reimbursementsByStatusAndUsername));
				}
			}
		}
	}
/**updating our reimburstments by our parameters
 * 
 */
public void updateReimbursement(HttpServletRequest request) throws IOException {
	
	// Assigns what came with the request
	String reim_id = request.getParameter("id");
	String managerName = request.getParameter("name");
	boolean status = Boolean.parseBoolean(request.getParameter("status"));
	
	rRs.updateRequest(status, managerName, Integer.parseInt(reim_id));
}
/**
 * setting variables to make a new request
 * @param request
 * @throws IOException
 */
public void createReimbursement(HttpServletRequest request) throws IOException {
	
	
	String description = request.getParameter("name");
	boolean status = false;	// Since its a new reimbursement its automatically "Pending"
	String username = request.getParameter("employee_name");
	//this is only temporary will change when we update page
	String manName = null;
	
	Employee e = empS.getEmpByName(username);	// gets employee by username
	String name = e.getEmployeeName();	// assigns employee name to then set on the reimbursement.
	
	Request r = new Request();
	r.setName(name);;
	r.setStatus(status);;
	r.setName(description);
	r.setEmployeeName(name);;
	r.setAprovedBy(manName);;
	
	rRs.makeRequest(r);
	
}
	
	
}
