package com.revature.services;

import java.util.List;
import com.revature.daos.ManagerDao;
import com.revature.daos.ManagerDaoImpl;
import com.revature.models.Manager;

/**
 * service layer for our manager
 * @author erpac
 *
 */
public class ManagerService {
	ManagerDao managerDao = new ManagerDaoImpl();
	
	public Manager getMangerByName(String name) {
		return managerDao.getMangerByName(name);
	}
	public Manager getManagerByRequest(String reqeustId) {
		return managerDao.getManagerByRequest(reqeustId);
	}
	public Manager getManagerNameAndPw(String name, String password) {
		return managerDao.getManagerNameAndPassword(name, password);
	}
	public List<Manager> getManagers(){
		return managerDao.getAllManagers();
	}
}
