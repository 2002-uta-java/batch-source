package com.dean.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dean.managers.LoginAccountManager;
import com.dean.managers.SessionManager;

public class VerifyLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    LoginAccountManager accountManager = new LoginAccountManager();
    
    public VerifyLoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			String endpoint = accountManager.determineAccountType(request);
			
			if (endpoint.equals("login")) {
				response.sendRedirect(endpoint);
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("username", request.getParameter("username"));
				String username = (String) session.getAttribute("username");
				SessionManager sessionManager = new SessionManager();
				sessionManager.createSessionForManagerAccounts(username, session);
				response.sendRedirect(endpoint);
				System.out.println(username);
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
