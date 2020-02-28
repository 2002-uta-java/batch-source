package com.hylicmerit.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewDelegate {
	
	public void resolveView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getServletPath();
		System.out.println(uri);
		switch(uri) {
		case "/login":{
			request.getRequestDispatcher("static/views/Login.html").forward(request, response);
			break;
		}
		case "/home":{
			request.getRequestDispatcher("static/views/Home.html").forward(request, response);
			break;
		}
		default:{
			response.sendError(404, "The static resource you requested does not exist.");
			break;
		}
		}
	}
}
