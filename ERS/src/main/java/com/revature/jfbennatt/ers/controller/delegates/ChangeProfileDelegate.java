package com.revature.jfbennatt.ers.controller.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.jfbennatt.ers.models.Employee;

/**
 * This is the delegate that handles profile changes.
 * 
 * @author Jared F Bennatt
 *
 */
public class ChangeProfileDelegate extends Delegate {

	/**
	 * javascript mapping of the first name field for the change profile form.
	 */
	public static final String CHANGE_FIRST_NAME_ID = "change-first";

	/**
	 * javascript mapping of the last name field for the change profile form.
	 */
	public static final String CHANGE_LAST_NAME_ID = "change-last";

	/**
	 * javascript mapping of the email field for the change profile form.
	 */
	public static final String CHANGE_EMAIL_ID = "change-email";

	/**
	 * javascript mapping of the password field for the change profile form.
	 */
	public static final String CHANGE_PASSWORD = "change-password";

	@Override
	protected void processRequest(Employee employee, String path, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		final String newFirst = request.getParameter(CHANGE_FIRST_NAME_ID);
		final String newLast = request.getParameter(CHANGE_LAST_NAME_ID);
		final String newEmail = request.getParameter(CHANGE_EMAIL_ID);
		final String newPassword = request.getParameter(CHANGE_PASSWORD);

		// only update non-empty fields
		if (newFirst.length() > 0) {
			employee.setFirstName(newFirst);
		}

		if (newLast.length() > 0) {
			employee.setLastName(newLast);
		}

		if (newEmail.length() > 0) {
			employee.setEmail(newEmail);
		}

		if (newPassword.length() > 0) {
			employee.setPassword(newPassword);
		}

		if (!empService.changeProfile(employee)) {
			Logger.getRootLogger().error("Failed to change profile--still redirecting to profile page");
		}

		request.getRequestDispatcher(ViewDelegate.PROFILE).forward(request, response);
	}

}
