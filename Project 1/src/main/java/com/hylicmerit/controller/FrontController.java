package com.hylicmerit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;

public class FrontController extends DefaultServlet {
	private static final long serialVersionUID = 1L;
	private RequestHelper rh = new RequestHelper();
     
    public FrontController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		if(path.startsWith("/static/")) {
			super.doGet(request, response);
		} else {
			//send GET request to the appropriate delegate (requestHelper)
			rh.processGet(request, response);
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		rh.processUpdate(request, response);
	}

}
