package com.revature.service;

import java.util.List;

import com.revature.dao.ManagerDao;
import com.revature.dao.ManagerDaoImplementation;
import com.revature.model.Manager;

public class ManagerService {

private ManagerDao managerDao = new ManagerDaoImplementation();
	
	public Manager getUserByUsername(String username) {
		return managerDao.getManagerByUsername(username);
	}

	public Manager validateUser(String username, String password) {
		Manager manager = managerDao.getManagerByUsername(username);
		if(manager != null && !(password.equals("")||password.equals(" "))) {
			if(password.equals(manager.getPwd()))
				return manager;
			else {
				System.out.println("\nInvalid username or password.\n");
				return null;
			}
		}
		System.out.println("Username not found\n");
		return null;
	}
	
	
	public boolean createUser(Manager m) {
		if(m == null)
			return false;
		List<Manager> managers = managerDao.getManager();
		for(Manager manager: managers) {
			if(m.getUsername().equals(manager.getUsername()) && m.getPwd().equals(manager.getPwd())) {
				System.out.println("User already exists, please sign in...");
				return false;
			}	
		}
		int userCreated = managerDao.createManager(m);
		if(userCreated != 0) {
			return true;
		}
		return false;
	}
	
	public boolean updateUser(Manager m) {
		int userUpdated = managerDao.updateManager(m);
		if(userUpdated != 0) {
			return true;
		}
		return false;
	}

	public boolean deleteUser(Manager m) {
		int deleteCreated = managerDao.deleteManager(m);
		if(deleteCreated != 0) {
			return true;
		}
		return false;
	}
}
