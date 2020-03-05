package delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import daos.UserDaoImp;
import daos.UserDao;
import models.User;

public class UserDelegate {
	private UserDao uDao = new UserDaoImp();

	public void getUserProfile(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException {
		String path = req.getServletPath().substring(5);
		System.out.println("	user profile path: "+path);
		if(path.equals("userProfile")) {
			
			String authentoken = req.getParameter("token");
			System.out.println(authentoken);
			String[] myArr = authentoken.split(":");
			long uid = Long.parseLong(myArr[0]);
			User u = uDao.getUserByID(uid);
			if (u==null) {
				resp.sendError(404, "No user profile given");
			}else {
				try(PrintWriter printer = resp.getWriter();){
					printer.write(new ObjectMapper().writeValueAsString(u));
				}
			}
			
		}else {
			
		resp.sendError(400, "Invalid ID jvkjvhkvhk");

		}
	}
}




