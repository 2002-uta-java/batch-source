package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.daos.LoginDao;
import com.daos.LoginDaoImpl;
import com.models.Login;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("Views/LoginPage.html").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		LoginDao ldi = new LoginDaoImpl();
		Login l = ldi.getLoginByUser(user);

		System.out.println(user + " " + pass);
		
		// Create a new session
		HttpSession session = request.getSession();
		
		if (user.equals(l.getUser()) && pass.equals(l.getPwsd())) {
			session.setAttribute("username", user);
			session.setAttribute("id", l.getId());
			response.sendRedirect("choose?id=" + l.getId());
		} else {
			response.sendRedirect("loginfail");
		}
	}
}
