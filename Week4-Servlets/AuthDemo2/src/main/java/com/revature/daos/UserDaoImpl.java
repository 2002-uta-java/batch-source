package com.revature.daos;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;

public class UserDaoImpl implements UserDao {
	
	private List<User> users = new ArrayList<>();
	
	public UserDaoImpl() {
		super();
		users.add(new User(1, "sallyjenkins", "passwort2500", "general"));
		users.add(new User(2, "jenkinssally","2500wortpass","admin"));
		users.add(new User(3, "jallysenkins", "1234", "general"));
		users.add(new User(4, "jellysankins", "wombatsrock", "general"));
	}

	@Override
	public List<User> getAll() {
		return new ArrayList<>(users);
	}

	@Override
	public User getById(int id) {
		for(User u: users) {
			if(u.getId() == id) {
				return u;
			}
		}
		return null;
	}

	@Override
	public User getUserByUsernameAndPassword(String username, String password) {
		for(User u: users) {
			if(u.getUsername() != null && u.getUsername().equals(username)) {
				if(u.getPassword() != null && u.getPassword().equals(password)) {
					return u;
				}
			}
		}
		return null;
	}
	
	

}
