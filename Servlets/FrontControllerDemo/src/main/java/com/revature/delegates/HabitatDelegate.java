package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Habitat;
import com.revature.services.HabitatService;

public class HabitatDelegate {
	
	private HabitatService habitatService = new HabitatService();
	
	public void getAllHabitats(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Habitat> habitats = habitatService.getAllHabitats();
		try(PrintWriter pw = response.getWriter()){
			pw.write(new ObjectMapper().writeValueAsString(habitats));
		}
		
	}

}