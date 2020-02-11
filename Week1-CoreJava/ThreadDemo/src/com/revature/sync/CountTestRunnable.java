package com.revature.sync;

public class CountTestRunnable implements Runnable {
	
	Counter c;
	
	public CountTestRunnable() {
		super();
	}
	
	public CountTestRunnable(Counter c) {
		super();
		this.c = c;
	}

	@Override
	public void run() {
		for(int i=0; i<5000; i++) {
			c.increment();
		}
	}

}
