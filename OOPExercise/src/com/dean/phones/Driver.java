package com.dean.phones;

public class Driver {

	public static void main(String[] args) {
		Phone iphoneX = new IPhoneX();
		iphoneX.ring();
		
		
		IPhoneX iphoneX2 = new IPhoneX(6);
		iphoneX2.ring();
		System.out.println(iphoneX2.hashCode());
		System.out.println(iphoneX2.toString());
		System.out.println("button number: "+iphoneX2.getNumOfButtons());
		System.out.println("phone touch screen? " + iphoneX2.isTouchScreen());
		
		IPhoneXS xs = new IPhoneXS("blue");
		System.out.println(xs.getColor());
		
		NokiaFlip nokia = new NokiaFlip(9);
		nokia.ring();
		System.out.println(nokia.hashCode());
		System.out.println(nokia.getNumOfKeyboardButtons());
		System.out.println(nokia.toString());
		
		Sidekick s = new Sidekick(35);
		s.ring();
		System.out.println(s.getNumOfKeyboardButtons());
		
	}
	
}
