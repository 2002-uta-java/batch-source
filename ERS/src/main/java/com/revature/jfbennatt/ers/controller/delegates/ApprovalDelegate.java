package com.revature.jfbennatt.ers.controller.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.jfbennatt.ers.models.Employee;

/**
 * This is the delegate that handles approvals/rejections for managers.
 * 
 * @author Jared F Bennatt
 *
 */
public class ApprovalDelegate extends Delegate {
	public static final String APPROVE = "/approve";
	public static final String REJECT = "/reject";

	/**
	 * Default constructor (does nothing).
	 */
	public ApprovalDelegate() {
		super();
	}

	@Override
	protected void processRequest(Employee employee, String path, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		Logger.getRootLogger().debug("calling method " + path);
		if (path.startsWith(APPROVE)) {
			// need to add one to get rid of the /
			final int reimbId = Integer.parseInt(path.substring(APPROVE.length() + 1));
			if (!empService.approveRequest(employee.getEmpId(), reimbId)) {
				response.sendError(500);
			} else {
				response.setStatus(202);
			}
		} else if (path.startsWith(REJECT)) {
			final int reimbId = Integer.parseInt(path.substring(REJECT.length() + 1));
			if (!empService.rejectRequest(employee.getEmpId(), reimbId)) {
				response.sendError(500);
			} else {
				response.setStatus(202);
			}
		} else {
			response.sendError(400);
		}
	}
}
