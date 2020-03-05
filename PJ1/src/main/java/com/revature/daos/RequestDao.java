package com.revature.daos;

import java.util.List;

import com.revature.models.Request;

public interface RequestDao {
	public Request getRequestById(int id);
	public List <Request> getAllRequets();
	public List <Request> getRequestByEmployeeName(String empName);
	public List <Request> getRequestByStatus(boolean status);
	public List <Request> getRequestByStatusAndEmployeeName(boolean status, String empName);
	public int updateRequest(boolean status,String aprovedBy, int reqNum );
	public int makeRequest(Request request);
	
	
}
