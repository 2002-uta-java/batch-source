package xyz.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;

public class FrontController extends DefaultServlet {
	private static final long serialVersionUID = 1L;
       
	private RequestHelper requestHelper = new RequestHelper();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();

    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if(path.startsWith("/static/")) {
			super.doGet(request, response);
		} else {
			requestHelper.processGet(request, response);
		}
		
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestHelper.processPost(request, response);
	}

}