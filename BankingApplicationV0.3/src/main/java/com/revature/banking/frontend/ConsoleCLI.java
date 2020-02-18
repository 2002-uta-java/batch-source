package com.revature.banking.frontend;

import java.io.BufferedReader;

import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * This class uses a Console object if possible but if not, it falls back on a
 * BufferedReader with System.in.
 * 
 * @author Jared F Bennatt
 *
 */
public class ConsoleCLI implements CLI {

	private final Console console;
	private final BufferedReader in;
	private final PrintStream out;
//	private ConsoleReader console;
	private final boolean isLinux;

	public ConsoleCLI() {
		super();
		this.console = System.console();

		this.in = console == null ? new BufferedReader(new InputStreamReader(System.in)) : null;
		this.out = console == null ? System.out : null;
		this.isLinux = System.getProperty("os.name").equals(CLI.LINUX_OS);
	}

	@Override
	public void println(String line) {
		if (console != null)
			console.printf(line + System.lineSeparator());
		else
			out.println(line);

	}

	@Override
	public void print(String line) {
		if (console != null)
			console.printf(line);
		else
			out.print(line);
	}

	@Override
	public String readLine() throws IOException {
		if (console != null)
			return console.readLine();
		else
			return in.readLine();
	}

	@Override
	public String readPassword() throws IOException {
		if (console != null) {
			final String password = new String(console.readPassword());
			return password;
		} else
			return in.readLine();
	}

	@Override
	public void println() {
		if (console != null)
			console.printf(System.lineSeparator());
		else
			out.println();
	}

	@Override
	public void clearScreen() {
		if (isLinux) {
			this.print(CLI.CLEAR_LINUX_SCREEN);
		} else {
			this.println();
			this.println();
		}
	}

}
