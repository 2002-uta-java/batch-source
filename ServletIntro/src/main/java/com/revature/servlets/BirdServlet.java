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
import com.revature.service.BirdService;

public class BirdServlet extends HttpServlet {

	/**
	 * default from eclipse
	 */
	private static final long serialVersionUID = 1L;

	private final BirdService birdService = new BirdService();
	private final ObjectMapper oMapper = new ObjectMapper();

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException, ServletException {
		final String breedParam = request.getParameter("breed");

		List<Bird> birdList = null;
		if (breedParam != null) {
			System.out.println("get request to /birds with breed param of " + breedParam);
			birdList = birdService.getBirdsByBreed(breedParam);
		} else {
			System.out.println("get request to /birds with no param");
			birdList = birdService.getBirds();
		}

		final String birdJson = oMapper.writeValueAsString(birdList);

		try (PrintWriter pw = response.getWriter()) {
			pw.write(birdJson);
		}
	}
}
