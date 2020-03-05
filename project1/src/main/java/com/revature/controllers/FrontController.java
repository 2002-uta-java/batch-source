package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import com.revature.delegates.AuthDelegate;
import com.revature.delegates.ReimbursementDelegate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger("ERS");
	private AuthDelegate authDelegate = new AuthDelegate();
	private ReimbursementDelegate rDelegate = new ReimbursementDelegate();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() {
		super();
		// TODO Auto-generated constructor stub
		logger.debug("FrontController constructed");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//logger.info("Served at: " + request.getContextPath() + " with " + request.getServletPath());
		//logger.error("logged in: " + (Boolean) (request.getSession().getAttribute("loggedIn")));
		//logger.warn("username: " + request.getSession().getAttribute("username"));
		//logger.info("manager?: " + (Boolean) request.getSession().getAttribute("manager"));
		String path = request.getServletPath();

		switch (path) {
		case "/employee":
			request.getRequestDispatcher("/static/employee.html").forward(request, response);
			break;
		case "/manager":
			request.getRequestDispatcher("/static/manager.html").forward(request, response);
			break;

		case "/reimbursements":
			request.getRequestDispatcher("/static/reimbursements.html").forward(request, response);
			break;
		case "/R":
			rDelegate.listReimbursements(request, response);
			break;
		case "/logout":
			authDelegate.logout(request, response);
			break;
		case "/login":
			request.getRequestDispatcher("/static/login.html").forward(request, response);
			break;
		case "":
			Boolean loggedin = (Boolean) (request.getSession().getAttribute("loggedIn"));
			if(loggedin != null && loggedin) {
				if ((Boolean)request.getSession().getAttribute("manager")) {
					response.sendRedirect(request.getContextPath() + "/manager");
				} else {
					response.sendRedirect(request.getContextPath() + "/employee");
				}
			} else {
				response.sendRedirect(request.getContextPath() + "/login");
			}
			break;
		default:
			response.sendError(404);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		switch (path) {
		case "/login":
			authDelegate.authenticate(request, response);
			logger.info("/login page posted to");
			// HttpSession s = request.getSession();
			// s.setAttribute("username", request.getParameter("username"));

			break;
		default:
			response.sendError(405);
		}
	}

	public void init(ServletConfig config) throws ServletException {
		InitialContext cxt = null;
		try {
			cxt = new InitialContext();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		if (cxt == null) {
			throw new ServletException("Uh oh -- no context!");
		}

		BasicDataSource ds = null;
		try {
			ds = (BasicDataSource) cxt.lookup("java:/comp/env/jdbc/postgres");
		} catch (NamingException e) {
			e.printStackTrace();
		}

		if (ds == null) {
			throw new ServletException("Data source not found!");
		}

		ServletContext sctx = config.getServletContext();
		InputStream resourceContent = sctx.getResourceAsStream("META-INF/password.pw");
		String r = new BufferedReader(new InputStreamReader(resourceContent)).lines()
				.collect(Collectors.joining());

		ds.setPassword(r);
		// ------
		logger.info("init");
	}
}
