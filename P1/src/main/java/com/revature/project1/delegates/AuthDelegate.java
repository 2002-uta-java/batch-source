package com.revature.project1.delegates;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.project1.daos.AppuserDao;
import com.revature.project1.daos.AppuserDaoImpl;
import com.revature.project1.models.Appuser;
import com.revature.project1.services.AppuserService;

public class AuthDelegate {
	
private AppuserService au = new AppuserService();
	
	public void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("user_pass");
		System.out.println(email + " " + password);
		
		Appuser a = au.checkPassword(email, password);
//		a = au.checkPassword(email, password);
		
		if(a!=null) {
			String token = a.getId()+":"+a.isManager();
			response.setStatus(200);
			response.setHeader("Authorization", token);
		} else {
			response.sendError(401);
		}
	}

}
