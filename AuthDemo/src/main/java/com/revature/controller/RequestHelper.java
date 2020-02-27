package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.delegates.AuthDelegate;
import com.revature.delegates.ViewDelegate;

public class RequestHelper {
	private final AuthDelegate authDelegate = new AuthDelegate();
	private final ViewDelegate viewDelegate = new ViewDelegate();

	public void processGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		// determine if this is a record based request
		// otherwise just show view with the view delegate

		viewDelegate.resolveView(request, response);

	}

	public void processPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		final String path = request.getServletPath();

		switch (path) {
		case "/login":
			authDelegate.authenticate(request, response);
			break;
		default:
			response.sendError(405);
		}
	}
}
