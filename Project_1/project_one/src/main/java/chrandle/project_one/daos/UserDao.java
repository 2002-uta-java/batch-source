package chrandle.project_one.daos;
import java.util.List;

import chrandle.project_one.models.User;

public interface UserDao {
	public User createUser();
	public int deleteUSer(User U);
	public int updateUser(User u);
	public User getUserByID(long uid);
	public List<User> getUsersBySuper();
	public User getUserbyCred(String uname, String pw);
	
}
