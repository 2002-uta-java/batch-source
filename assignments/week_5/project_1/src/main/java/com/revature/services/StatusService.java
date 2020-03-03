package com.revature.services;

import java.util.List;

import com.revature.dao.StatusDao;
import com.revature.dao.StatusDaoImpl;
import com.revature.models.Status;

public class StatusService {
	
	private StatusDao sd = new StatusDaoImpl();
	
	public List<Status> getAllStatus() {
		return sd.getAllStatus();
		
	}

}
