package com.revature.project1.delegates;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.project1.models.Appuser;
import com.revature.project1.services.AppuserService;

public class EmployeeDelegate {
	
	private AppuserService as = new AppuserService();
	
	public void submitRequest(HttpServletRequest request, HttpServletResponse response){
		
	}
	
	public void viewPending(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	public void viewUnresolved(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	public void viewProfile(HttpServletRequest request, HttpServletResponse response) {
		
	}

	public void updateProfile(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		System.out.println(email+" "+pass+" "+firstname+" "+lastname);
		
		Appuser au = new Appuser();
		au = as.getAppuserByEmail(email);
		
		au.setEmail(email);
		au.setPass(pass);
		au.setFirstname(firstname);
		au.setLastname(lastname);
		System.out.println(au.toString());
		
		if(as.updateAppuser(au)) {
			response.setStatus(200);
		} else {
			response.setStatus(401);
		}
		
	}

}
