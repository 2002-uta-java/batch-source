package com.revature;

import java.security.NoSuchAlgorithmException;

import com.revature.ui.UIDriver;

public class Revabank {

	public static void main(String[] args) {
		try {
			UIDriver.start();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

}
