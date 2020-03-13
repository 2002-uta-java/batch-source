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
	final HabitatService habitatService = new HabitatService();
	final ObjectMapper oMapper = new ObjectMapper();

	public void getAllHabitats(final HttpServletRequest request, HttpServletResponse response) throws IOException {
		final List<Habitat> habitats = habitatService.getAllHabitats();

		try (final PrintWriter pw = response.getWriter()) {
			pw.write(oMapper.writeValueAsString(habitats));
		}
	}
}
