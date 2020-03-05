package com.revature.jfbennatt.ers.controller.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.jfbennatt.ers.controller.RequestDispatcher;
import com.revature.jfbennatt.ers.models.Employee;
import com.revature.jfbennatt.ers.models.Reimbursement;

/**
 * Delegate for returning reimbursement requests.
 * 
 * @author Jared F Bennatt
 *
 */
public class ViewReimbursementsDelegate extends Delegate {
	/**
	 * Resource for viewing all pending requests
	 */
	public static final String PENDING = "/pending";
	/**
	 * Resource for viewing all processed requests
	 */
	public static final String PROCESSED = "/processed";

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

		Logger.getRootLogger().debug("view request: " + method);
		if (method.equals(PENDING)) {
			getAllPendingRecordsByEmployee(employee, response);
		} else if (method.equals(PROCESSED)) {
			getAllProcessedRecordsByEmployee(employee, response);
		} else if (employee.isManager()) {

		}
	}

	private void getAllProcessedRecordsByEmployee(Employee employee, HttpServletResponse response) throws IOException {
		final List<Reimbursement> reimbs = empService.getProcessedReimbursementsByEmployeeId(employee.getEmpId());

		if (reimbs != null) {
			try (final PrintWriter pw = response.getWriter()) {
				final String json = objMapper.writeValueAsString(reimbs);
				Logger.getRootLogger().debug("Json: " + json);
				pw.write(json);
			}

			response.setStatus(200);
		} else {
			response.setStatus(503);
		}
	}

	private void getAllPendingRecordsByEmployee(Employee employee, HttpServletResponse response) throws IOException {
		final List<Reimbursement> reimbs = empService.getPendingReimbursementsByEmployeeId(employee.getEmpId());

		if (reimbs != null) {
			try (final PrintWriter pw = response.getWriter()) {
				final String json = objMapper.writeValueAsString(reimbs);
				Logger.getRootLogger().debug("Json: " + json);
				pw.write(json);
			}

			response.setStatus(200);
		} else {
			response.setStatus(503);
		}
	}

}
