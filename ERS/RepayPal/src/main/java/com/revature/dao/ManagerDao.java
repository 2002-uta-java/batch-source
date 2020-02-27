package com.revature.dao;

import java.util.List;

import com.revature.model.Manager;

public interface ManagerDao {

	public List<Manager> getManagers();
	public Manager getManagerByUsername(String username);
	public int createManager(Manager m);
	public int updateManager(Manager m);
	public int deleteManager(Manager m);
}
