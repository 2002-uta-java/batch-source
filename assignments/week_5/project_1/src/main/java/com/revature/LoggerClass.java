package com.revature;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerClass {
	
	private static final Logger LOGGER = LogManager.getLogger();

	public LoggerClass() {
		super();
	}
	
	public void postInfoLog(String log) {
		LOGGER.info(log);
	}
	
	public void postErrorLog(String log) {
		LOGGER.info(log);
	}

}
