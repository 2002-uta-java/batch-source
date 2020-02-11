package com.revature.sync;

public class StringTestRunnable implements Runnable {
	
	StringBuilder sBuilder = new StringBuilder();
	StringBuffer sBuffer = new StringBuffer();
	
	public StringTestRunnable() {
		super();
	}
	
	public StringTestRunnable(StringBuilder sBuilder, StringBuffer sBuffer) {
		super();
		this.sBuffer = sBuffer;
		this.sBuilder = sBuilder;
	}

	@Override
	public void run() {
		for(int i=0; i<50; i++) {
			sBuilder = sBuilder.append("~");
			sBuffer = sBuffer.append("~");
		}
		
	}

}
