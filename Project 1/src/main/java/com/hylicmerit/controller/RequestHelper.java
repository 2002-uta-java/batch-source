package com.hylicmerit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hylicmerit.delegates.EmployeeDelegate;
import com.hylicmerit.delegates.ReimbursementDelegate;
import com.hylicmerit.delegates.ValidationDelegate;
import com.hylicmerit.delegates.ViewDelegate;

public class RequestHelper {
	private ViewDelegate vd = new ViewDelegate();
	private EmployeeDelegate ed = new EmployeeDelegate();
	private ValidationDelegate vald = new ValidationDelegate();
	private ReimbursementDelegate rd = new ReimbursementDelegate();
	
	public void processGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		if (path.startsWith("/api/")) {
			// determine which delegate to use based on the path
			String record = path.substring(5);
			switch (record) {
			case "employee": {
				//use employee delegate
				if(request.getParameterMap().containsKey("email")) {
					//get employee based on given email
					ed.getEmployeeById(request, response);
				} else {
					//get all employees
					ed.getAllEmployees(request, response);
				}
				break;
			}
			case "validate":{
				//use validation delegate
				vald.validateUser(request, response);
				break;
			}
			case "reimbursement":{
				if(request.getParameterMap().containsKey("employee")) {
					rd.getAllByEmployee(request, response);
				} else if(request.getParameterMap().containsKey("manager")) {
					rd.getReimbursementByManager(request, response);
				} else {
					rd.getAllReimbursements(request, response);
				}
				break;
			}
			default: {
				//send error if the api endpoint doesn't exist
				response.sendError(404, "The API endpoint you provided does not exist.");
				break;
			}
			}
		} else {
			// forward to correct page if it's a view request
			vd.resolveView(request, response);
		}
	}
	
	public void processUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String path = request.getRequestURI().substring(request.getContextPath().length());
		if(path.startsWith("/api/")) {
			String record = path.substring(5);
			switch(record) {
			case "employee":{
				if(request.getParameterMap().containsKey("bio") &&
						request.getParameterMap().containsKey("birthday") &&
						request.getParameterMap().containsKey("email") &&
						request.getParameterMap().containsKey("image")) {
					//update employee
					ed.updateEmployee(request, response);
				} else {
					response.sendError(400, "Data must be included in order to update profile.");
				}
				break;
			}
			case "reimbursement":{
				//update reimbursement
				if(request.getParameterMap().containsKey("id") && request.getParameterMap().containsKey("status")) {
					rd.updateReimbursement(request, response);
				} else {
					response.sendError(400, "Reimbursement Id is required for this update.");
				}
				break;
			}
			default:{
				//send error if the api endpoint doesn't exist
				response.sendError(404, "The endpoint your provided does not exist.");
				break;
			}
			}
		} else {
			//send invalid request error if request was sent to non api directory
			response.sendError(400, "You cannot update data in this directory.");
		}
	}

	public void processPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		if(path.startsWith("/api/")) {
			String record = path.substring(5);
			switch(record) {
			case "reimbursement":{
				if(request.getParameterMap().containsKey("email") &&
						request.getParameterMap().containsKey("details") && 
						request.getParameterMap().containsKey("amount")) {
					rd.createReimbursement(request, response);
				} else {
					response.sendError(400, "Data must be included in order to create reimbursement.");
				}
				break;
			}
			default:{
				response.sendError(404, "The endpoint you have provided doesn't exist.");
				break;
			}
			}
		}
	}
}
