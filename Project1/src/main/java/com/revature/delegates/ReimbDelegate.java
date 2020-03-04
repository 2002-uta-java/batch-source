package com.revature.delegates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.ReimbursementDao;
import com.revature.daos.ReimbursementDaoImpl;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;

public class ReimbDelegate {
	
	private ReimbursementDao rDao = new ReimbursementDaoImpl();
	private ReimbursementService rServ = new ReimbursementService();
	
	public void getReimbursements(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String requestPath = request.getServletPath();
		System.out.println(requestPath);
		
		if (requestPath.length() == "/api/reimb".length()) {
			System.out.println("Getting all reimbursements...");
			List<Reimbursement> reimbursements = rDao.getReimbursementsAll();
			
			try (PrintWriter pw = response.getWriter();) {
				pw.write(new ObjectMapper().writeValueAsString(reimbursements)); // returns list of reimbursements
			}
		} else {
			String reimbPath = request.getServletPath().substring(11); // shaves off /api/reimb/
			
			if (reimbPath.startsWith("p")) {
				System.out.println("Getting pending reimbs: " + reimbPath);
	
				List<Reimbursement> r = rDao.getPendingReimbursements();
				
				try (PrintWriter pw = response.getWriter()) {
						pw.write(new ObjectMapper().writeValueAsString(r));
				}
			}
			else if (reimbPath.startsWith("r")) {
				System.out.println("Getting resolved reimbs: " + reimbPath);
				
				List<Reimbursement> r = rDao.getResolvedReimbursements();
				
				try (PrintWriter pw = response.getWriter()) {
					pw.write(new ObjectMapper().writeValueAsString(r));
				}
			}
			else if (reimbPath.startsWith("e/")) {
				String idStr = reimbPath.substring(2);
				List<Reimbursement> r = rDao.getReimbursementsByEmployeeId(Integer.parseInt(idStr));
				
				System.out.println("Getting employee ID: " + idStr);
				
				if (r == null) {
					response.sendError(404, "No empl. with given ID");
				} else {
					try (PrintWriter pw = response.getWriter()) {
						pw.write(new ObjectMapper().writeValueAsString(r));
					}
				}
			}
			else {
				try {
			      int idStr = Integer.parseInt(reimbPath.trim());
			      System.out.println("Getting ReimbId: " + idStr);
			      
			      Reimbursement r = rDao.getReimbursementById(idStr);
			      
			      if (r == null) {
						response.sendError(404, "No reimb. with given ID");
					} else {
						try (PrintWriter pw = response.getWriter()) {
							pw.write(new ObjectMapper().writeValueAsString(r));
						}
					}
			    }
			    catch (NumberFormatException nfe)
			    {
			      System.out.println("NumberFormatException: " + nfe.getMessage());
			      response.sendError(404, "Bad request");
			    }
			}
		}
		
	}
	
	public void updateReimbursement(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String requestPath = request.getServletPath();
		String idStr = requestPath.substring(22); // shaves off /resolvereimbursement/
		System.out.println("Posting to Reimb. ID: " + idStr);
		
		List<String> dataPack = readReimbJson(request); // contains [manager id, reimb status]
		
		Reimbursement r = rDao.getReimbursementById(Integer.parseInt(idStr));
		
		if (r == null) {
			response.sendError(404, "No reimb. with given ID");
		} else {
			// Send updated reimb information to database. (probably need try200/catch500s if Dao goes wrong)
			int managerId = Integer.parseInt(dataPack.get(0));
			r.setIdManager(managerId);
			r.setStatus(dataPack.get(1));
			
			rDao.updateReimbursement(r);
			response.setStatus(200);
			System.out.println("Reimbursement successfully updated!");
		}
	}
	
	public List<String> readReimbJson(HttpServletRequest request) throws IOException {
		// Read the request payload into a String
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
		    buffer.append(line);
		}
		String data = buffer.toString();
		
		// If the String is not empty, parses the payload into a map
		Reimbursement r = null;
		if (!data.isEmpty()) {
		    ObjectMapper mapper = new ObjectMapper();
		    r = mapper.readValue(data, Reimbursement.class);
		}
		
		List<String> dataPack = new ArrayList<>();
		dataPack.add(Integer.toString(r.getIdManager()));
		dataPack.add(r.getStatus());
		
		return dataPack;
	}
	
	public void newReimbursement(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Reimbursement r = readNewReimbJson(request);
		
		// Validate amount (no negatives, zeros, etc.)
		if (!rServ.validAmount(r.getAmount())) {
			response.sendError(400, "Invalid amount.");
		}
		else {
			// Make sure cents renders correctly via rounding.
			r.setAmount(rServ.roundTwoDecimal(r.getAmount()));
			
			int newId = rServ.getNewId();
			r.setId(newId);
			
			// Update database.
			rDao.createReimbursement(r);
			response.setStatus(200);
		}
	}
	
	public Reimbursement readNewReimbJson(HttpServletRequest request) throws IOException {
		// Read the request payload into a String
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
		    buffer.append(line);
		}
		String data = buffer.toString();
		
		// If the String is not empty, parses the payload into a map
		Reimbursement r = null;
		if (!data.isEmpty()) {
		    ObjectMapper mapper = new ObjectMapper();
		    r = mapper.readValue(data, Reimbursement.class);
		}
		
		return r;
	}
}
