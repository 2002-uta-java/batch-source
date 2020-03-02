package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.ReimbursementDao;
import com.revature.daos.ReimbursementDaoImpl;
import com.revature.models.Reimbursement;

public class ReimbDelegate {
	
	private ReimbursementDao rDao = new ReimbursementDaoImpl();
	
	public void getReimbursements(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String requestPath = request.getServletPath();
		String reimbPath = request.getServletPath().substring(11); // shaves off /api/reimb/ (if not /api/reimb)
		
		if (requestPath.length() == "/api/reimb".length()) {
			System.out.println("Getting all reimbursements...");
			List<Reimbursement> reimbursements = rDao.getReimbursementsAll();
			
			try (PrintWriter pw = response.getWriter();) {
				pw.write(new ObjectMapper().writeValueAsString(reimbursements)); // returns list of reimbursements
			}
		} 
		else if (reimbPath.startsWith("p")) {
			System.out.println("Getting pending reimbs: " + reimbPath);

			List<Reimbursement> r = rDao.getPendingReimbursements();
			
			try (PrintWriter pw = response.getWriter()) {
					pw.write(new ObjectMapper().writeValueAsString(r)); // returns specific employee
			}
		}
		else if (reimbPath.startsWith("r")) {
			System.out.println("Getting resolved reimbs: " + reimbPath);
			
			List<Reimbursement> r = rDao.getResolvedReimbursements();
			
			try (PrintWriter pw = response.getWriter()) {
				pw.write(new ObjectMapper().writeValueAsString(r)); // returns specific employee
			}
		}
		else if (reimbPath.startsWith("e/")) {
			System.out.println("Getting employee ID: " + reimbPath);
			
			String idStr = reimbPath.substring(2);
			List<Reimbursement> r = rDao.getReimbursementsByEmployeeId(Integer.parseInt(idStr));
			
			if (r == null) {
				response.sendError(404, "No reimb. with given ID");
			} else {
				try (PrintWriter pw = response.getWriter()) {
					pw.write(new ObjectMapper().writeValueAsString(r)); // returns specific employee
				}
			}
		}
		else {
			response.sendError(404, "Bad request");
		}
		
	}
}
