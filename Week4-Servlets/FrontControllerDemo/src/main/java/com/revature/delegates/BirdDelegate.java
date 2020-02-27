package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Bird;
import com.revature.services.BirdService;

public class BirdDelegate {
	
	private BirdService birdService = new BirdService();
	
	public void getAllBirds(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Bird> birds = birdService.getAllBirds();
		
		try(PrintWriter pw = response.getWriter()){
			pw.write(new ObjectMapper().writeValueAsString(birds));
		}
	}

}
