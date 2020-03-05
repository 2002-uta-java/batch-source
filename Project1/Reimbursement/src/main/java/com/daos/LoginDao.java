package com.daos;

import java.util.List;

import com.models.Login;

public interface LoginDao {
	public Login getLoginByUser(String user);
	public List<Login> getLogins();
}
