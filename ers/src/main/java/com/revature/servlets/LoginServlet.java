package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String loginType = req.getParameter("loginType");
		String uName     = req.getParameter("uName");
		String password  = req.getParameter("password");
		
		String session = (String) req.getAttribute("uName");
		
		res.getWriter().write("Signed in");
		
		System.out.println("Recieved from Login Page:" + loginType + " and " + uName + " and " + password);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String loginType = req.getParameter("loginType");
		String uName     = req.getParameter("uName");
		String password  = req.getParameter("password");
		
		System.out.println("Recieved from Login Page:" + loginType + " and " + uName + " and " + password);
		
		HttpSession userSession = req.getSession();
		String verified = req.getParameter("uName");
		userSession.setAttribute("uName", verified);
		String session = (String) req.getAttribute("uName");
		
		res.getWriter().write("Signed in");
	}
}
