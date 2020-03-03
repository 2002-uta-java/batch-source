package com.revature.delegate;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Reimbursement;
import com.revature.service.ReimbursementService;

public class ReimbursementDelegate {

	private ReimbursementService rs = new ReimbursementService();

	public void getReimbursemets(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String requestPath = request.getServletPath();
		System.out.println(requestPath);
		if (requestPath.length() == "/api/reimbursements".length()) {
			List<Reimbursement> reimbursements = rs.getReimbursements();
			try (PrintWriter pw = response.getWriter();) {
				pw.write(new ObjectMapper().writeValueAsString(reimbursements));
			}
		} else {
			String username = request.getServletPath().substring(20);
			System.out.println(username);
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

}
