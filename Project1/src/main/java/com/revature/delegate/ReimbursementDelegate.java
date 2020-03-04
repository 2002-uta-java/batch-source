package com.revature.delegate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.JSONArray;
import org.json.JSONObject;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Reimbursement;
import com.revature.service.ReimbursementService;

public class ReimbursementDelegate {

	private ReimbursementService remService = new ReimbursementService();
	private ObjectMapper om = new ObjectMapper();
	StringBuilder sb = new StringBuilder();
	
	private int id;
	private String type;
	private double ramount;
	private String rdate;
	private String reciept;
	private String notes;
	private int emplId;
	private int managerId;
	private String adate;
	private double amount;
	private String comment;
	private String status;
	private List<Reimbursement> newList;

	
	public ReimbursementDelegate() {
		super();
	}

	public void getAllReimbursement(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Reimbursement> reimbursements = remService.getReimbursement();
		
		try(PrintWriter pw = response.getWriter()){
			pw.write(om.writeValueAsString(reimbursements));
		}
	}
	
	public void getReimbursementId(HttpServletRequest request, HttpServletResponse response) throws IOException {
			String b = request.getParameter("remId");
			int id = Integer.parseInt(b);
			List<Reimbursement> remId = remService.getReimbursementId(id);
			this.newList = remId;
		
		try(PrintWriter pw = response.getWriter()){
			pw.write(om.writeValueAsString(newList));
		}
		
	}
	public void getStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
	try (BufferedReader requestReader = request.getReader();) {
		String newEmpIdJson = requestReader.readLine();	
		String b = om.readValue(newEmpIdJson, String.class);
		int id = Integer.parseInt(b);
		String status = remService.getStatus(id);
		this.status = status;
		}
	
	try(PrintWriter pw = response.getWriter()){
		pw.write(om.writeValueAsString(status));
		}
	}
	
	public void getMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String authToken = request.getHeader("Authorization");
		if(authToken != null) {
			String[] tokenArr = authToken.split(":");
			String permission = tokenArr[1];
			String managerId = tokenArr[3];
			String emplId = tokenArr[2];
			int pId = Integer.parseInt(permission);
			int mId = Integer.parseInt(managerId);
			int eId = Integer.parseInt(emplId);
			if(pId == 1) {
			List<Reimbursement> list = remService.managerStatus(mId);
			this.newList = list;
			} else {
			List<Reimbursement> eList = remService.employeeStatus(eId);
			this.newList = eList;
				}		
		}
	try(PrintWriter pw = response.getWriter()){
		pw.write(om.writeValueAsString(newList));
		}
	}
	
	public void getReciept(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try (BufferedReader requestReader = request.getReader();) {
			String newEmpIdJson = requestReader.readLine();	
			String b = om.readValue(newEmpIdJson, String.class);
			int id = Integer.parseInt(b);
			String reciept = remService.getReciept(id);
			this.reciept = reciept;
			}
		
		try(PrintWriter pw = response.getWriter()){
			pw.write(om.writeValueAsString(reciept));
			}	
	}

	public void deleteReimbursement(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try (BufferedReader requestReader = request.getReader();) {
			String newEmpIdJson = requestReader.readLine();	
			String b = om.readValue(newEmpIdJson, String.class);
			int id = Integer.parseInt(b);
			this.id = id;
			remService.deleteReimbursement(id);
			}
	
		if(id != 0) {
			try(PrintWriter pw = response.getWriter()) {
			pw.write(om.writeValueAsString("Failed to remove employee")); }
		}
		 else {
			try(PrintWriter pw = response.getWriter()) {
				pw.write(om.writeValueAsString("Employee Deleted")); }
		}
	}

	public void createReimbursement(HttpServletRequest request, HttpServletResponse response) throws IOException {
			try (BufferedReader requestReader = request.getReader();) {
			String jsonData = requestReader.readLine();
			JSONObject data = new JSONObject(jsonData);
			JSONArray jsonArr = data.getJSONArray("Rem");
		    for (int i = 0; i < jsonArr.length(); i++) {
		        JSONObject jsonObj = jsonArr.getJSONObject(i);
		        Reimbursement newRem = new Reimbursement(type, rdate, ramount, reciept, notes, emplId, managerId, adate, amount, comment, status);
		        type = newRem.setRemType(jsonObj.getString("type"));
		        rdate = newRem.setRemRequestDate(jsonObj.getString("rdate"));
		        ramount = newRem.setRemRequestedAmount(jsonObj.getDouble("ramount"));
		        reciept = newRem.setRemReciept(jsonObj.getString("reciept"));
		        notes = newRem.setRemNotes(jsonObj.getString("notes"));
		        emplId = newRem.setEmployeeId(jsonObj.getInt("emplId"));
		        managerId = newRem.setManagerId(jsonObj.getInt("managerId"));
		        adate = newRem.setRemApprovedDate(jsonObj.getString("adate"));
		        amount = newRem.setRemApprovedAmount(jsonObj.getDouble("amount"));
		        comment = newRem.setRemComment(jsonObj.getString("comment"));
		        status = newRem.setRemStatus(jsonObj.getString("status"));

		       remService.createReimbursementByFunction(newRem);
		    }
		
				try(PrintWriter pw = response.getWriter()) {
					pw.write(om.writeValueAsString("New Reimbursement Created")); 
					}
				}
			}
	
	public void updateReimbursement(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try (BufferedReader requestReader = request.getReader();) {
			String newEmpIdJson = requestReader.readLine();	
			JSONArray jsonArr = new JSONArray(newEmpIdJson);
		    for (int i = 0; i < jsonArr.length(); i++) {
		        JSONObject jsonObj = jsonArr.getJSONObject(i);
		        Reimbursement updateRem = new Reimbursement(type, adate, amount, comment, status);
		        type = updateRem.setRemType(jsonObj.getString("type"));
		        adate = updateRem.setRemApprovedDate(jsonObj.getString("adate"));
		        amount = updateRem.setRemApprovedAmount(jsonObj.getDouble("amount"));
		        comment = updateRem.setRemComment(jsonObj.getString("comment"));
		        status = updateRem.setRemStatus(jsonObj.getString("status"));
		        remService.updateReimbursementByFunction(updateRem);
		    	}		

				try(PrintWriter pw = response.getWriter()) {
				pw.write(om.writeValueAsString("Update complete"));
			
				}
		
			}
	}
}
