package com.hylicmerit.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hylicmerit.models.Reimbursement;
import com.hylicmerit.service.ReimbursementService;

public class ReimbursementDelegate {
	private ReimbursementService rs = new ReimbursementService();
	public ReimbursementDelegate() {
		super();
	}
	public void getAllReimbursements(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Reimbursement> reimbursements = rs.getAll();
		try(PrintWriter pw = response.getWriter();){
			pw.append(new ObjectMapper().writeValueAsString(reimbursements));
		}
	}
	public void getAllByEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("employee");
		List<Reimbursement> reimbursements = rs.getAllByEmployee(email);
		try(PrintWriter pw = response.getWriter();){
			pw.append(new ObjectMapper().writeValueAsString(reimbursements));
		}
	}
	
	public void getReimbursementByManager(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("manager");
		List<Reimbursement> reimbursements = rs.getAllByManager(email);
		try(PrintWriter pw = response.getWriter();){
			pw.append(new ObjectMapper().writeValueAsString(reimbursements));
		}
	}
	public void updateReimbursement(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		String status = request.getParameter("status");
		Reimbursement r = rs.getById(id);
		r.setStatus(status);
		rs.updateReimbursement(r);
		response.setStatus(200);
		response.setHeader("Status", "Success");
	}
}
