package com.revature.servlets.api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Bird;
import com.revature.services.BirdService;

/**
 * Servlet implementation class BirdIdServlet
 */
public class BirdIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BirdService birdService = new BirdService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BirdIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		String pathVariable = uri.substring(request.getContextPath().length()+7);
		System.out.println(pathVariable);
		if(pathVariable.matches("^\\d+$")) {
			Bird bird = birdService.getBirdById(Integer.parseInt(pathVariable));
			if(bird==null) {
				response.sendError(404, "No Bird with Given ID");
			} else {
				ObjectMapper om = new ObjectMapper();
				response.getWriter().write(om.writeValueAsString(bird));
			}
		} else {
			response.sendError(400, "Unsupported Path Variable");
		}
	}


}