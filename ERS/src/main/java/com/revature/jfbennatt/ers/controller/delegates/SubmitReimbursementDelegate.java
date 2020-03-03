package com.revature.jfbennatt.ers.controller.delegates;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
	public static final String DATE_ID = "date";

	@Override
	protected void processRequest(Employee employee, String path, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		final String description = request.getParameter(DESCRIPTION_ID);
		final String amount = request.getParameter(AMOUNT_ID);
		final String date = request.getParameter(DATE_ID);
		Logger.getRootLogger().debug("date: " + date);
		final DateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
		Date sqlDate;
		try {
			sqlDate = new Date(formatter.parse(date).getTime());

			Logger.getRootLogger().debug(
					"Attempting to submit reimbursement request: " + amount + ", " + description + ", " + sqlDate);
			response.sendRedirect(RequestDispatcher.CONTEXT_ROOT + HOME);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
