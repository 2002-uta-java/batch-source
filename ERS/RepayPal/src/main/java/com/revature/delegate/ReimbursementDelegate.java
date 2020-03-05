package com.revature.delegate;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Reimbursement;
import com.revature.service.ReimbursementService;

public class ReimbursementDelegate {

	private ReimbursementService rs = new ReimbursementService();
	
	private static Logger log = Logger.getRootLogger();
	
	public void getReimbursemets(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String requestPath = request.getServletPath();
		log.debug(requestPath);
		if (requestPath.length() == "/api/reimbursements".length()) {
			List<Reimbursement> reimbursements = rs.getReimbursements();
			try (PrintWriter pw = response.getWriter();) {
				pw.write(new ObjectMapper().writeValueAsString(reimbursements));
			}
		} else {
			String username = request.getServletPath().substring(20);
			log.debug(username);
			if (username.matches("\\w+")) {
				List<Reimbursement> r = rs.getReimbursement(username);
				if (r == null) {
					response.sendError(404, "No reimbursements with given Username");
				} else {
					try (PrintWriter pw = response.getWriter()) {
						pw.write(new ObjectMapper().writeValueAsString(r));
					}
				}
			} else {
				response.sendError(400, "Invalid username param");
			}
		}
	}

	public void addReimbursement(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		double amount = Double.parseDouble(request.getParameter("amount"));
		String description = request.getParameter("description");
		log.debug(username + " " + amount + " " + description);
		
		Reimbursement r = new Reimbursement(username, amount, description);
		log.debug(r);
		if(rs.createReimbursement(r)) {
			response.setStatus(200);
		} else {
			response.sendError(401);
		}	
	}

	public void updateReimbursement(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String status = request.getParameter("status");
		String username = request.getParameter("username");
		log.debug(username + " " + status + " " + id);
		Reimbursement r = rs.getReimbursementById(id);
		log.debug(r);
		r.setResolved(status);
		r.setStatus(status);
		if(rs.updateReimbursement(r, username)) {
			response.setStatus(200);
		} else {
			response.sendError(401);
		}	
	}

}
