package com.revature.banking.frontend;

/**
 * This class takes a CLI object and runs an animation to show that the system
 * is working.
 * 
 * @author bennattj
 *
 */
public class WorkingAnimation implements Runnable {
	public static final long SLEEP_TIME = 250;
	public static final String[] ANIMATION_CHARS = { "/", "-", "\\", "|" };

	private final CLI io;
	private volatile boolean running = false;

	public WorkingAnimation(final CLI io) {
		this.io = io;
	}

	public void stop() {
		running = false;
	}

	@Override
	public void run() {
		running = true;
		int index = 0;
		while (running) {
			io.print(ANIMATION_CHARS[index]);
			index++;
			index %= ANIMATION_CHARS.length;
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				// do nothing if this fails
			}
			io.print("\b");
		}
	}

}
