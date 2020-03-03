package com.revature.services;

import com.revature.dao.ProfileDao;
import com.revature.dao.ProfileDaoImpl;
import com.revature.models.Profile;

public class ProfileService {
	
	private ProfileDao pd = new ProfileDaoImpl();
	
	// call dao to create a user profile in the database. Returns new user profile id (int)
	public int createProfile(Profile p) {
		return pd.createProfile(p);
	}
	
	// call dao to validate user log in information being passed in. Returns Profile object with id and is_manager set
	public Profile validateProfile(Profile p) {
		return pd.validateProfile(p);
	}
	
//	public int updateProfile(Profile p) {
//		return pd.updateProfile(p);
//	}

}
