package com.revature;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CalculatorTest {
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	
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
	public void testSingleNegNumber() {
		int expected = -97;
		int actual = Calculator.add("-97");
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
	public void testMultipleNumber() {
		int expected = 152;
		int actual = Calculator.add("50,52,27,23");
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNumberGreaterThan1000() {
		int expected = 999;
		int actual = Calculator.add("999,1001");
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNegativeNumbers() {
		expectedException.expect(IllegalArgumentException.class);
		Calculator.add("-1");
	}
	
}
