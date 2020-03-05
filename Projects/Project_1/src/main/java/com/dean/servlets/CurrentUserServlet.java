package com.dean.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CurrentUserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public CurrentUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		response.setContentType("application/json"); 
		PrintWriter pw = response.getWriter();
		
		if (session != null) {
			String username = (String) session.getAttribute("username");
			int isManager = (Integer) session.getAttribute("isManager");
			int id = (Integer) session.getAttribute("id");
			String name = (String) session.getAttribute("name");
			String position = (String) session.getAttribute("position");
			String json = "{\"username\": \"" + username;
			json += "\", \"" + "isManager\": \"" + isManager;
			json += "\", \"" + "id\": \"" + id;
			json += "\", \"" + "name\": \"" + name;
			json += "\", \"" + "position\": \"" + position + "\"}";
			pw.write(json);
		} else {
			pw.write("N/A");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
