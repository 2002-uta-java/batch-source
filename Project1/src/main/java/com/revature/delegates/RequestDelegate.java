package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.services.RequestService;
import com.revature.models.Request;

public class RequestDelegate {
	
	private final RequestService requestService = new RequestService();
	private final Logger log = Logger.getRootLogger();
	
	public void getRequests(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String path = request.getServletPath().substring(5);
		
		if (path.matches("^requests/?$")) {
			List<Request> allRequests = requestService.getAllRequests();
			
			try(PrintWriter pw = response.getWriter();){
				pw.append(new ObjectMapper().writeValueAsString(allRequests));
			}
		} else if (path.startsWith("requests/") && path.substring(9).matches("^\\d+/?$")) {
			int id = Integer.parseInt(path.substring(9).replace("/", ""));
			
			Request a = requestService.getRequestById(id);
			
			try(PrintWriter pw = response.getWriter();){
				pw.append(new ObjectMapper().writeValueAsString(a));
			}
		}
		
	}
	
	public void postRequests(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String authKey = request.getHeader("Authorization");
		String[] authArr = authKey.split("%");
		int emplId = Integer.parseInt(authArr[0]);
		
		LocalDate rDate = LocalDate.parse(request.getParameter("date"), DateTimeFormatter.ISO_LOCAL_DATE);
		double amount = Double.parseDouble(request.getParameter("amount"));
		String description = request.getParameter("description");
		String category = request.getParameter("category");
		String imgUrl = "";
		
		if (requestService.createNewRequest(emplId, rDate, amount, description, imgUrl, category)) {
			response.setStatus(200);
		} else {
			response.sendError(401);
		}
		
	}
	
	public void putRequests(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String reviewType = request.getHeader("Review");
		int rb = Integer.parseInt(request.getHeader("Authorization").split("%")[0]);
		
		String path = request.getServletPath().substring(5);
		int id = Integer.parseInt(path.substring(9).replace("/", ""));
		
		Request req = requestService.getRequestById(id);
		req.setStatus(reviewType);
		req.setReviewed(LocalDateTime.now());
		req.setResolvedBy(rb);
		
		log.info(req);
		
		boolean updated = requestService.reviewRequest(req);
		
		if (updated) {
			response.setStatus(200);
		} else {
			response.sendError(401);
		}
		
	}

}
