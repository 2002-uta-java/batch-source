package generate_users;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ParseLastNames {
	public static final String LAST_NAMES = "last_names_raw.txt";
	public static final String FILE_OUT = "last_names.txt";

	public static void main(String[] args) {
		try (final Scanner scanner = new Scanner(new File(LAST_NAMES));
				final PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(FILE_OUT)));) {
			while (scanner.hasNextLine()) {
				final String name = readLine(scanner);
				pw.println(name);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	private static String readLine(Scanner scanner) {
		final String lastName = scanner.next();
		scanner.next();
		scanner.next();
		scanner.next();

		return lastName.charAt(0) + lastName.substring(1).toLowerCase();
	}
}
