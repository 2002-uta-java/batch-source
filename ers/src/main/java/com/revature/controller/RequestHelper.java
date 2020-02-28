package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RequestHelper {
	
	/* 
	 *    /new -> returns NewBird.html view
	 *    /directory -> returns BirdDirectory.html
	 *    /api/birds -> returns bird data
	 *    /api/habitats -> returns habitat data
	 */
	
//	private ViewDelegate viewDelegate = new ViewDelegate();
//	private HabitatDelegate habitatDelegate = new HabitatDelegate();
//	private BirdDelegate birdDelegate = new BirdDelegate();
	
	public void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		if(path.startsWith("/api/")) {
			//evaluate the record in the url and send req/resp to a resource based delegate
			String record = path.substring(5);
			switch(record) {
			case "birds":
				// process request with bird delegate
//				birdDelegate.getAllBirds(request, response);
				break;
			case "habitats":
				// process request with habitat delegate
//				habitatDelegate.getAllHabitats(request, response);
				break;
			default:
				response.sendError(404, "Record not supported");
			}
			
		} else {
			//requesting a view
//			viewDelegate.resolveView(request, response);
		}
	}

}
