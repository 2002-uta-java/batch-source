package com.revature.sync;

public class Driver {
	
	public static void main(String[] args) {
		
		/*
		StringBuilder sharedBuilder = new StringBuilder();
		StringBuffer sharedBuffer = new StringBuffer();
		
		Runnable job = new StringTestRunnable(sharedBuilder, sharedBuffer);
		
		Thread t1 = new Thread(job);
		Thread t2 = new Thread(job);
		
		t1.start();
		t2.start();
		
		try { 
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			
		System.out.println("StringBuilder result: ");
		System.out.println(sharedBuilder);
		System.out.println();
		
		System.out.println("StringBuffer result:");
		System.out.println(sharedBuffer);
		*/
		
		Counter count = new Counter();
		Runnable counterJob = new CountTestRunnable(count);
		Thread t1 = new Thread(counterJob);
		Thread t2 = new Thread(counterJob);
		
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Count: "+ count.value);
		
	}

}
