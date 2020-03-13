package org.hopto.jfbennatt.pillars;

public class Quadratic extends Polynomial {
	// This is another example of inheritance because Quadratic inherits most of its
	// behavior from Polynomial (but also adds to it, for instance "exact"
	// solutions").

	/**
	 * This reprsents the "normal" case where there are real solution/s.
	 */
	public static final int NORMAL = 0;
	/**
	 * This is the case that there are no x-intercepts and thus only complex
	 * solutions.
	 */
	public static final int NO_REAL_SOLUTIONS = 1;
	/**
	 * This represents the case that the quadratic is trivial (i.e. f(x) = 0).
	 */
	public static final int INFINITE_SOLUTIONS = 2;

	/**
	 * This holds the square root of the discriminant
	 */

	// these are examples of encapsulation. The user does not know how the methods
	// discriminant, yIntercept, leftSolution, and rightSolution, are being
	// calculated. They also don't know how exceptions are being thrown.
	private final double disc;
	private final double left, right;
	private final double yInt;
	private final int type;

	/**
	 * Just calls the default constructor for Polynomial, which gives a function
	 * which evaluates to 0.
	 */
	public Quadratic() {
		super();
		this.disc = -1;
		this.left = this.right = this.yInt = 0;
		type = INFINITE_SOLUTIONS;
	}

	/**
	 * Initializes a quadratic of the form ax^2 + bx + c. <i>Note:</i> this is
	 * opposite of the direction in the Polynomial class.
	 * 
	 * @param a quadratic coefficient
	 * @param b linear coefficient
	 * @param c constant coefficient
	 */
	public Quadratic(final double a, final double b, final double c) {
		// This is polymorphism because I'm overloading the constructor for Quadratic
		super(c, b, a);
		disc = Quadratic.discriminant(a, b, c);

		this.yInt = this.eval(0);

		if (disc > 0) {
			final double disc2 = Math.sqrt(disc);
			this.left = (-b - disc2) / (2 * a);
			this.right = (-b + disc2) / (2 * a);
			this.type = NORMAL;
		} else if (disc < 0) {
			this.left = this.right = 0;
			this.type = NO_REAL_SOLUTIONS;
		} else {
			// the discriminant is 0, just need to make sure a isn't zero
			if (a != 0) {
				this.left = (-b) / (2 * a);
				this.right = (-b) / (2 * a);
				this.type = NORMAL;
			} else {
				this.left = this.right = 0;
				this.type = INFINITE_SOLUTIONS;
			}
		}
	}

	/**
	 * Returns the y-coordinate of the y-intercept for this function. This always
	 * exists unless the quadratic was trivial (f(x) = 0), in which case a
	 * QuadraticException is thrown.
	 * 
	 * @return y-coordinate of the y-intercept
	 * @throws QuadraticException thrown if this quadratic is trivial (f(x) = 0).
	 */
	public double yIntercept() throws QuadraticException {
		if (type != INFINITE_SOLUTIONS)
			return yInt;
		// else throw infinite solutions exception
		throw new QuadraticException(QuadraticException.NO_Y_INTERCEPT);
	}

	/**
	 * Gives the smaller (or more negative) zero of this function (or the
	 * x-coordinate of the more left x-intercept).
	 * 
	 * @return Smallest zero of this quadratic
	 * @throws QuadraticException thrown if there are no real solutions.
	 */
	public double leftSolution() throws QuadraticException {
		if (type == NORMAL)
			return left;
		// else
		switch (type) {
		case INFINITE_SOLUTIONS:
			throw new QuadraticException(QuadraticException.INFINITE_SOLUTIONS);
		case NO_REAL_SOLUTIONS:
			throw new QuadraticException(QuadraticException.NO_REAL_SOLUTIONS);
		default:
			throw new QuadraticException("unknown error-this shouldn't happen");
		}
	}

	/**
	 * Gives the larger (more positive) zero for this quadratic (or the x-coordinate
	 * of the more right x-intercept).
	 * 
	 * @return larger zero of this quadratic.
	 * @throws QuadraticException thrown if there are no real solutions
	 */
	public double rightSolution() throws QuadraticException {
		if (type == NORMAL)
			return right;
		// else
		switch (type) {
		case INFINITE_SOLUTIONS:
			throw new QuadraticException(QuadraticException.INFINITE_SOLUTIONS);
		case NO_REAL_SOLUTIONS:
			throw new QuadraticException(QuadraticException.NO_REAL_SOLUTIONS);
		default:
			throw new QuadraticException("unknown error-this shouldn't happen");
		}
	}

	/**
	 * Returns the discriminant for this quadratic
	 * 
	 * @return the discriminant for this quadratic function.
	 */
	public double discriminant() {
		// This is polymporphism because I'm overloading the discriminant method (one
		// here as an instance method and the one below, a static method)
		return disc;
	}

	/**
	 * Calculates the discriminant for a quadratic function of the form ax^2 + bx +
	 * c.
	 * 
	 * @param a coefficient of quadratic term.
	 * @param b coefficient of linear term.
	 * @param c coefficient of constant term.
	 * @return the discriminant of the given quadratic function
	 */
	public static double discriminant(final double a, final double b, final double c) {
		return b * b - 4 * a * c;
	}

	// generate by eclipse
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(left);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(right);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(yInt);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	// generated by eclipse
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quadratic other = (Quadratic) obj;
		if (Double.doubleToLongBits(left) != Double.doubleToLongBits(other.left))
			return false;
		if (Double.doubleToLongBits(right) != Double.doubleToLongBits(other.right))
			return false;
		if (Double.doubleToLongBits(yInt) != Double.doubleToLongBits(other.yInt))
			return false;
		return true;
	}

}
