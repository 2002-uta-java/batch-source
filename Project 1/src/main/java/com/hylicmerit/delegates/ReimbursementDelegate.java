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
		List<Reimbursement> reimbursements = rs.getAllReimbursements();
		try(PrintWriter pw = response.getWriter();){
			pw.append(new ObjectMapper().writeValueAsString(reimbursements));
		}
	}
	
	public void getReimbursementById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Reimbursement r = rs.getReimbursementById(id);
		try(PrintWriter pw = response.getWriter();){
			pw.append(new ObjectMapper().writeValueAsString(r));
		}
	}
}
