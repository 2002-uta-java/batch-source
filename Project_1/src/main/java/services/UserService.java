package services;

import daos.UserDao;
import daos.UserDaoImp;
import models.*;
import java.util.List;

public class UserService {
	UserDao uDao = new UserDaoImp();
	
	
	
	public UserService() {
		super();
	}

	public void deleteAccount(User u) {
		uDao.deleteUser(u);
		
	};
	
	public User login(String un, String pw) {
		return uDao.getUserbyCred(un, pw);
		
	};
	
	public List<User> getSubordinates(long uid) {
		return uDao.getUsersBySuper(uid);
	}
	

}
