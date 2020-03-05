package com.project1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.project1.delegates.AuthDelegate;
import com.project1.delegates.EmployeeDelegate;
import com.project1.delegates.ReimbursementDelegate;
import com.project1.delegates.ViewDelegate;

public class RequestHelper {
	/*
	 *	/homepage -> employee homepage
	 *
	 *	/homepage -> manager homepage
	 *
	 *	/api/profile -> returns employee information
	 *
	 *	/api/pending -> returns employee pending reimbursement
	 *
	 *	/api/resolved -> returns employee resolved reimbursement
	 *
	 *	/api/profile -> returns employee information
	 *	
	 *	/api/request -> manager approve/deny employee reimbursement
	 *
	 *	/api/all-pending -> manager can view all pending reimbursement
	 *
	 *	/api/all-resolved -> manager can view all resolved reimbursement
	 *
	 *	/api/all-employees -> returns all employee
	 *
	 *	/api/id=? -> returns all employee reimbursement with that id
	 */
	private static final Logger LOGGER = LogManager.getLogger(RequestHelper.class.getName());
	private EmployeeDelegate empDelegate = new EmployeeDelegate();
	private ViewDelegate viewDelegate = new ViewDelegate();
	private AuthDelegate authDelegate = new AuthDelegate();
	private ReimbursementDelegate reimbDelegate = new ReimbursementDelegate();
	
	public void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		if(path.startsWith("/api/")) {
			//evaluate the record in the url and send req/resp to a resource based delegate
			LOGGER.info("in processGet, handle /api/ request");
			String record = path.substring(5);
			switch(record) {
			case "all-employees":
				// process request with all employee delegate
				LOGGER.info("call get all employees");
				empDelegate.getAllEmployees(request, response);
				break;
			case "reimbursement-pending":
				LOGGER.info("call get pending reimbursement");
				reimbDelegate.getPendingReimb(request, response);
				break;
			case "reimbursement-resolve":
				LOGGER.info("call get resolve reimbursement");
				reimbDelegate.getResolveReimb(request, response);
				break;
			case "manager-reimbursement-pending":
				reimbDelegate.getEmpPendingReimb(request, response);
				break;
			case "manager-reimbursement-resolve":
				LOGGER.info("call get employee resolve reimbursement");
				reimbDelegate.getEmpResolveReimb(request, response);
				break;
			case "profile":
				LOGGER.info("call get employee by employee id");
				empDelegate.getEmployeeById(request, response);
				break;
			default:
				LOGGER.info("error 404, record not supported");
				response.sendError(404, "Record not supported");
			}
			
		} else {
			//requesting a view
			LOGGER.info("call resolve view");
			viewDelegate.resolveView(request, response);
		}
	}
	
	public void processPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		if(path.startsWith("/api/")) {
			LOGGER.info("in processPost, handle /api/ request");
			//evaluate the record in the url and send req/resp to a resource based delegate
			String record = path.substring(5);
			switch(record) {
			case "login":
				LOGGER.info("call authenticate");
				authDelegate.authenticate(request, response);
				break;
			case "submit-reimbursement":
				LOGGER.info("call reimbursement delegate to create reimbursement");
				reimbDelegate.createReimb(request, response);
				break;
			default:
				LOGGER.info("error 405");
				response.sendError(405);
			}
		} 
	}
	
	public void processPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		if(path.startsWith("/api/")) {
			//evaluate the record in the url and send req/resp to a resource based delegate
			LOGGER.info("in processPut, handle /api/ request");
			String record = path.substring(5);
			switch(record) {
			case "edit-profile":
				LOGGER.info("call update Empoyee to update");
				empDelegate.updateEmployee(request, response);
				break;
			case "edit-reimbursement":
				LOGGER.info("call update reimbursement to update");
				reimbDelegate.updateReimb(request, response);
				break;
			default:
				LOGGER.info("error 404");
				response.sendError(404, "Record not supported");
			}
			
		}
	}
}
