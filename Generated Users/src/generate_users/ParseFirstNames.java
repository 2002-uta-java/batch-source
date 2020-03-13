package generate_users;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ParseFirstNames {
	public static final String FIRST_NAMES = "first_names_raw.txt";
	public static final String FIRST_NAME_OUT = "first_names.txt";

	public static void main(String[] args) {
		try (final Scanner scanner = new Scanner(new File(FIRST_NAMES));
				final PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(FIRST_NAME_OUT)));) {
			while (scanner.hasNextLine()) {
				final String[] names = readLine(scanner);
				pw.println(names[0]);
				pw.println(names[1]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	private static String[] readLine(Scanner scanner) {
		scanner.next();
		final String firstName = scanner.next();
		scanner.next();
		final String lastName = scanner.next();
		scanner.next();

		return new String[] { firstName, lastName };
	}
}
