package com.dean.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dean.daos.ReimbursementDao;
import com.dean.daos.ReimbursementDaoImpl;
import com.dean.managers.ReimbursementManager;
import com.dean.managers.SessionManager;
import com.dean.models.Reimbursement;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReimbursementServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ReimbursementServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SessionManager sm = new SessionManager();

		String reimbursementStatus = request.getParameter("reimbursementStatus");
		String employeeId = request.getParameter("employeeId");
		String reimbursementId = request.getParameter("reimbursementId");
		String action = request.getParameter("action");

		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();

		ReimbursementDao rd = new ReimbursementDaoImpl();
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();

		if (action != null && reimbursementId != null) {

			try {
				int reimbursementIdNumber = Integer.parseInt(reimbursementId);
				Reimbursement r = rd.getReimbursementById(reimbursementIdNumber);

				if (action.equalsIgnoreCase("approve") && r != null) {
					System.out.println(r);
				} else if (action.equalsIgnoreCase("deny") && r != null) {
					rd.denyReimbursementByErbId(reimbursementIdNumber);
				} else {
					pw.write("N/A");
				}
			} catch (Exception e) {
				pw.write("N/A");
			}
		}

		if (reimbursementStatus != null) {
			if (reimbursementStatus.equalsIgnoreCase("pending")) {
				reimbursements = rd.getAllPendingReimbursements();
			} else if (reimbursementStatus.equalsIgnoreCase("resolved")) {
				reimbursements = rd.getAllResolvedReimbursements();
			}

		} else if (employeeId != null) {
			try {
				int id = Integer.parseInt(employeeId);
				reimbursements = rd.getAllResolvedReimbursementsEmployeeId(id);
			} catch (Exception e) {
				pw.write("N/A");
			}
		} else {
			reimbursements = rd.getAllReimbursements();
		}

		boolean userExistsInSession = sm.validateUserExistence(request);
		ReimbursementManager rm = new ReimbursementManager();
		reimbursements = rm.connectEmployeeERB(reimbursements);

		if (userExistsInSession) {
			String reimbursementsString = om.writeValueAsString(reimbursements);
			reimbursementsString = "{\"reimbursements\": " + reimbursementsString + "}";
			pw.write(reimbursementsString);
		} else {
			response.sendRedirect("login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
	}

}
