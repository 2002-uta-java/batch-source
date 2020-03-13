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

	private final BirdService birdService = new BirdService();
	private final ObjectMapper oMapper = new ObjectMapper();

	public void getAllBirds(HttpServletRequest request, HttpServletResponse response) throws IOException {
		final List<Bird> birds = birdService.getAllBirds();

		try (final PrintWriter pw = response.getWriter()) {
			pw.write(oMapper.writeValueAsString(birds));
		}
	}

}
