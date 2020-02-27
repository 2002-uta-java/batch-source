package com.revature.delegates;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.daos.UserDao;
import com.revature.daos.UserDaoImpl;
import com.revature.models.User;

public class AuthDelegate {

	private final UserDao uDao = new UserDaoImpl();

	public AuthDelegate() {
		super();
	}

	public void authenticate(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		final String username = request.getParameter("username");
		final String password = request.getParameter("password");

		System.out.println("username: " + username);
		System.out.println("password: " + password);

		final User user = uDao.getUserByUsernameAndPassword(username, password);

		if (user != null) {
			// send back a token
			final String token = user.getId() + ":" + user.getUserRole();
			response.setStatus(200);
			response.setHeader("Authorization", token);
		} else {
			response.sendError(401);
		}
	}
}
