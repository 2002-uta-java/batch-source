package com.revature.types;

import java.util.Arrays;

public class ArrayDriver {
	
	public static void main(String[] args) {
		
		int[] intArr1 = new int[10];
		int intArr2[] = new int[10];
		int[] intArr3 = {3,6,2,1,9,8};
		
		System.out.println(intArr3[2]);
		
		for(int i=0; i<intArr3.length; i++) {
			System.out.print(intArr3[i]+" ");
		}
		System.out.println();
		
		// we can do this for anything that is considered iterable
		for(int currentInt: intArr3) {
			System.out.print(currentInt + " ");
		}
		
		// we can also create 2D arrays
		int[][] int2DArr = new int[3][3];
		
		System.out.println();
		
//		printAll("Hello World");
//		printAll("bananas", "apples", "oranges");
//		printAll();
		
		printAll(5,"Hello World");
		
	}
	
	public static void printAll(int num, String... strArr) {
		for(int i = 0; i < num; i++) {
			System.out.println(Arrays.toString(strArr));			
		}
	}

}
