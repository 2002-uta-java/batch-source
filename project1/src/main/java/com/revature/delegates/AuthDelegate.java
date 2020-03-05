package com.revature.delegates;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.services.UserService;

public class AuthDelegate {
	private static final Logger logger = LogManager.getLogger("ERS");
	private UserService userService = new UserService();
	
	public void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//System.out.println(username + " " + password);
		if(userService.checkPassword(username, password)) {
			boolean isManager = userService.isManager(username);
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("loggedIn", true);
			request.getSession().setAttribute("manager", isManager);
			//System.out.println("going to redreict");
			if(isManager) {
				response.sendRedirect(request.getContextPath() + "/manager");
			} else {
			response.sendRedirect(request.getContextPath() + "/employee");
			}
			//System.out.println("redirected");
		} else {
			response.sendError(401);
		}
	}

	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession s = request.getSession();
		s.removeAttribute("username");
		s.removeAttribute("loggedIn");
		logger.info("logged out");
		response.sendRedirect(request.getContextPath() + "/login");
	}

}
