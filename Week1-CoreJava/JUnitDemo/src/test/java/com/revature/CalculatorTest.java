package com.revature;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculatorTest {
	
	
	@Test
	public void testEmptyString() {
//		int expected = 0;
//		int actual = Calculator.add("");
		assertEquals(0, Calculator.add(""));
	}

	@Test
	public void testInvalidInput() {
		int expected = -1;
		int actual = Calculator.add("jafklsf");
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSingleNumber() {
		int expected = 97;
		int actual = Calculator.add("97");
		assertEquals(expected, actual);
	}
	
	@Test
	public void testTwoNumbers() {
		int expected = 13;
		int actual = Calculator.add("5,8");
		assertEquals(expected, actual);
	}
	
	@Test
	public void testOneNumberAndInvalidInput() {
		int expected = -1;
		int actual = Calculator.add("82,bananas");
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNullInput() {
		assertEquals(-1, Calculator.add(null));
	}
	
	@Test
	public void testNegative() {
		
		String expected = ""; 
				
		try {
			Calculator.add("-1,0");
		} catch (Exception e){
			expected = e.getMessage();
		}
		
		assertEquals("Cannot call add on negative numbers",expected);
	}
	
	@Test
	public void multipleThousands() {
		int expected = 1005;
		int actual = Calculator.add("5,8000,1001,1000");
		assertEquals(expected, actual);
	}
	
	
	
}
