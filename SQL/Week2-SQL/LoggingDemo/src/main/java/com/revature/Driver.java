package com.revature;

import org.apache.log4j.Logger;

public class Driver {

	private static Logger log = Logger.getRootLogger();
	
	public static void main(String[] args) {
//		log.trace("trace");
//		log.debug("debug");
//		log.info("info");
//		log.warn("warn");
//		log.error("error");
//		log.fatal("fatal");
		
		calculate(9,4);
//		calculate(-3,5);
	}
	
	
	public static int calculate(int x, int y) {
		
		if(x<0 || y<0) {
			log.error("incorrect input provided");
			throw new RuntimeException();
		}
		
		int sum = x+y;
		log.info("calculated sum: "+sum);
		return sum;
	}
	
}
