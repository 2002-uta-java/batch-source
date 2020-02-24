package com.revature.jfbennatt.testing;

import java.io.IOException;

import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jasypt.util.password.PasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;

import com.revature.jfbennatt.reimbursement.dao.models.Employee;
import com.revature.jfbennatt.reimbursement.dao.EmployeeDao;
import com.revature.jfbennatt.reimbursement.daos.postgres.EmployeeDaoPostgres;

public class TestServlet extends HttpServlet {

	/**
	 * default from eclipse
	 */
	private static final long serialVersionUID = 1L;

	public static final String EMAIL = "email";
	public static final String PASSWORD = "password";

	public static final String SESSION_ID_COOKIE = "sessionid";

	public static final String FIRST_NAME_COOKIE = "firstname";
	public static final String LAST_NAME_COOKIE = "lastname";

	private final EmployeeDao eDao = new EmployeeDaoPostgres();
	private final PasswordEncryptor passEnc = new StrongPasswordEncryptor();
	private final Random rand = new Random();

	@Override
	public void init() {
		// do any setup necessary
	}

	@Override
	public void doGet(final HttpServletRequest request, final HttpServletResponse response) {
		final Cookie[] cookies = request.getCookies();

		if (cookies == null) {
			Logger.getRootLogger().fatal("No cookies were sent with HTTP request");
			response.setStatus(400);
			// get html page to send back
			final RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
				Logger.getRootLogger().error(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
				Logger.getRootLogger().error(e.getMessage());
			}
		} else {
			final String email = TestServlet.getValue(EMAIL, cookies);
			final String password = TestServlet.getValue(PASSWORD, cookies);

			// delete the cookies once we read the email and password
			deleteAllCookies(cookies);

			final Employee emp = eDao.getEmployee(email);

			// good status (even if they don't log in)
			response.setStatus(200);
			response.setContentType("text/html");

			// check password
			if (emp != null && passEnc.checkPassword(password, emp.getPassword())) {
				// create a session_id and send it back to the browser via a cookie
				final String sessionId = createSessionId();

				final Cookie sessionIdCookie = new Cookie(SESSION_ID_COOKIE, sessionId);
				final Cookie firstNameCookie = new Cookie(FIRST_NAME_COOKIE, emp.getFirstName());
				final Cookie lastNameCookie = new Cookie(LAST_NAME_COOKIE, emp.getLastName());
				response.addCookie(sessionIdCookie);
				response.addCookie(firstNameCookie);
				response.addCookie(lastNameCookie);

				// get html page to send back
				final RequestDispatcher dispather = request.getRequestDispatcher("/employee/template.html");

				// update sessionid in database
				eDao.updateSessionId(email, sessionId);

				// attempt to send back page (with response)
				try {
					dispather.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
					Logger.getRootLogger().fatal(e.getMessage());
				} catch (IOException e) {
					e.printStackTrace();
					Logger.getRootLogger().fatal(e.getMessage());
				}
			} else {
				// TODO need to redirect to login page with message saying login failed

				// get html page to send back
				final RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
				try {
					dispatcher.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
					Logger.getRootLogger().error(e.getMessage());
				} catch (IOException e) {
					e.printStackTrace();
					Logger.getRootLogger().error(e.getMessage());
				}
			}
		}
	}

	private void deleteAllCookies(Cookie[] cookies) {
		for (final Cookie cookie : cookies) {
			cookie.setMaxAge(0);
		}
	}

	private String createSessionId() {
		final char[] sessionId = new char[EmployeeDao.SESSION_ID_LENGTH];

		for (int i = 0; i < EmployeeDao.SESSION_ID_LENGTH; ++i)
			sessionId[i] = randomChar();

		return new String(sessionId);
	}

	private char randomChar() {
		// generate random lower case, upper case, or number
		switch (rand.nextInt(3)) {
		case 0:
			return (char) ('a' + rand.nextInt(26));
		case 1:
			return (char) ('A' + rand.nextInt(26));
		case 2:
			return (char) ('0' + rand.nextInt(10));
		}

		Logger.getRootLogger().error("Random number generator created a number not, 0, 1, or 2");
		throw new RuntimeException("Random number generator created a number not, 0, 1, or 2");
	}

	public static String getValue(final String name, final Cookie[] cookies) {
		for (final Cookie cookie : cookies) {
			if (cookie.getName().equals(name))
				return cookie.getValue();
		}
		Logger.getRootLogger().error(name + " property wasn't found in cookies");

		return null;
	}
}
