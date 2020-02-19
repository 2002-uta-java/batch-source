package com.hylicmerit;

public class Driver {

	public static void main(String[] args) {
		while(!CLIUtil.isExiting()) {
			CLIUtil.options();
		}
	}

}