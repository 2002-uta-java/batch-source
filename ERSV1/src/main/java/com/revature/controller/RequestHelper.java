package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.revature.delegates.EmployeeDelegate;
import com.revature.delegates.ReimbursementDelegate;
import com.revature.delegates.ViewDelegate;
import com.revature.delegates.AuthDelegate;
import com.revature.models.Reimbursement;

// This is Dispatcher 
public class RequestHelper {

	/*
	 * /login -> returns Login.html view /home -> returns Home.html /profile ->
	 * returns Profile.html /api/employee -> returns Employee data in json
	 * /api/reimbursement -> returns Reimbursement data in json /api/home-info ->
	 * returns home data /api/profile-info -> returnmns profile data
	 */

	private ViewDelegate viewDelegate = new ViewDelegate();
	private EmployeeDelegate employeeDelegate = new EmployeeDelegate();
	private AuthDelegate authDelegate = new AuthDelegate();
	private ReimbursementDelegate reimbursementDelegate = new ReimbursementDelegate();

	public void processGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// http://localhost:8080/ERS/api/employee?username=gaddagada1
		String path = request.getRequestURI().substring(request.getContextPath().length());

		if (path.startsWith("/api/")) {
			// determine which delegate to use based on the path
			String record = path.substring(5).trim();
			switch (record) {

			case "employee": {
				String employee_id = request.getHeader("employee_id");
				//String emplid = (String) request.getParameterMap().get("employee_id");
				// process request with employee delegate
				if ((employee_id != null) && (!employee_id.isEmpty())) {
					employeeDelegate.viewEmployeeDetails(request, response, Integer.parseInt(employee_id));

				} else {
					// get all employees
					employeeDelegate.getAllEmployees(request, response);
				}

				// employeeDelegate.getAllEmployees(request, response);
				break;
			}
			case "reimbursement": {
				// process request with reimbursement delegate
				if (request.getParameterMap().containsKey("remibursementid")) {
					// get reimbursement based on given id
					String[] strReimburid = (String[]) request.getParameterMap().get("remibursementid");

					int rimburseId = Integer.valueOf(strReimburid[0]).intValue();
					if (rimburseId > 0) {
						reimbursementDelegate.viewReimbursementDetails(request, response, 0, rimburseId);
					} else {
						reimbursementDelegate.getAllReimbursements(request, response);
					}
					break;
				}
				reimbursementDelegate.getAllReimbursements(request, response);
				break;
			}
			default: {
				// send error if the api endpoint doesn't exist
				response.sendError(404, "The API endpoint you provided does not exist.");
				break;
			}
			}
		} else {
			// forward to correct page if it's a view request
			viewDelegate.resolveView(request, response);
		}
	}

	public void processUpdate(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		if (path.startsWith("/api/")) {
			String record = path.substring(5);
			switch (record) {
			case "employee": {
				// update employee
				employeeDelegate.updateEmployee(request, response);
				break;
			}
			case "reimbursement": {
				// update reimbursement
				String[] strReimbursementid = (String[]) request.getParameterMap().get("id");
				int reimbursementid = 0;
				if (strReimbursementid.length > 0) {
					reimbursementid = (Integer.valueOf(strReimbursementid[0])).intValue();

					reimbursementDelegate.updateReimbursement(request, response, reimbursementid);
				} else {
					response.sendError(400, "Reimbursement Id is required for this update.");
				}
				break;
			}
			case "reimbursement/bulk": {

				String jsonString = IOUtils.toString(request.getInputStream());
				System.out.println(jsonString);
				JSONObject jObj = new JSONObject(jsonString);
				System.out.println(jObj);
				JSONArray reimbList = jObj.getJSONArray("reimbursementsList");
			
				String status = jObj.getString("status");
				System.out.println(status);
				// reimbList.get



				int reimbursementid = 0;

				if (!reimbList.isEmpty()) {
					reimbursementDelegate.updateReimbursementStatus(request, response, reimbList, status);

				}

							
				break;
			}
			default: {
				// send error if the api endpoint doesn't exist
				response.sendError(404, "The endpoint your provided does not exist.");
			}
			}
		} else

		{
			// send invalid request error if request was sent to non api directory
			response.sendError(400, "You cannot update data in this directory.");
		}
	}

	public void processPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println(path);
		if (path.startsWith("/api/")) {
			String record = path.substring(5);
			switch (record) {
			case "employee": {
				// update employee
				employeeDelegate.createEmployee(request, response);
				break;
			}
			case "reimbursement": {
				// create reimbursement
				reimbursementDelegate.createReimbursement(request, response);
				break;
			}
			case "validate": {
				// use validation delegate

//				String username = request.getParameter("username");
//				String password = request.getParameter("password");
				authDelegate.authenticate(request, response);
				break;
			}
			default: {
				// send error if the api endpoint doesn't exist
				response.sendError(404, "The endpoint your provided does not exist.");
			}
			}
		} else {
			// send invalid request error if request was sent to non api directory
			response.sendError(400, "You cannot update data in this directory.");
		}
	}
}
