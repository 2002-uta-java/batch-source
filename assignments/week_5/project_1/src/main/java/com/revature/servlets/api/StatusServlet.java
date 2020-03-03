package com.revature.servlets.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Status;
import com.revature.services.StatusService;

/**
 * Servlet implementation class StatusServlet
 */
public class StatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StatusService ss = new StatusService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatusServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Status> staList = new ArrayList<>();
		staList = ss.getAllStatus();
		
		ObjectMapper om = new ObjectMapper();
		
		String staJson = om.writeValueAsString(staList);
		
		try (PrintWriter pw = response.getWriter();){
			pw.write(staJson);
			response.setStatus(200);
			
		}
		
	}

}
