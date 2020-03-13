package org.hopto.jfbennatt.pillars;

public class Driver {

	public static void main(String[] args) throws QuadraticException {
		testPolynomial();
		printBreak();
		testQuadratic();
		printBreak();
		testIntegrator();
	}

	public static void printBreak() {
		System.out.println("-----------------");
	}

	public static void testPolynomial() {
		System.out.println("Testing Polynomial");
		// make polynomial 1 + 2x + 3x^2 + 4x^3 + 5x^4
		final RealFunction poly = new Polynomial(1, 2, 3, 4, 5);

		System.out.println("f(0) = 0 ?: " + poly.eval(0));
		System.out.println("f(1) = 15 ?: " + poly.eval(1));
		System.out.println("f(2) = 129 ?: " + poly.eval(2));
	}

	public static void testQuadratic() throws QuadraticException {
		System.out.println("Testing Quadratic");
		// try something with two real values
		Quadratic quad = new Quadratic(1, 5, 6);

		try {
			System.out.println("discriminant (1): " + quad.discriminant());
			System.out.println("y-intercept (6): " + quad.yIntercept());
			System.out.println("left (-3): " + quad.leftSolution());
			System.out.println("right (-2): " + quad.rightSolution());
		} catch (QuadraticException qe) {
			System.out.println("there shouldn't have been an exception");
			throw qe;
		}

		// try something with a 0 discriminant
		quad = new Quadratic(1, 2, 1);

		try {
			System.out.println("discriminant (0): " + quad.discriminant());
			System.out.println("y-intercept (1): " + quad.yIntercept());
			System.out.println("left (-1): " + quad.leftSolution());
			System.out.println("right (-1): " + quad.rightSolution());
		} catch (QuadraticException qe) {
			System.out.println("there shouldn't have been an exception");
			throw qe;
		}

		// try something with no real solutions
		quad = new Quadratic(1, 0, 1);

		try {
			System.out.println("discriminant (-4): " + quad.discriminant());
			System.out.println("y-intercept (1): " + quad.yIntercept());
			System.out.println("left (-1): " + quad.leftSolution());
			System.out.println("right (-1): " + quad.rightSolution());
		} catch (QuadraticException qe) {
			System.out.println("This is correct, there should be a \"no real solutions exception\" ");
			System.out.println(qe.getMessage());
		}

		// try something with infinite solutions
		quad = new Quadratic();

		try {
			System.out.println("discriminant (0): " + quad.discriminant());
			System.out.println("y-intercept (1): " + quad.yIntercept());
			System.out.println("left (-1): " + quad.leftSolution());
			System.out.println("right (-1): " + quad.rightSolution());
		} catch (QuadraticException qe) {
			System.out.println("there should be an infinite solutions exception");
			System.out.println(qe.getMessage());
		}
	}

	public static void testIntegrator() {
		System.out.println("Testing integrator");

		final Polynomial poly = new Polynomial(1, 2, 3, 4);
		// the indefinite integral for this should be:
		final Polynomial integral = poly.integrate();

		System.out.println("testing [0, 1]: " + RealFunction.integrate(poly, 0, 1) + " and  (exact) "
				+ (integral.eval(1) - integral.eval(0)));
		System.out.println("testing from Polynomial.integrate (should be exact): " + poly.integrate(0, 1));
	}
}
