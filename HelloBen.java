import java.util.Scanner;

public class HelloBen {
	public static void main(String... args) {
		final Scanner scanner = new Scanner(System.in);

		System.out.println("What is your name?");

		final String name = scanner.nextLine();

		System.out.println(name + "'s farts stink the worst.");

		scanner.close();
	}
}
