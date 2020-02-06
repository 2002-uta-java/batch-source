package com.revature.types;

public class stringDriver {

	public static void main(String[] args) {
		String string1 = "I'm a string!";
		String string2 = "I'm a string!";
		
		System.out.println("with equals: " + string1.equals(string2));
		System.out.println("with ==: " + (string1 == string2));
		
		System.out.println("-----------------------");
		
		String alsoString1 = string1;
		string1 = string1 + "!";
		
		System.out.println("string1: " + string1);
		System.out.println("alsoString1: " + alsoString1);
		
		System.out.println("with equals: " + string1.equals(alsoString1));
		System.out.println("with ==: " + (string1 == alsoString1));
		
		System.out.println("-----------------------");
		System.out.println("working with string builders");
		System.out.println("-----------------------");
		
		StringBuilder sb1 = new StringBuilder("I'm a StringBuilder object");
		StringBuilder sb2 = new StringBuilder("I'm a StringBuilder object");
		System.out.println("with ==: " + (sb1 == sb2));

	}

}
