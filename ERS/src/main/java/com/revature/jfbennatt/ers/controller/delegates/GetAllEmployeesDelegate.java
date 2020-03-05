package com.revature.jfbennatt.ers.controller.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.jfbennatt.ers.models.Employee;

public class GetAllEmployeesDelegate extends Delegate {

	private final ObjectMapper objMapper = new ObjectMapper();

	/**
	 * Default constructor (does nothing).
	 */
	public GetAllEmployeesDelegate() {
		super();
	}

	@Override
	protected void processRequest(Employee employee, String path, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		final List<Employee> employees = empService.getallEmployeesExceptManager(employee.getEmpId());
		if (employees != null) {
			final String json = objMapper.writeValueAsString(employees);

			try (final PrintWriter pw = response.getWriter()) {
				Logger.getRootLogger().debug("Sending back: " + json);
				pw.write(json);
			}

			response.setStatus(200);
		} else {
			response.sendError(503);
		}
	}

}
