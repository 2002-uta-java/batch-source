package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;

public class ReimbursementDelegate {
	private ReimbursementService rs = new ReimbursementService();

	public ReimbursementDelegate() {
		super();
	}

	public void getAllReimbursements(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Reimbursement> reimbursements = rs.getAllReimbursements();
		try (PrintWriter pw = response.getWriter();) {
			pw.write(new ObjectMapper().writeValueAsString(reimbursements));
		}
	}

	public Reimbursement viewReimbursementDetails(HttpServletRequest request, HttpServletResponse response,
			int employeeId, int reimbursementId) {
		String description = request.getParameter("description");
		Reimbursement reimbObj = rs.viewReimbursementDetails(reimbursementId);
		try (PrintWriter pw = response.getWriter();) {
			try {
				pw.write(new ObjectMapper().writeValueAsString(reimbObj));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return reimbObj;
	}

	public void updateReimbursement(HttpServletRequest request, HttpServletResponse response,int reimbursementid) {
		String jsonString = null;
		try {
			jsonString = IOUtils.toString(request.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reimbursement updatedReimbursementInfo = new Gson().fromJson(jsonString, Reimbursement.class);

		boolean isReimAdded = rs.updateReimbursement(updatedReimbursementInfo,reimbursementid);
		String message = "";
		if (isReimAdded) {
			message = "Updated successfully";
			response.setStatus(200);
			response.setHeader("New Reimbursement", message);
		} else {
			try {
				response.sendError(401);
				message = "Update failed";
				response.setHeader("New Reimbursement", message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
//		int reimbursementId = Integer.parseInt(request.getParameter("id"));
//		String status = request.getParameter("status");
//		Reimbursement r.setStatus(status);
//		rs.updateReimbursement(r);
//		response.setStatus(200);
	}

	public void createReimbursement(HttpServletRequest request, HttpServletResponse response) {
		String jsonString = null;
		try {
			jsonString = IOUtils.toString(request.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reimbursement newReim = new Gson().fromJson(jsonString, Reimbursement.class);

		//Reimbursement newReimb = new Reimbursement();
		boolean isReimAdded = rs.addReimbursement(newReim);
		String message="";
		if(isReimAdded) {
			message = "Created successfully";
			response.setStatus(200);
			response.setHeader("New Reimbursement", message);
		}else {
			try {
				response.sendError(401);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			message = " Creation failed";
			response.setHeader("New Reimbursement", message);
		}
		// TODO - Check for nulls and invalid inputs	
	}
}
