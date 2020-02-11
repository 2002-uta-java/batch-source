package com.revature;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ListDriver {

	public static void main(String[] args) {
		
		List<Integer> intList = new LinkedList<>();
		intList.add(5);
		intList.add(7);
		intList.add(8);
		intList.add(1);
		intList.add(39);
		intList.add(6);
//		intList.add("fajlkdfaldjsk");
		System.out.println(intList);
		
		Collections.sort(intList);
		
		System.out.println(intList);
		
//		Comparator<Integer> reverseIntCompare = Collections.reverseOrder();
	
		Comparator<Integer> reverseIntCompare = (int1, int2) -> int2 - int1;
		
		Collections.sort(intList, reverseIntCompare);
		
		System.out.println(intList);

		
		/*
		// why we like generics!! compile time type safety
		List generalList = new LinkedList();
		generalList.add(673);
		generalList.add("Hello World");
		String str = (String) generalList.get(0);
		System.out.println(str);
		*/
		
		
		
		
	}
	
}
