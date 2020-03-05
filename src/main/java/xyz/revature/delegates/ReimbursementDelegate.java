package xyz.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import xyz.revature.models.Reimbursement;
import xyz.revature.services.ReimbursementService;

public class ReimbursementDelegate {
	private ReimbursementService reService= new ReimbursementService();
	
	
	public void expenses(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Reimbursement> reimbursements = reService.getAllReimbursements();
		System.out.println(reimbursements);
		try (PrintWriter pw = response.getWriter()) {
			System.out.println("printwriting...");
			pw.write(new ObjectMapper().writeValueAsString(reimbursements));
		}
	}
	public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String reimbIdStr = request.getParameter("reimbursementId");
		String approvalStr = request.getParameter("approval");
		int reimbId = 0;
		if (reimbIdStr.matches("-?(0|[1-9]\\d*)")) {
			reimbId = Integer.parseInt(reimbIdStr);
		}
		boolean approval = Boolean.parseBoolean(approvalStr);
		int updated = reService.update(reimbId, approval);
		if (updated == 0) {
			throw new IOException();
		}
		
	}
	public void addExpense(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String empIdStr = request.getParameter("employeeId");
		String expenseType = request.getParameter("expenseType");
		String amountStr = request.getParameter("amount");
		Reimbursement reimb = new Reimbursement();
		int empId = 0;
		if (empIdStr.matches("-?(0|[1-9]\\d*)")) {
			empId = Integer.parseInt(empIdStr);
		}
		reimb.setEmployeeId(empId);
		reimb.setExpenseType(expenseType);
		int amount = 0;
		if (amountStr.matches("-?(0|[1-9]\\d*)")) {
			amount = Integer.parseInt(amountStr);
		}
		reimb.setAmount(amount);
		int rId = reService.addReimbursement(reimb);
		reimb.setEmployeeId(rId);
	}
	public void status(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String empIdStr = request.getParameter("employeeId");
		int empId = 0;
		if (empIdStr.matches("-?(0|[1-9]\\d*)")) {
			empId = Integer.parseInt(empIdStr);
		}
		List<Reimbursement> reimbursements = reService.getReimbursementsOfEmployee(empId);
		try (PrintWriter pw = response.getWriter()) {
			System.out.println("printwriting...");
			pw.write(new ObjectMapper().writeValueAsString(reimbursements));
		}
	}
	
}
