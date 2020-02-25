package com.revature.servlets;

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

public class BirdServlet extends HttpServlet {
	
	private BirdService birdService = new BirdService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String breedParam = request.getParameter("breed");
		
		List<Bird> birdList;
		
		if(breedParam!=null) {
			System.out.println("get request to /birds with breed param of: "+ breedParam);
			birdList = birdService.getBirdsByBreed(breedParam);
			
		} else {
			System.out.println("get request to /birds with no param");
			birdList = birdService.getBirds();
					
		}
		
		ObjectMapper om = new ObjectMapper();
		String birdJson = om.writeValueAsString(birdList);
		try(PrintWriter pw = response.getWriter();){
			pw.write(birdJson);
		}
	}

}
