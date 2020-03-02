package com.revature.project1.daos;

import java.util.List;

import com.revature.project1.models.Appuser;

public interface AppuserDao {
	
	public List<Appuser> getAppuser();
	public Appuser getAppuserByEmail(String email);
	public Appuser getAppuserByUnameAndPass(String email, String pass);
	public int updateAppuser(Appuser a);
	
}
