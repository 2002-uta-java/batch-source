package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;

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
		String emplyee_id = request.getHeader("employee_id");
		List<Reimbursement> reimbursements = null;
		if((emplyee_id!= null )&& (Integer.parseInt(emplyee_id) > 0)) {
			reimbursements = rs.getAllReimbursementsByEmployeeId(Integer.parseInt(emplyee_id));
		}else {//if the user is manager get all reimbursements
			reimbursements = rs.getAllReimbursements();
		}
		
		
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

	}
	
	public void updateReimbursementStatus(HttpServletRequest request, HttpServletResponse response,JSONArray arrReimbiIds,String status) {
		boolean isReimupdated = rs.updateReimbursementStatus(arrReimbiIds,status);
		String message = "";
		if (isReimupdated) {
			message = "Updated successfully";
			response.setStatus(200);
			response.setHeader("Updated Reimbursement", message);
		} else {
			try {
				response.sendError(401);
				message = "Update failed";
				response.setHeader("Updated Reimbursement", message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void updateReimbursementStatus(HttpServletRequest request, HttpServletResponse response,JSONArray arrReimbiIds,
			String status, int approverId) {
		boolean isReimupdated = rs.updateReimbursementStatus(arrReimbiIds,status,approverId);
		String message = "";
		if (isReimupdated) {
			message = "Updated successfully";
			response.setStatus(200);
			response.setHeader("Updated Reimbursement", message);
		} else {
			try {
				response.sendError(401);
				message = "Update failed";
				response.setHeader("Updated Reimbursement", message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	
	public void createReimbursement(HttpServletRequest request, HttpServletResponse response) {
		String jsonString = null;
		String authKey = request.getHeader("Authorization");
		String[] authArr = authKey.split("%");
		int emplId = Integer.parseInt(authArr[0]);
				
		//double amount = Double.parseDouble(request.getParameter("cost"));
		String amount = request.getParameter("cost");
		String description = request.getParameter("description");
		String category = request.getParameter("category");
		String comments = request.getParameter("comments");
		
		Reimbursement newReim = new Reimbursement(new Date(System.currentTimeMillis()),
				description, category, amount, "PENDING", comments, emplId);
				

		
//		try {
//			jsonString = IOUtils.toString(request.getInputStream());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Reimbursement newReim = new Gson().fromJson(jsonString, Reimbursement.class);
//		newReim.setEmployee_id(emplId);
//		//Reimbursement newReimb = new Reimbursement();
//		
		
		
		
		
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
