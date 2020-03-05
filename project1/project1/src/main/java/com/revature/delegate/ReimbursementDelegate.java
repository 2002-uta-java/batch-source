package com.revature.delegate;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.services.EmployeeService;
import com.revature.services.ReimbursementService;

public class ReimbursementDelegate {

	private Logger log = Logger.getRootLogger();
	private ReimbursementService rs = new ReimbursementService();
	private EmployeeService es = new EmployeeService();
	
	public void getReimbursements(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = request.getServletPath();
		if("/api/reimbursements".equals(path)) {
			
		} else if(path.substring(20).length() > 0) {
			String status = path.substring(20);
			if("pending".contentEquals(status) || "resolved".equals(status)) {
				int id = Integer.parseInt(request.getHeader("Authorization").split(":")[0]);
				Employee e = es.myInfo(id);
				List<Reimbursement> reimbursements = rs.getMyReimbursementsByStatus(e, status);
				ObjectMapper om = new ObjectMapper();
				String reimbursementJson = om.writeValueAsString(reimbursements);
				try(PrintWriter pw = response.getWriter();){
					pw.write(reimbursementJson);
				}
				response.setStatus(200);
			} else if(status.startsWith("pending") || status.startsWith("resolved")) {
				status = status.startsWith("pending") ? "pending" : "resolved";
				List<Reimbursement> reimbursements = rs.getMyReimbursementsByStatus(status);
				ObjectMapper om = new ObjectMapper();
				String reimbursementJson = om.writeValueAsString(reimbursements);
				try(PrintWriter pw = response.getWriter();){
					pw.write(reimbursementJson);
				}
				response.setStatus(200);
			} else if(status.startsWith("id")) {
				String eidStr = status.substring(3);
				if (eidStr.matches("^\\d+$")) {
					int eid = Integer.parseInt(eidStr);
					Employee e = es.myInfo(eid);
					List<Reimbursement> reimbursements = rs.getEmployeeReimbursements(e);
					ObjectMapper om = new ObjectMapper();
					String reimbursementJson = om.writeValueAsString(reimbursements);
					try(PrintWriter pw = response.getWriter();){
						pw.write(reimbursementJson);
					}
					response.setStatus(200);
				} else {
					log.error("Invalid eid param\n");
					response.sendError(400, "Invalid ID param");
				}
			}
			else {
				log.error("Invalid status param\n");
				response.sendError(400, "Invalid status param");
			}
		}
	}
	
	public void postReimbursement(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = request.getServletPath();
		if("/api/reimbursements/create".equals(path)) {
			int id = Integer.parseInt(request.getHeader("Authorization").split(":")[0]);
			String expenseStr = request.getParameter("expense");
			String rtype = request.getParameter("rtype");
			if (expenseStr.matches("^\\d+.?\\d{0,2}$")) {
				double expense = Double.parseDouble(expenseStr);
				Employee e = es.myInfo(id);
				int createdReimbursement = rs.createReimbursement(e, expense, rtype);
				if(createdReimbursement > 0) {
					response.setStatus(200);
				} else {
					log.error("Invalid reimbursement params\n");
					response.sendError(400, "Failed to create reimbursement");
				}
			} else {
				log.error("Invalid expense param\n");
				response.sendError(400, "Failed to create reimbursement");
			}
		} else if("/api/reimbursements/update".equals(path)) {
			int id = Integer.parseInt(request.getHeader("Authorization").split(":")[0]);
			String ridStr = request.getParameter("rid");
			String resolution = request.getParameter("resolution");
			if (ridStr.matches("^\\d+$")) {
				int rid = Integer.parseInt(ridStr);
				int updatedReimbursement = rs.updateReimbursement(rid, resolution, id);
				if(updatedReimbursement > 0) {
					response.setStatus(200);
				} else {
					log.error("Invalid reimbursement params\n");
					response.sendError(400, "Failed to update reimbursement");
				}
			}
		}
	}
}
