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
		try (BufferedReader requestReader = request.getReader();) {
			String newEmpIdJson = requestReader.readLine();	
			String b = om.readValue(newEmpIdJson, String.class);
			int id = Integer.parseInt(b);
			int remId = remService.getReimbursementId(id);
			this.id = remId;
		}
		
		try(PrintWriter pw = response.getWriter()){
			pw.write(om.writeValueAsString(id));
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

	public void createReimbursemetn(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try (BufferedReader requestReader = request.getReader();) {
			String newEmpIdJson = requestReader.readLine();	
			JSONArray jsonArr = new JSONArray(newEmpIdJson);
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
