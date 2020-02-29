package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.servlets.LoginServlet;
import com.revature.servlets.ViewServlet;

public class RequestHelper {
	
	private LoginServlet login = new LoginServlet();
	private ViewServlet view = new ViewServlet();
	public void processGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.startsWith("/static/")) {
			if (!login.isAuthorized(req, res)) {
				res.sendError(401);
				return;
			} else
				view.resolveView(req, res);
		}
	}
	
	public void processPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case "/login":
			login.authenticate(req, res);
			break;
		default:
			res.sendError(405);
		}
	}
}
