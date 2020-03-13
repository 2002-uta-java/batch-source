package com.revature.banking.frontend;

import java.io.IOException;

public interface CLI {
	public void println(final String line);

	public void print(final String line);

	public String readLine() throws IOException;

	public String readPassword() throws IOException;
}
