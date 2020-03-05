package com.revature.delegates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Account;
import com.revature.models.Request;
import com.revature.services.AccountService;
import com.revature.services.RequestService;

public class AccountDelegate {
	
	private final AccountService accountService = new AccountService();
	private final Logger log = Logger.getRootLogger();
	
	public void getUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String path = request.getServletPath().substring(5);
		
		if (path.matches("^user$")) {
			
			String authToken = request.getHeader("Authorization");
			
			String[] tokenArr = authToken.split("%");
			
			Account a = null;
			
			if(tokenArr.length == 2) {
				String idStr = tokenArr[0];
				if(idStr.matches("^\\d+$")) {
					a = accountService.getAccountById(Integer.parseInt(idStr));
					a.setPassword(""); // Clear Password
				}
			}
			
			if (a != null) {
				response.getWriter().write(new ObjectMapper().writeValueAsString(a));
			}
			
			
		} else if (path.matches("^users/?$")) {
			List<Account> allAccounts = accountService.getAllAccounts();
			
			try(PrintWriter pw = response.getWriter();){
				pw.append(new ObjectMapper().writeValueAsString(allAccounts));
			}
		} else if (path.matches("^users/\\d+/?$")) {
			int id = Integer.parseInt(path.substring(6).replace("/", ""));
			
			Account a = accountService.getAccountById(id);
			
			try(PrintWriter pw = response.getWriter();){
				pw.append(new ObjectMapper().writeValueAsString(a));
			}
		} else if (path.matches("^users/\\d+/requests/?$")) {
			String[] pathArr = path.split("/");
			int id = Integer.parseInt(pathArr[1]);
			
			String authToken = request.getHeader("Authorization");
			String[] tokenArr = authToken.split("%");

			List<Request> requests;
			
			Account a = accountService.getAccountById(id);
			
			if ("EMPLOYEE".equals(a.getAcctType())) {
				requests = new RequestService().getEmployeeRequests(id);
			} else {
				requests = new RequestService().getManagerRequests(id);
			}
			
			try(PrintWriter pw = response.getWriter();){
				pw.append(new ObjectMapper().writeValueAsString(requests));
			}
			
		}
		
	}
	
	public void putUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String path = request.getServletPath().substring(5);
		int id = Integer.parseInt(request.getHeader("Authorization").split("%")[0]);
		
		if (path.matches("^user/?$")) {
			
			InputStream in = request.getInputStream();
			StringBuilder result = null;
			try(BufferedReader reader = new BufferedReader(new InputStreamReader(in));) {
				result = new StringBuilder();
				String line;
				while((line = reader.readLine()) != null) {
					result.append(line);
				}
			}
			
			String parameters = result.toString();
			
			String[] pArr = parameters.split("&");
			
			String name = pArr[0].split("=")[1];
			String email = pArr[1].split("=")[1];
			String password = pArr[2].split("=")[1];
			
			
			if (accountService.updateUser(id, name, email, password)) {
				response.setStatus(200);
			}
			else {
				response.sendError(401);
			}
			
		} else {
			response.sendError(401);
		}
		
	}

}
