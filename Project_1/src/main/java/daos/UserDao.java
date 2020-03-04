package daos;
import java.util.List;

import models.User;

public interface UserDao {
	public long createUser(String firstname, String lastname,
			String username, String email, String password);
	public int deleteUser(User U);
	public int updateUser(User u, String oldname, String oldpw);
	public User getUserByID(long uid);
	public List<User> getUsersBySuper(long uid);
	public User getUserbyCred(String uname, String pw);
	
}
