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
		//User u = uDao.getUserbyCred(req.getParameter("userName"), req.getParameter("password"));
		String username =  req.getParameter("username");
		String password =  req.getParameter("password");
//		System.out.println("AUTHENTICATE"+username+" "+password);
		User u = uDao.getUserbyCred(username, password);
		
		if (u != null) {
			String token = u.getUid()+":"+u.getRole();
			rep.setStatus(200);
			rep.setHeader("Authorization", token);
		} else {
			rep.sendError(401,"User account not found");
		}
		
	}
	
	public boolean authorized(HttpServletRequest req) {
		String authentoken = req.getHeader("Authorization");
		
		if (authentoken !=null) {
			String[] tokenArr = authentoken.split(":");
			if(tokenArr.length==2) {
				String idStr = tokenArr[0];
				String roleStr = tokenArr[1];
				
				if (idStr.matches("^\\d+$")) {
					User u = uDao.getUserByID(Integer.parseInt(idStr));
					if(u!=null&&u.getRole().contentEquals(roleStr)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
}
