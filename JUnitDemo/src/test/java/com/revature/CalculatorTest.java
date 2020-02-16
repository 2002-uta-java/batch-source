package com.revature;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class CalculatorTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testEmptyString() {
		try {
			int expected = 0;
			int actual = Calculator.add("");
			assertEquals(expected, actual);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testInvalidInput() {
		try {
			int expected = -1;
			int actual = Calculator.add("jafklsf");
			assertEquals(expected, actual);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testSingleNumber() {
		try {
			int expected = 97;
			int actual = Calculator.add("97");
			assertEquals(expected, actual);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testTwoNumbers() {
		try {
			int expected = 13;
			int actual = Calculator.add("5,8");
			assertEquals(expected, actual);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testOneNumberAndInvalidInput() {
		try {
			int expected = -1;
			int actual = Calculator.add("82, bananas");
			assertEquals(expected, actual);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testNullInput() {
		try {
			assertEquals(-1, Calculator.add(null));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testUnknownAmountOfNumbers() {
		int expected = 10;
		int actual = 0;
		try {
			actual = Calculator.add("2,2,2,2,2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(expected, actual);
	}

	@Test
	public void testNumbersGreaterThan1000() {
		try {
			int expected = 500;
			int actual = Calculator.add("2000,250,250,5000,1500");
			assertEquals(expected, actual);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testNegativeNumberCallThrowsException() {

		try {
			Calculator.add("-1");
		} catch (Exception e) {
			assertEquals(true, e instanceof NumberFormatException);

		}

	}
}
