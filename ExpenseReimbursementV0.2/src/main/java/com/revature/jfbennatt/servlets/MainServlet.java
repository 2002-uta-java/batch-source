package com.revature.jfbennatt.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.jfbennatt.reimbursement.daos.postgres.EmployeeDaoPostgres;
import com.revature.services.ReimbursementService;

public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final Controller controller = new Controller();

	@Override
	public void init() throws ServletException {
		super.init();
		// TODO need to setup up services
		controller.setEmployeeDao(new EmployeeDaoPostgres());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final int status = controller.dispatch(req.getReader(), resp.getWriter());

		if (status < 200 || status > 299)
			resp.sendError(status);
	}
}
