package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursmentDaoImpl;
import com.revature.model.Reimbursment;

public class ReimbursmentServlet {
private ReimbursementDAO rdao = new ReimbursmentDaoImpl();
	
	public void getEmployee(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String requestPath = req.getServletPath();
		if (requestPath.length() =="/api/employees".length()) {
			List<Reimbursment> rbs = rdao.getAllReimbursments();
			try (PrintWriter pw = res.getWriter()) {
				pw.write(new ObjectMapper().writeValueAsString(rbs));
			}
		} else {
			String idStr = req.getServletPath().substring(11);
			if (idStr.matches("^\\d+$")) {
				Reimbursment r = rdao.getReimbursment(Integer.parseInt(idStr));
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
