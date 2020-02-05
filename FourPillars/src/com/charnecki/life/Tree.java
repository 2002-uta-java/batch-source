package com.charnecki.life;

public class Tree extends Living {
	
	// Constructor setting type of living thing to Tree
	public Tree() {
		super();
		this.setType("Tree");
		birth();
	}

	// Unique birth method overriding the abstract method
	@Override
	public void birth() {
		System.out.println("A tree has started growing.");
	}
}
