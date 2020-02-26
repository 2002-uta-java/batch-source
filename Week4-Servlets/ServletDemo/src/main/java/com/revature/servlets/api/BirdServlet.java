package com.revature.servlets.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Bird;
import com.revature.services.BirdService;

/**
 * Servlet implementation class BirdServlet
 */
public class BirdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private BirdService birdService = new BirdService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BirdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String breedParam = request.getParameter("breed");
		
		List<Bird> birdList;
		
		if(breedParam!=null) {
			System.out.println("get request to /birds with breed param of: "+ breedParam);
			birdList = birdService.getBirdsByBreed(breedParam);
			
		} else {
			System.out.println("get request to /birds with no param");
			birdList = birdService.getAllBirds();
					
		}
		
		ObjectMapper om = new ObjectMapper();
		String birdJson = om.writeValueAsString(birdList);
		try(PrintWriter pw = response.getWriter();){
			pw.write(birdJson);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try(BufferedReader requestReader = request.getReader();){
			String newBirdJson = requestReader.readLine();
			ObjectMapper om = new ObjectMapper();
			Bird b = om.readValue(newBirdJson, Bird.class);
			birdService.createBird(b);
			response.setStatus(201);
		}
		
	}

}
