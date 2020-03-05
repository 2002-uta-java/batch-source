package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.delegate.EmployeeDelegate;
import com.revature.delegate.ReimbursementDelegate;
import com.revature.delegate.UserDelegate;
import com.revature.delegate.ViewDelegate;

public class RequestHelper {

	private EmployeeDelegate employeeDelegate = new EmployeeDelegate();
	private ViewDelegate viewDelegate = new ViewDelegate();
	private UserDelegate userDelegate = new UserDelegate();
	private ReimbursementDelegate reimbursementDelegate = new ReimbursementDelegate();
	
	
	public void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		if(path.startsWith("/api/")) {
			String record = path.substring(5);
			switch(record) {
			case "employeeall":
				employeeDelegate.getAllEmployees(request, response);
				break;
			case "employeeid":
				employeeDelegate.getEmployeeId(request, response);
				break;
			case "employeemanagerid":
				employeeDelegate.getManagerId(request, response);
				break;
			case "employeereportsto":
				employeeDelegate.getReportsTo(request, response);
				break;
			case "employeeemail" :
				employeeDelegate.getEmail(request, response);
				break;
			case "reimbursementall":
				reimbursementDelegate.getAllReimbursement(request, response);
				break;
			case "reimbursementstatus":
				reimbursementDelegate.getStatus(request, response);
				break;
			case "messageupdate":
				reimbursementDelegate.getMessage(request, response);
				break;
			case "reimbursementreciept":
				reimbursementDelegate.getReciept(request, response);
				break;
			default:
				response.sendError(404, "Record not supported");
			}
			
		} else {
			viewDelegate.resolveView(request, response);
		}
	}
	
	public void processPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = request.getServletPath();
		switch(path) {
		case "/index":
			break;
		case "/employeenew":
			employeeDelegate.createEmployee(request, response);
			break;
		case "/reimbursementnew":
			reimbursementDelegate.createReimbursement(request, response);
			break;
		case "/logon":
			userDelegate.clientAuth(request, response);
			break;
		case "/clientnew":
			userDelegate.clientCreate(request, response);
			break;
		case "/reimbursementid":
			reimbursementDelegate.getReimbursementId(request, response);
			break;
		case "/employeeupdate":
			employeeDelegate.updateEmployee(request, response);
			break;
		case "/reimbursementupdate":
			reimbursementDelegate.updateReimbursement(request, response);
			break;
		case "/clientupdate":
			userDelegate.updateClient(request, response);
			break;
		case "/employeeById":
			employeeDelegate.employeeById(request, response);
			break;
		case "/reimbursementByType":
			reimbursementDelegate.remByType(request, response);
			break;
		case "/employeeUpdateFname":
			employeeDelegate.updateFName(request, response);
			break;
		case "/employeeUpdateLname":
			employeeDelegate.updateLName(request, response);
			break;
		case "/employeeUpdateTitle":
			employeeDelegate.updateTitle(request, response);
			break;
		default:
			response.sendError(405);
		}
	}
	
	public void processDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = request.getServletPath();
		switch(path) {
		case "/employeedel":
			employeeDelegate.deleteEmployee(request, response);
			break;
		case "/reimbursementdel":
			reimbursementDelegate.deleteReimbursement(request, response);
			break;
		default:
			response.sendError(405);
		}
	}
}
