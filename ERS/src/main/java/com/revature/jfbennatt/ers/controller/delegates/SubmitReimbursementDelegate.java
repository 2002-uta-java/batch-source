package com.revature.jfbennatt.ers.controller.delegates;

import java.io.IOException;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.jfbennatt.ers.controller.RequestDispatcher;
import com.revature.jfbennatt.ers.models.Employee;

/**
 * This class handles reimbursement submissions. This class needs to be declared
 * (i.e. should not be declared as a {@link Delegate} object). This is because
 * this class needs the {@link RequestDispatcher} to communicate success (or
 * failure) to the {@link ViewDelegate}.
 * 
 * @author Jared F Bennatt
 *
 */
public class SubmitReimbursementDelegate extends Delegate {
	public static final int MAX_DESCRIPTION_LENGTH = 140;
	public static final String DESCRIPTION_ID = "description";
	public static final String AMOUNT_ID = "amount";
	public static final String DATE_ID = "date";

	/**
	 * Regex for decimals with at most two decimal places
	 */
	public static final String AMOUNT_REGEX = "^(\\d+(\\.\\d{1,2})?|\\.\\d{1,2})$";
	/**
	 * Pattern that can be used to check amount strings.
	 */
	public static final Pattern AMOUNT_PATTERN = Pattern.compile(AMOUNT_REGEX);

	private RequestDispatcher dispatcher;

	/**
	 * Default constructor (does nothing).
	 */
	public SubmitReimbursementDelegate() {
		super();
	}

	/**
	 * Set the {@link RequestDispatcher} used to communicate with the
	 * {@link ViewDelegate}.
	 * 
	 * @param dispatcher {@link RequestDispatcher} for this
	 *                   SubmitReimbursementDelegate.
	 */
	public void setRequestDispatcher(final RequestDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	/**
	 * Reads the form submitted from the client for the description, amount, and
	 * date of reimbursement (date from which the employee is attempting to be
	 * reimbursed not the date the request was submitted--that's handled
	 * automagically).
	 */
	@Override
	protected void processRequest(Employee employee, String path, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		final String description = request.getParameter(DESCRIPTION_ID);
		final String amount = request.getParameter(AMOUNT_ID);
		final String date = request.getParameter(DATE_ID);
		Logger.getRootLogger()
				.debug("Attempting to submit reimbursement request: " + amount + ", " + description + ", " + date);

		if (!AMOUNT_PATTERN.matcher(amount).matches()) {
			dispatcher.setMessage(ViewDelegate.FAIL);
		} else {
			// subimt request, give it the current time
			if (empService.submitRequest(employee, description, amount, date, new Date())) {
				dispatcher.setMessage(ViewDelegate.SUCCESS);
			} else {
				dispatcher.setMessage(ViewDelegate.FAIL);
			}
		}

		response.sendRedirect(RequestDispatcher.CONTEXT_ROOT + HOME);
	}

}
