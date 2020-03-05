package com.revature.services;

import java.util.List;

import com.revature.daos.RequestDao;
import com.revature.daos.RequestDaoImpl;
import com.revature.models.Request;

public class RemReqService {
	private RequestDao remS = new RequestDaoImpl();

	public Request getRequestById(int id) {
		return remS.getRequestById(id);
	}

	public List<Request> getAllRequets() {
		return remS.getAllRequets();
	}

	public List<Request> getRequestByEmployeeName(String empName) {
		return remS.getRequestByEmployeeName(empName);
	}

	public List<Request> getRequestByStatus(boolean status) {
		return remS.getRequestByStatus(status);
	}

	public List<Request> getRequestByStatusAndEmployeeName(boolean status, String empName) {
		return remS.getRequestByStatusAndEmployeeName(status, empName);
	}

	public int updateRequest(boolean status, String aprovedBy, int reqNum) {
		return remS.updateRequest(status, aprovedBy, reqNum);
	}

	public int makeRequest(Request request) {
		return remS.makeRequest(request);
	}
}
