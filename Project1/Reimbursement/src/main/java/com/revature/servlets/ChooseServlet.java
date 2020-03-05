package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.daos.ManagerDao;
import com.daos.ManagerDaoImpl;
import com.models.Manager;

public class ChooseServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	 public ChooseServlet() {
	        super();
	    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("username") != null) {
				request.getRequestDispatcher("Views/Choose.html").forward(request, response);
			} else {
				response.sendRedirect("login");
			}
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession(false);
			String emp = request.getParameter("employee");
			String man = request.getParameter("manager");
			int id = Integer.parseInt(session.getAttribute("id").toString());
			ManagerDao mdi = new ManagerDaoImpl();
			Manager m = mdi.getManagerById(id);

			if (emp != null) {
				response.sendRedirect("empprofile?id=" +id);
				return;
			} else if (man != null) {
				if (m.getId() != 0) {
					response.sendRedirect("manprofile?id=" +id);
					return;
				}
//				System.out.println("Not a manager");
			}
			doGet(request, response);
		}
}

