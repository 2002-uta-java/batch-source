package com.charnecki.models;

import java.util.List;

public class Savings extends Account {

	private static final long serialVersionUID = 1L;
	
	public Savings() {
		super();
	}

	public Savings(int id, double balance, List<Integer> holders) {
		super(id, balance, holders);
	}

}
