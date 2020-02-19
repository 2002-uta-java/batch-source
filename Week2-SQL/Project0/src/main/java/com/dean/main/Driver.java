package com.dean.main;

public class Driver {

	public static void main(String[] args) {
		Bank b = Bank.getBank();
		b.start();
	}
}
