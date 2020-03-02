package com.revature.project1.services;

import java.util.List;

import com.revature.project1.daos.AppuserDao;
import com.revature.project1.daos.AppuserDaoImpl;
import com.revature.project1.models.Appuser;

public class AppuserService {
	
	private AppuserDao appuserDao = new AppuserDaoImpl();
	
	public List<Appuser> getAppuser(){
		return appuserDao.getAppuser();
	}
	
	public Appuser getAppuserByEmail(String email) {
		return appuserDao.getAppuserByEmail(email);
	}
	
	public boolean updateAppuser(Appuser a) {
		int appusersUpdated = appuserDao.updateAppuser(a);
		if (appusersUpdated != 0) {
			return true;
		}
		return false;
	}

}
