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
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException {
		String path = req.getServletPath();
		if(path.startsWith("/static/")) {
			super.doGet(req, resp);
		} else {
			helper.delegateTask(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		super.doPost(request, response);
	}
	
}
