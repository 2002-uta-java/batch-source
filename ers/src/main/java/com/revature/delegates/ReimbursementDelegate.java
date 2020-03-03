package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.model.Reimbursement;

public class ReimbursementDelegate {
	private ReimbursementDAO rdao = new ReimbursementDaoImpl();
	
	public void getEmployee(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String requestPath = req.getServletPath();
		if (requestPath.length() == "/api/reimbursements".length()) {
			String authToken = req.getHeader("Authorization");
			String[] tokenArr = authToken.split(":");
			String idStr = tokenArr[0];
			List<Reimbursement> rbs = rdao.getAllReimbursements();
			
			List<Reimbursement> srbs = new ArrayList<>();
			
			for (Reimbursement r: rbs) {
				if (Integer.parseInt(idStr) == r.getEmployeeId())
					srbs.add(r);
			}
			
			try (PrintWriter pw = res.getWriter()) {
				pw.write(new ObjectMapper().writeValueAsString(srbs));
			}
		} else {
			String idStr = req.getServletPath().substring(11);
			if (idStr.matches("^\\d+$")) {
				Reimbursement r = rdao.getReimbursement(Integer.parseInt(idStr));
				if (r == null)
					res.sendError(404, "No Employee with given id");
				else {
					try (PrintWriter pw = res.getWriter()) {
						pw.write(new ObjectMapper().writeValueAsString(r));
					}
				}
			} else
				res.sendError(400, "Invalid ID");
		}
	}
}
