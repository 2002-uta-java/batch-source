package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.delegates.BirdDelegate;
import com.revature.delegates.HabitatDelegate;
import com.revature.delegates.ViewDelegate;

public class RequestHelper {

	private final ViewDelegate viewDelegate = new ViewDelegate();
	private final HabitatDelegate habitatDelegate = new HabitatDelegate();
	private final BirdDelegate birdDelegate = new BirdDelegate();

	/**
	 * / new -> returns NewBird.html view /directory -> returns BirdDirectory.html
	 * 
	 * /api means I'm getting resources /api is /api/birds -> returns birds data
	 * /apit/habitats -> returns habitat data
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void processGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		// strip off context root (e.g. /fcdemo)
		final String path = request.getRequestURI().substring(request.getContextPath().length());

		if (path.startsWith("/api")) {
			final String record = path.substring("/api/".length());

			switch (record) {
			case "birds":
				// process birds
				birdDelegate.getAllBirds(request, response);
				break;
			case "habitats":
				// process habitats
				habitatDelegate.getAllHabitats(request, response);
				break;
			default:
				response.sendError(404, "Record not supported");
			}
		} else {
			// requesting a view
			viewDelegate.resolveView(request, response);
		}
	}
}
