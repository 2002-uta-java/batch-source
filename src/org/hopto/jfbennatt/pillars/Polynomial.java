package org.hopto.jfbennatt.pillars;

import java.util.Arrays;

/**
 * This gives an implementation for a polynomial function with real
 * coefficients, i.e. ax^n + bx^(n - 1) + ... .
 * 
 * @author Jared F Bennatt
 *
 */
public class Polynomial implements RealFunction {
	// This class is an example of inheritance because Polynomial inherits the
	// functionality of the interface RealFunction

	// this is an example of encapsulation because all the user knows is that
	// eval(x) returns the
	// value for this polynomial, they do not know how that is being evaluated.
	private final double[] coeffs;

	/**
	 * Gives a default constructor, which will essentially initialize this function
	 * to f(x) = 0
	 */
	public Polynomial() {
		// there are no coefficients, so no evaluation will take place (zero will be
		// returned)
		coeffs = new double[0];
	}

	/**
	 * This takes a list of double coefficients, the order is given starting with
	 * the 0th exponent (the constant).
	 * 
	 * Ex 1. If you want ax^2 + bx + c, you would call
	 * <code>new Polynomial(c, b, a)</code>.
	 * 
	 * <i>All coefficients must be given:</i>
	 * 
	 * Ex 2. If you want ax^2 - b, you would call
	 * <code>new Polynomial(-b, 0, a)</code>
	 * 
	 * @param The coefficients for this polynomial, starting with the x^0 (constant)
	 *            coefficient.
	 */
	public Polynomial(final double... c) {
		// this is another example of polymorphism, where I am overloading the
		// constructor for Polynomial
		this.coeffs = new double[c.length];

		for (int i = 0; i < c.length; i++) {
			this.coeffs[i] = c[i];
		}
	}

	/**
	 * Returns a Polynomial that represents an anti-derivative of this polynomial.
	 * 
	 * @return A new polynomial that is this polyomial's anti-derivative.
	 */
	public Polynomial integrate() {
		if (coeffs.length == 0)
			return new Polynomial();
		// else normal case
		final double[] coeffs = new double[this.coeffs.length + 1];

		coeffs[0] = 0;

		for (int i = 1; i < coeffs.length; i++) {
			coeffs[i] = this.coeffs[i - 1] / i;
		}

		return new Polynomial(coeffs);
	}

	/**
	 * This evaluates ("exactly"--as much as floating point operations are exact)
	 * the integral of this polynomial over the given range.
	 * 
	 * @param left  Left limit of integral
	 * @param right right limit of integral
	 * @return evaluation of integral.
	 */
	public double integrate(final double left, final double right) {
		double xxLeft = left;
		double xxRight = right;
		double exp = 1.0;
		double val = 0;

		for (final double c : coeffs) {
			val += c * (xxRight - xxLeft) / exp;
			xxRight *= right;
			xxLeft *= left;
			exp += 1.0;
		}

		return val;
	}

	// This is an example of polymorphism. Here I am overriding the eval method from
	// the inherited interface.
	@Override
	public double eval(double x) {
		// we will keep a running sum
		double val = 0;

		// this is the current exponent (initially x^0 = 1), each time we will increase
		// by multiplying by x
		double xx = 1;

		// iterate through each coefficient
		for (final double c : coeffs) {
			// add this term to val
			val += c * xx;
			// increase the exponent
			xx *= x;
		}

		return val;
	}

	// generated by eclipse
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(coeffs);
		return result;
	}

	// generated by eclipse
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Polynomial other = (Polynomial) obj;
		if (!Arrays.equals(coeffs, other.coeffs))
			return false;
		return true;
	}
}