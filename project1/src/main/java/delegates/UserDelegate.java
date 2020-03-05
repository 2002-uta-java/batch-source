package delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.UserDao;
import dao.UserDaoImpl;
import model.User;

public class UserDelegate {

	private dao.UserDao userDao = new UserDaoImpl();

	public void getUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String requestPath = request.getServletPath();
		System.out.println(requestPath);
		if (requestPath.length() == "/api/users".length()) {
			List<User> users = userDao.getUsers();
			try (PrintWriter pw = response.getWriter();) {
				pw.write(new ObjectMapper().writeValueAsString(users));
			}
		} else {
			String idStr = request.getServletPath().substring(11);
			System.out.println(idStr);
			if (idStr.matches("^\\d+$")) {
				User u = userDao.getUserByUserID(Integer.parseInt(idStr));
				if (u == null) {
					response.sendError(404, "No user with given ID");
				} else {
					try (PrintWriter pw = response.getWriter()) {
						pw.write(new ObjectMapper().writeValueAsString(u));
					}
				}
			} else {
				response.sendError(400, "Invalid ID param");
			}
		}
	}

}