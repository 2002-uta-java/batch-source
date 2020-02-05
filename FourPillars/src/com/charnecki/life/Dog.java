package com.charnecki.life;

public class Dog extends Living implements CanSpeak {

	public Dog() {
		super();
	}
	
	@Override
	public void birth() {
		System.out.println("A dog was born.");
	}

	@Override
	public void speak() {
		System.out.println("A dog is barking.");
	}

	
	
}
