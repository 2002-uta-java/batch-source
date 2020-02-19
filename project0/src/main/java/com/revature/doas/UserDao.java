package com.revature.doas;



import com.revature.models.User;

public interface UserDao {
	public int createUser(User user); /// will call the create account method. every new user has to make and account.
	public String generateAccountNumber(int len);// will called by the userUI class
}