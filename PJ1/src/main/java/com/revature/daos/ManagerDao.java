package com.revature.daos;

import java.util.List;

import com.revature.models.Manager;

public interface ManagerDao {
	public Manager getMangerByName(String name);
	public Manager getManagerByRequest(String reqeustId);
	public Manager getManagerNameAndPassword(String name, String password);
	public List<Manager> getAllManagers();
}
