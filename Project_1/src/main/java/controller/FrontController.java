package controller;

import org.apache.catalina.servlets.DefaultServlet;
import javax.servlet.annotation.*;//specify web servlet?

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class FrontController extends DefaultServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	RequestHelper helper = new RequestHelper();
	
	public FrontController() {
		super();
	}
	

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException {
		String path = req.getServletPath();
		if(path.startsWith("/static/")) {
			super.doGet(req, resp);
		} else {
			helper.delegateGet(req, resp);
		}
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		helper.delegatePost(req, resp);
	}
	
}
