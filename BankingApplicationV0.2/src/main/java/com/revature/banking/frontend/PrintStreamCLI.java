package com.revature.banking.frontend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * A CLI implementation that uses a given PrintStream and InputStream for i/o.
 * The InputStream is then converted to a BufferedReader.
 * 
 * @author Jared F BEnnatt
 *
 */
public class PrintStreamCLI implements CLI {

	private final PrintStream out;
	private final BufferedReader in;

	/**
	 * The default constructor uses System.out and System.in as the i/o streams.
	 */
	public PrintStreamCLI() {
		this(System.out, System.in);
	}

	/**
	 * Creates a CLI (command line interface) with the given PrintStream and
	 * InputStream.
	 * 
	 * @param out PrintStream used for writing text.
	 * @param in  InputStream used for reading input.F
	 */
	public PrintStreamCLI(final PrintStream out, final InputStream in) {
		super();
		this.out = out;
		this.in = new BufferedReader(new InputStreamReader(in));
	}

	@Override
	public void println(String line) {
		out.println(line);
	}

	@Override
	public void print(String line) {
		out.print(line);
	}

	@Override
	public String readLine() throws IOException {
		return in.readLine();
	}

	@Override
	public String readPassword() throws IOException {
		return readLine();
	}
}
