package com.revature.banking.frontend;

public interface CLI {
	public static final String CLEAR_LINUX_SCREEN = "\033[H\033[2J";
	public static final String LINUX_OS = "Linux";
	public static final String WINDOWS_OS = "Windows";
	public static final Character PASSWORD_ECHO = new Character((char) 0);

	public void println(final String line);

	public void print(final String line);

	public String readLine();

	public String readPassword();

	public void println();

	public void clearScreen();

	public void flush();

	public void working();

	public void done();
}
