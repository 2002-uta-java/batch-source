package generate_users;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ThreeLetterWordsParser {
	public static final String THREE_LETTER = "three_letter_words_raw.txt";
	public static final String FILE_OUT = "three_letter_words.txt";

	public static void main(String[] args) {
		try (final BufferedReader br = new BufferedReader(new FileReader(THREE_LETTER));
				final PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(FILE_OUT)));) {
			String line = null;
			while ((line = br.readLine()) != null) {
				pw.println(getWord(line));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getWord(String line) {
		final int index = line.indexOf(":");
		return line.substring(0, index);
	}
}
