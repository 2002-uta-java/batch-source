package com.revature.abstracting;

public abstract class AbstractAnimal {


	public AbstractAnimal() {
		super();
		
	}
	
	private String color;
	
	public void BodyType(){
		System.out.println("Abstract: Default all animal have a body type");
	}
	
	public void SpeedType(){
		System.out.println("Abstract: Default all animal have a speed type");
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}


	
}
