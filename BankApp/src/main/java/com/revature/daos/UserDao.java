package com.revature.daos;

import java.sql.SQLException;

public interface UserDao {

	public void createUser() throws SQLException;
	public void login() throws SQLException;
	public void deposit() throws SQLException;
}
