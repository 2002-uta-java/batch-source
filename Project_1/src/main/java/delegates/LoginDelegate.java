package delegates;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import daos.UserDao;
import daos.UserDaoImp;
import models.User;

public class LoginDelegate {
	private UserDao uDao = new UserDaoImp();
	
	public void authenticate(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
		User u = uDao.getUserbyCred(req.getParameter("userName"), req.getParameter("password"));
		

		
		if (u != null) {
			String token = u.getUid()+":"+u.getRole();
			rep.setStatus(200);
			rep.setHeader("Authorization", token);
		} else {
			rep.sendError(401,"User account not found");
		}
		
	}
}
