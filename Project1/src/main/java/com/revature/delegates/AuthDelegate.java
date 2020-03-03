package com.revature.delegates;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.models.Account;
import com.revature.services.AccountService;

public class AuthDelegate {
	
	private AccountService as = new AccountService();
	
	public void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Account a = as.getAccountByEmailAndPassword(email, password);
		
		if (a != null) {
			String token = a.getId() + "%" + a.getAcctType();
			response.setStatus(200);
			response.setHeader("Authorization", token);
		} else {
			response.sendError(401, "Invalid User Credentials");
		}
	}
	
	public boolean isAuthorized(HttpServletRequest request) {
		String authToken = request.getHeader("Authorization");
		if(authToken!=null) {
			
			// Decrypt Token Here
			
			String[] tokenArr = authToken.split("%");
			if(tokenArr.length == 2) {
				String idStr = tokenArr[0];
				String accountType = tokenArr[1];
				if(idStr.matches("^\\d+$")) {
					Account a = as.getAccountById(Integer.parseInt(idStr));
					if(a!=null && a.getAcctType().equals(accountType)) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
