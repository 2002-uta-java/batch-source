package com.revature.jfbennatt.ers.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.jfbennatt.ers.controller.delegates.Delegate;
import com.revature.jfbennatt.ers.controller.delegates.ViewDelegate;

/**
 * This class routes requests from the {@link FrontController} to the
 * appropriate {@link Delegate}
 * 
 * @author Jared F Bennatt
 *
 */
public class RequestDispatcher {

	private final Delegate viewDelegate = new ViewDelegate();

	public RequestDispatcher() {
		super();
	}

	public void dispatch(final String path, final HttpServletRequest request, final HttpServletResponse response) {

	}

	public void init() {
		// TODO Auto-generated method stub

	}
}
