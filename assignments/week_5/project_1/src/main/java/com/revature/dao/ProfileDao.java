package com.revature.dao;

import com.revature.models.Profile;

public interface ProfileDao {
	
	// create profile in database, returns new user profile id (int)
	public int createProfile(Profile p);
	
	// validate user log in input with database data, returns user profile id
	public Profile validateProfile(Profile p);
	
//	public int updateProfile(Profile p);

}
