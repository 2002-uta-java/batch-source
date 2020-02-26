package com.revature.jfbennatt.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.revature.jfbennatt.reimbursement.dao.EmployeeDao;
import com.revature.services.ReimbursementService;

public class Controller {
	public static final String LOGIN = "login";

	private final ReimbursementService rService;

	public Controller() {
		super();
		rService = new ReimbursementService();
	}

	public void setEmployeeDao(final EmployeeDao eDao) {
		this.rService.setEmployeeDao(eDao);
	}

	public synchronized int dispatch(final BufferedReader br, final PrintWriter pw) {
		String method = null;
		// read method
		try {
			method = br.readLine();
		} catch (IOException e) {
//			e.printStackTrace();
			// there was something wrong (probably couldn't read from the request)
			return 400;
		}

		if (method == null)
			return 400;

		switch (method) {
		case LOGIN:
			return Controller.login(rService, br, pw);
		}

		return -1;
	}

	private static int login(final ReimbursementService rService, final BufferedReader br, final PrintWriter pw) {
		// read user name
		String email = null, password = null;

		try {
			email = br.readLine();
			password = br.readLine();
		} catch (IOException e) {
			return 400;
		}

		if (email == null || password == null)
			return 400;

		final String loginToken = rService.login(email, password);

		if (loginToken == null) {
			// client provided bad credentials, have them retry to login
			return 449;
		}

		// else, they were logged in fine, write the token to the response body and
		// return a good signal
		pw.print(loginToken);
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw.close();

		return 200;
	}
}
