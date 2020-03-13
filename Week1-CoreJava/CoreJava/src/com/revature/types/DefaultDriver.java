package com.revature.types;

//import java.util.Arrays;

public class DefaultDriver {

	static int i;
	static boolean bool;
	static char ch;
	static String str;
	static Object obj;
	static boolean[] arr = new boolean[4];
	
	public static void main(String[] args) {
		System.out.println(i);
		System.out.println(bool);
		System.out.println(ch);
		System.out.println(str);
		System.out.println(obj);
		
		// Arrays is a utility class which provides static methods that are helpful for searching and sorting
		System.out.println(java.util.Arrays.toString(arr));
		
		int localVar;
//		System.out.println(localVar);
	}

}
