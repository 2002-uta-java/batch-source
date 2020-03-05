package com.dean.managers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutAccountManager {

	public void endUserSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			session.invalidate();
		}
	}
}
