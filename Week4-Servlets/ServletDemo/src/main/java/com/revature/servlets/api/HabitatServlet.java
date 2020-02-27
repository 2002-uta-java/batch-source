package com.revature.servlets.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Habitat;
import com.revature.services.HabitatService;

/**
 * Servlet implementation class HabitatServlet
 */
public class HabitatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private HabitatService habitatService = new HabitatService();
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HabitatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Habitat> habitats = habitatService.getAllHabitats();
		ObjectMapper om = new ObjectMapper();
		String habitatJson = om.writeValueAsString(habitats);
		response.getWriter().write(habitatJson);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
