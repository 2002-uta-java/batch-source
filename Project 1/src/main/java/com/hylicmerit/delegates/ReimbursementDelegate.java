package com.hylicmerit.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hylicmerit.models.Employee;
import com.hylicmerit.models.Reimbursement;
import com.hylicmerit.service.EmployeeService;
import com.hylicmerit.service.ReimbursementService;

public class ReimbursementDelegate {
	private ReimbursementService rs = new ReimbursementService();
	private EmployeeService es = new EmployeeService();
	private static final Logger logger = LogManager.getLogger(ReimbursementDelegate.class.getName());
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
		//get id from request parameters
		int id = Integer.parseInt(request.getParameter("id"));
		//get status from request parameters
		String status = request.getParameter("status");
		//get reimbursement object based on given id
		Reimbursement r = rs.getById(id);
		//set status in reimbursement object to status from request
		r.setStatus(status);
		//update reimbursement in database
		rs.updateReimbursement(r);
		logger.info("Reimbursement successfully updated.");
		//set success status
		response.setStatus(200);
	}
	public void createReimbursement(HttpServletRequest request, HttpServletResponse response) {
		//get amount from request parameters
		double amount = Double.parseDouble(request.getParameter("amount"));
		//get employee_email from request parameters
		String email = request.getParameter("email");
		//get details  from request parameters
		String details = request.getParameter("details");
		//create date formatter
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		//get today's date
		Date date = new Date();
		//get employee object (for manager email)
		Employee e = es.getEmployeeById(email);
		//create reimbursement to pass to service
		Reimbursement r = new Reimbursement(email, e.getManager(), "Pending", details, formatter.format(date), amount);
		//create reimbursement in database
		rs.createReimbursement(r);
		logger.info("Reimbursement successfully created.");
		//set success status
		response.setStatus(200);
	}
}
