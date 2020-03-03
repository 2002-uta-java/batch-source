package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Account;
import com.revature.models.Request;
import com.revature.services.AccountService;
import com.revature.services.RequestService;

public class AccountDelegate {
	
	private final AccountService accountService = new AccountService();
	
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
			
			if ("EMPLOYEE".equals(tokenArr[1])) {
				requests = new RequestService().getEmployeeRequests(id);
			} else {
				requests = new RequestService().getManagerRequests(id);
			}
			
			try(PrintWriter pw = response.getWriter();){
				pw.append(new ObjectMapper().writeValueAsString(requests));
			}
			
		}
		
	}

}
