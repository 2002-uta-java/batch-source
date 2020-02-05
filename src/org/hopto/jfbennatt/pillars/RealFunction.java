package org.hopto.jfbennatt.pillars;

/**
 * This represents a function which maps from Reals to Reals (a scalar
 * function). Several common functions are provided via the Math class.
 * 
 * @author Jared F. Bennatt
 *
 */
public interface RealFunction {
	/**
	 * This is the default number of partitions used for evaluation an integral
	 */
	public static final int NUM_PARTITIONS = 100;

	/**
	 * Takes a decimal parameter and returns the result of this function
	 * 
	 * @param x the real valued argument to this functions
	 * @return the real valued result, given the argument <code>x</code>.
	 */
	public double eval(final double x);

	/**
	 * Uses a trapezoid method to integrate and breaks up into the given number of
	 * regions
	 * 
	 * @param f     the function to integrate
	 * @param left  the left bound of the integral
	 * @param right the right bound of the integral
	 * @param n     the number of partitions to break integral into
	 * @return the evaluation of this integral
	 */
	public static double integrate(final RealFunction f, final double left, final double right, final int n) {
		// this function is an example of abstraction. I am using a particular method to
		// do a numerical integration, but the user does not know aside from the fact
		// that I say so in the comment...but even then they don't know how I'm doing
		// the trapezoid rule (I could be using scaling to get a better integral--which
		// I'm not).
		double val = 0.5 * (f.eval(left) + f.eval(right));
		final double range = right - left;
		final double dx = (right - left) / n;

		for (int i = 1; i < n; ++i) {
			final double x = left + (i * range) / n;
			val += f.eval(x);
		}

		return val * dx;
	}

	/**
	 * Uses a trapezoid method to integrate over the given region. This uses the
	 * NUM_PARTITIONS to break the integral up.
	 * 
	 * @param f     the function to integrate
	 * @param left  the left bound of the integral
	 * @param right the right bound of the integral
	 * @return the evaluation of this integral
	 */
	public static double integrate(final RealFunction f, final double left, final double right) {
		return RealFunction.integrate(f, left, right, NUM_PARTITIONS);
	}
}
