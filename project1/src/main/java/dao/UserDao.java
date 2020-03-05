package dao;

import java.util.List;

import model.User;

public interface UserDao {
	
	public User getUserByUserNameAndPassword(String n,String p);
	public User getUserByUserID(int i);
	public User getManager(String n,String p);
	public List<User> getManagers();
	public List<User> getUsers();

}
