package com.revature.banking.frontend;

import java.io.IOException;

public interface CLI {
	public static final String CLEAR_LINUX_SCREEN = "\\033[H\\033[2J";

	public void println(final String line);

	public void print(final String line);

	public String readLine() throws IOException;

	public String readPassword() throws IOException;

	public void println();

	public void clearScreen();
}
