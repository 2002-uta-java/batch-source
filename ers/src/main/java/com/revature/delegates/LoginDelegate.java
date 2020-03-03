package com.revature.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revature.dao.*;
import com.revature.model.*;

public class LoginDelegate {
	private EmployeeDAO edao = new EmployeeDaoImpl();

	public void authenticate(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String uName     = req.getParameter("username");
		String password  = req.getParameter("password");

		Employee em = edao.getEmployee(uName);
		
		if (em != null && em.getPass().equals(password)) {
			String token = em.getId() + ":" + em.getEmail() + ":" + em.getIsManager();
			res.setStatus(200);
			res.setHeader("Authorization", token);
		} else
			res.sendError(401);
	}
	
	public boolean isAuthorized(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String authToken = req.getHeader("Authorization");
		if (authToken != null) {
			String[] tokenArr = authToken.split(":");
			if(tokenArr.length == 3) {
				String idStr = tokenArr[0];
				String email = tokenArr[1];
				if(idStr.matches("^\\d+$")) {
					System.out.println(idStr);
					Employee em = edao.getEmployee(Integer.parseInt(idStr));
					if(em !=null && em.getEmail().equals(email)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public void addReimbursement(HttpServletRequest req, HttpServletResponse res) {
		
	}
}
