package com.revature.project1.delegates;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.project1.daos.AppuserDao;
import com.revature.project1.daos.AppuserDaoImpl;
import com.revature.project1.models.Appuser;

public class AuthDelegate {
	
private AppuserDao appuserDao = new AppuserDaoImpl();
	
	public void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println(email + " " + password);
		
		Appuser a = appuserDao.getAppuserByEmail(email);
		
		if(a!=null) {
			String token = a.getId()+":"+a.isManager();
			response.setStatus(200);
			response.setHeader("Authorization", token);
		} else {
			response.sendError(401);
		}
	}

}
