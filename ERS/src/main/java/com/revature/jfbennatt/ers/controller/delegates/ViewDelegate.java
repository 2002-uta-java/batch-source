package com.revature.jfbennatt.ers.controller.delegates;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.jfbennatt.ers.models.Employee;
import com.revature.jfbennatt.ers.services.EmployeeService;

public class ViewDelegate extends Delegate {

	private final EmployeeViewDelegate evDelegate = new EmployeeViewDelegate();
	private final ManagerViewDelegate mvDelegate = new ManagerViewDelegate();

	public ViewDelegate() {
		super();
	}

	@Override
	public void setEmployeeService(EmployeeService empService) {
		super.setEmployeeService(empService);
		evDelegate.setEmployeeService(empService);
		mvDelegate.setEmployeeService(empService);
	}

	@Override
	protected void processRequest(final Employee employee, final HttpServletRequest request,
			final HttpServletResponse response) throws IOException, ServletException {
		// else delegate to the employee view or manager view

		if (employee.isManager())
			mvDelegate.processRequest(employee, request, response);
		else
			evDelegate.processRequest(employee, request, response);
	}

}
