package com.revature;

import org.apache.log4j.Logger;

public class Driver {
	private static Logger logger = Logger.getRootLogger();

	public static void main(String[] args) {
		/*
		 * Initially get:
		 * 
		 * log4j:WARN No appenders could be found for logger (root). log4j:WARN Please
		 * initialize the log4j system properly. log4j:WARN See
		 * http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
		 * 
		 * Need to create a configuration file (lo4j.properties...which we put in
		 * resources folder)...going to need to get her logging file off of github
		 */
		logger.warn("info");
	}
}
