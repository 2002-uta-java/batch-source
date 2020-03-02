package com.revature.jfbennatt.ers.controller.delegates;

import com.revature.jfbennatt.ers.services.EmployeeService;

public class LogoutDelegate {
	private EmployeeService empService = null;

	
	
	public void setEmployeeService(EmployeeService empService) {
		this.empService = empService;
	}

}
