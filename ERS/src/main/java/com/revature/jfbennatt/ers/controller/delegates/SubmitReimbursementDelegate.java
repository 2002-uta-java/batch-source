package com.revature.jfbennatt.ers.controller.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.jfbennatt.ers.controller.RequestDispatcher;
import com.revature.jfbennatt.ers.models.Employee;

public class SubmitReimbursementDelegate extends Delegate {
	public static final int MAX_DESCRIPTION_LENGTH = 140;
	public static final String DESCRIPTION_ID = "description";
	public static final String AMOUNT_ID = "amount";

	@Override
	protected void processRequest(Employee employee, String path, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		final String description = request.getParameter(DESCRIPTION_ID);
		final String amount = request.getParameter(AMOUNT_ID);
		Logger.getRootLogger().debug("Attempting to submit reimbursement request: " + amount + ", " + description);
		response.sendRedirect(RequestDispatcher.CONTEXT_ROOT + HOME);
	}

}
