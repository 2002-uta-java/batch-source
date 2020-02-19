package com.hylicmerit.service;

import org.apache.commons.validator.routines.EmailValidator;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import com.hylicmerit.dao.UserDao;
import com.hylicmerit.dao.UserDaoImpl;
import com.hylicmerit.models.User;

public class UserService {
	private UserDao userDao = new UserDaoImpl();

	public User getUserById(String username) {
		return userDao.getUserById(username);
	}
	
	public boolean checkUsernameAvailability(String username) {
		if(!"".equals(username)) {
			if(userDao.checkUsernameAvailability(username) == 0) {
				return true;
			}
			else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean createUser(User u) {
		if(u != null) {
			if(userDao.createUser(u) !=0) {
				return true;
			}
			else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean updateUser(User u) {
		if(u != null) {
			if(userDao.updateUser(u) !=0) {
				return true;
			}
			else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean deleteUser(User u) {
		if(u != null) {
			if(userDao.deleteUser(u) !=0) {
				return true;
			}
			else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean validateEmail(String email) {
		if(EmailValidator.getInstance().isValid(email)) {
			return true;
		} else {
			return false;
		}
	}
	
	public String encryptPassword(String pw) {
		StandardPBEStringEncryptor passwordEncryptor = new StandardPBEStringEncryptor();
		passwordEncryptor.setPassword("some_salt");
		String password = new String(passwordEncryptor.encrypt(pw));
		return password;
	}
	
	public String decryptPassword(String pw) {
		StandardPBEStringEncryptor passwordEncryptor = new StandardPBEStringEncryptor();
		passwordEncryptor.setPassword("some_salt");
		String password = new String(passwordEncryptor.decrypt(pw));
		return password;
	}
}
