package com.revature.jfbennatt.ers.controller.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.jfbennatt.ers.controller.RequestDispatcher;
import com.revature.jfbennatt.ers.models.Employee;

public class ChangeProfileDelegate extends Delegate {

	public static final String CHANGE_FIRST_NAME_ID = "change-first";
	public static final String CHANGE_LAST_NAME_ID = "change-last";
	public static final String CHANGE_EMAIL_ID = "change-email";
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
