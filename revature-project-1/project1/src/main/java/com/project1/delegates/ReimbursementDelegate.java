package com.project1.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.models.Reimbursement;
import com.project1.services.ReimbursementService;

public class ReimbursementDelegate {
	private ReimbursementService reimbService = new ReimbursementService();
	private static final Logger LOGGER = LogManager.getLogger(ReimbursementDelegate.class.getName());
	
	public void getPendingReimb(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String empIdParam = request.getParameter("id");
		List<Reimbursement> reimbs = reimbService.getEmpReimbByIdPending(Integer.parseInt(empIdParam));
		LOGGER.info("get Pending Reimbursement!");
		try(PrintWriter pw = response.getWriter()){
			pw.write(new ObjectMapper().writeValueAsString(reimbs));
		}
	}
	
	public void getResolveReimb(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String empIdParam = request.getParameter("id");
		List<Reimbursement> reimbs = reimbService.getEmpReimbByIdResolve(Integer.parseInt(empIdParam));
		LOGGER.info("get Resolve Reimbursement!");
		try(PrintWriter pw = response.getWriter()){
			pw.write(new ObjectMapper().writeValueAsString(reimbs));
		}
	}
	public void getEmpPendingReimb(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String empIdParam = request.getParameter("id");
		List<Reimbursement> reimbs = reimbService.getAllReimbByStatus("Pending");
		LOGGER.info("get Employee Pendding Reimbursement!");
		try(PrintWriter pw = response.getWriter()){
			pw.write(new ObjectMapper().writeValueAsString(reimbs));
		}
	}
	public void getEmpResolveReimb(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String empIdParam = request.getParameter("id");
		List<Reimbursement> reimbs = reimbService.getAllReimbByStatus("Approve");
		List<Reimbursement> reimbs2 = reimbService.getAllReimbByStatus("Deny");
		System.out.println(reimbs);
		System.out.println(reimbs2);
		reimbs.addAll(reimbs2);
		LOGGER.info("get Employee Resolve Reimbursement!");
		
		try(PrintWriter pw = response.getWriter()){
			pw.write(new ObjectMapper().writeValueAsString(reimbs));
		}
	}
	public void updateReimb(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String reimbIdParam = request.getParameter("id");
		String reimbApproveBy = request.getParameter("approve-by");
		String reimbStatus = request.getParameter("status");
		Reimbursement reimb = reimbService.getReimbByID(Integer.parseInt(reimbIdParam));
		reimb.setApproveBy(Integer.parseInt(reimbApproveBy));
		reimb.setReimbStatus(reimbStatus);
		LOGGER.info("update reimbursement: "+ reimb);
		boolean flag = reimbService.updateReimbursement(reimb);
		LOGGER.info("update reimbursement: "+ flag);
	}
	
	public void createReimb(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String empIdParam = request.getParameter("empId");
		String reimbAmount = request.getParameter("amount");
		String reimbPurpose = request.getParameter("purpose");
		Reimbursement reimb = new Reimbursement();
		reimb.setEmpId(Integer.parseInt(empIdParam));
		reimb.setAmount(Integer.parseInt(reimbAmount));
		reimb.setPurpose(reimbPurpose);
		
		reimbService.createReimbWithFunction(reimb);
		LOGGER.info("create a new reimbursement: "+ reimb);
		
	}
}
