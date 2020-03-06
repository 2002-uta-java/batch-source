package daos;
import java.util.List;

import models.User;
import models.Reimbursement;

public interface UserDao {
	public long createUser(String firstname, String lastname,
			String username, String email, String password);
	public int deleteUser(User U);
	public int updateUser(User u, String oldname, String oldpw);
	public User getUserByID(long uid);
	public List<User> getUsersBySuper(long uid);
	public User getUserbyCred(String uname, String pw);
	public List<User> getUsers();
	public List<Reimbursement> getMyReimbursements(long uid);
	public List<Reimbursement> accountsOverseen(long uid);
	public long postRequest(long uid, long sid, long amm, String status);
	public long stampRequest(long uid, String status);
	
	
}
