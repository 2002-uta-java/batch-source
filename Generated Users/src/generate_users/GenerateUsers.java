package generate_users;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class GenerateUsers {
	public static final String WORDS = "words.txt";
	public static final String FIRST_NAME_FILE = "first_names.txt";
	public static final String LAST_NAME_FILE = "last_names.txt";
	public static final String FILE_OUT = "users.txt";

	public static final int NUM_PEOPLE = 30;

	private static final Set<String> usernames = new HashSet<String>();
	private static final Set<String> ids = new HashSet<String>();
	private static final Random rand = new Random();
	private static final List<String> words3 = new ArrayList<String>();
	private static final List<String> words4 = new ArrayList<String>();
	private static final String[] SPECIALS = { "!", "@", "#", "$", "%", "^", "&", "*" };
	private static final List<String> FIRST_NAMES = new ArrayList<String>();
	private static final List<String> LAST_NAMES = new ArrayList<String>();

	public static void main(String[] args) {
		ids.add("1234567890");
		ids.add("0987654321");
		ids.add("6789012345");
		ids.add("6789054321");
		readWords();
		readWords(FIRST_NAMES, FIRST_NAME_FILE);
		readWords(LAST_NAMES, LAST_NAME_FILE);

		try (final PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(FILE_OUT)))) {

			for (int i = 0; i < NUM_PEOPLE; ++i) {
				final String name = randomName();
				final String[] firstLast = name.split(" ");
				final String username = genUserName(firstLast[0], firstLast[1]);
				final String taxid = generateTaxID();
				final String password = genPassword();

				pw.printf("%s %s %s %s" + System.lineSeparator(), name, taxid, username, password);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void readWords(List<String> names, String file) {
		try (final Scanner scanner = new Scanner(new File(file))) {
			while (scanner.hasNext())
				names.add(scanner.next());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void readWords() {
		try (final Scanner scanner = new Scanner(new File(WORDS))) {
			while (scanner.hasNext()) {
				final String next = scanner.next();
				if (next.length() == 3)
					words3.add(next);
				else
					words4.add(next);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static String genPassword() {
		final boolean first3 = rand.nextBoolean();
		final boolean firstCAP = rand.nextBoolean();
		String password = "";
		if (first3) {
			if (firstCAP) {
				password += randomWord(words3).toUpperCase();
				password += randomWord(words4).toLowerCase();
			} else {
				password += randomWord(words3).toLowerCase();
				password += randomWord(words4).toUpperCase();
			}
		} else {
			if (firstCAP) {
				password += randomWord(words4).toUpperCase();
				password += randomWord(words3).toLowerCase();
			} else {
				password += randomWord(words4).toLowerCase();
				password += randomWord(words3).toUpperCase();
			}
		}

		final int numNums = 1 + rand.nextInt(4);
		for (int i = 0; i < numNums; ++i)
			password += (char) ('0' + rand.nextInt(10));
		final int numSpecials = 1 + rand.nextInt(3);

		for (int i = 0; i < numSpecials; ++i) {
			password += randomSpecial();
		}
		return password;
	}

	public static String randomWord(final List<String> words) {
		return words.get(rand.nextInt(words.size()));
	}

	public static String randomSpecial() {
		return SPECIALS[rand.nextInt(SPECIALS.length)];
	}

	public static String randomName() {
		final String first = randomWord(FIRST_NAMES);
		final String last = randomWord(LAST_NAMES);

		return first + " " + last;
	}

	public static String genUserName(String first, String last) {
		first = first.toLowerCase();
		last = last.toLowerCase();
		String username = first.substring(0, 2) + last.substring(0, 3);

		if (usernames.contains(username)) {
			int count = 0;
			String newUsername = username + count++;
			while (usernames.contains(newUsername)) {
				newUsername = username + count++;
			}
			username = newUsername;
		}

		usernames.add(username);

		return username;
	}

	public static String generateTaxID() {
		while (true) {
			final String id = randomID();
			if (!ids.contains(id)) {
				ids.add(id);
				return id;
			}
		}
	}

	public static String randomID() {
		final char[] id = new char[10];
		for (int i = 0; i < 10; ++i) {
			id[i] = (char) ('0' + rand.nextInt(10));
		}

		return new String(id);
	}
}
