package com.revature.delegate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			this.id = id;
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

	
}

