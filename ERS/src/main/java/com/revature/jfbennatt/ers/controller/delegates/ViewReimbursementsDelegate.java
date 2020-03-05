package com.revature.jfbennatt.ers.controller.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.jfbennatt.ers.controller.RequestDispatcher;
import com.revature.jfbennatt.ers.models.Employee;
import com.revature.jfbennatt.ers.models.Reimbursement;

public class ViewReimbursementsDelegate extends Delegate {
	private final ObjectMapper objMapper = new ObjectMapper();

	/**
	 * Default constructor (does nothing).
	 */
	public ViewReimbursementsDelegate() {
		super();
	}

	@Override
	protected void processRequest(Employee employee, String path, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// get the method by cutting off the (likely) /view
		final String method = path.substring(RequestDispatcher.VIEW_REIMBURSEMENT_ROOT.length());

		if (employee.isManager()) {
			getManagerReimbursements(employee, method, request, response);
		} else {
			getEmployeeReimursements(employee, method, request, response);
		}
	}

	private void getEmployeeReimursements(Employee employee, String method, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		switch (method) {
		case "":
			getAllRecordsByEmployee(employee, request, response);
			break;
		default:
			response.sendError(404);
		}
	}

	private void getAllRecordsByEmployee(Employee employee, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		final List<Reimbursement> reimbs = empService.getAllReimbursementsByEmployeeId(employee.getEmpId());

		if (reimbs != null) {
			try (final PrintWriter pw = response.getWriter()) {
				final String json = objMapper.writeValueAsString(reimbs);
				pw.write(json);
			}

			response.setStatus(200);
		} else {
			response.setStatus(503);
		}
	}

	private void getManagerReimbursements(Employee employee, String method, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

}
