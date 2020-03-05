package com.revature.delegates;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;
import com.revature.models.Employee;

public class AuthDelegate {

	private EmployeeDao eDao = new EmployeeDaoImpl();
	private static Logger log = Logger.getRootLogger();
	
	public void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		log.info(email + "/" + password);
		
		Employee e = eDao.getEmployeeByEmailAndPassword(email, password);
		
		if(e!=null) {
			String token = e.getId() + ":" + e.getPosition();
			response.setStatus(200);
			response.setHeader("Authorization", token);
		} else {
			response.sendError(401);
		}
	}
	
	public boolean isAuthorized(HttpServletRequest request) {
		String authToken = request.getHeader("Authorization");
		
		//check if there is an auth header
		if(authToken!=null) {
			String[] tokenArr = authToken.split(":");
			
			// if the token is formatted correctly, we take the id and query for that user
			if(tokenArr.length == 2) {
				String idStr = tokenArr[0];
				String userRoleStr = tokenArr[1];
				
				Employee e = eDao.getEmployeeById(Integer.parseInt(idStr));
				if (e!=null && e.getPosition().contentEquals(userRoleStr)) {
					return true;
				}
				
			}
		}
		return false;
	}
	
	
}
