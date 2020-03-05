package com.dean.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dean.managers.LogoutAccountManager;

public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
    public LogoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LogoutAccountManager lam = new LogoutAccountManager();
		lam.endUserSession(request);
		response.sendRedirect("login");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
