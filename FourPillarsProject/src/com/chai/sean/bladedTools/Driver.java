package com.chai.sean.bladedTools;

public class Driver {

	public static void main(String[] args) {

		//Implementation of Stats uses different and appropriate calculateGrindAngle() depending on which blade class is instantiated
		//Example of Abstraction
		RazorBlade rb = new RazorBlade(0,1,15,"Chisel");
		rb.Cut();
		rb.Sharpen();
		rb.Stats();
		
		EuropeanKitchenKnife ekk = new EuropeanKitchenKnife(2,4,20,"Hollow");
		ekk.Cut();
		ekk.Sharpen();
		ekk.Stats();
		
		RazorBlade rb2 = new RazorBlade(-1,-1,-1,"");
		rb2.Cut();
		rb2.Sharpen();
		rb2.Stats();
		
		
	}

}
