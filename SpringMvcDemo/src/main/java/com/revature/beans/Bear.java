package com.revature.beans;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Bear implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean isFull = true;
	private boolean isAwake = true;
	private boolean isWinter = false;

	public boolean isWinter() {
		return isWinter;
	}

	public void setWinter(boolean isWinter) {
		this.isWinter = isWinter;
	}

	@Override
	public String toString() {
		return "Bear [isFull=" + isFull + ", isAwake=" + isAwake + "]";
	}

	public Bear() {
		super();
	}

	public boolean isFull() {
		return isFull;
	}

	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}

	public boolean isAwake() {
		return isAwake;
	}

	public void setAwake(boolean isAwake) {
		this.isAwake = isAwake;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void hibernate() {
		if (isWinter) {
			setAwake(false);
			System.out.println("zzzzz");
		} else {
			throw new RuntimeException("It's not winter");
		}
	}

}
