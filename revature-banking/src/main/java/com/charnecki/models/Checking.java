package com.charnecki.models;

import java.util.List;

public class Checking extends Account {

	private static final long serialVersionUID = 1L;

	public Checking() {
		super();
	}
	
	public Checking(int id, double balance, List<Integer> holders) {
		super(id, balance, holders);
	}

}
