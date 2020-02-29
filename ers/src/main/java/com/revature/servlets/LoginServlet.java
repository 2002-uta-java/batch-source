package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revature.dao.*;
import com.revature.model.*;

public class LoginServlet {
	private EmployeeDAO edao = new EmployeeDaoImpl();

	public void authenticate(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String uName     = req.getParameter("username");
		String password  = req.getParameter("password");

		Employee em = edao.getEmployee(uName);
		
		if (em != null && em.getPass().equals(password)) {
			String token = em.getId() + ":" + em.getEmail();
			res.setStatus(200);
			res.setHeader("Authorization", token);
			req.getRequestDispatcher("/static/employeepage.html").forward(req, res);
		} else
			res.sendError(401);
	}
	
	public boolean isAuthorized(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String authToken = req.getHeader("Authorization");
		if (authToken != null) {
			String[] tokenArr = authToken.split(":");
			if(tokenArr.length == 2) {
				String idStr = tokenArr[0];
				String email = tokenArr[1];
				if(idStr.matches("^\\d+$")) {
					Employee em = edao.getEmployee(Integer.parseInt(idStr));
					if(em !=null && em.getEmail().equals(email)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public void register(HttpServletRequest req, HttpServletResponse res) {
		
	}
}
