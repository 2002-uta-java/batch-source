package com.revature.shapes;

public class Square extends Rectangle{
	public Square() {
		super();
	}
	
	public Square(int sideLength) {	
		// implicit super() here
		super.setHeight(sideLength);
		super.setWidth(sideLength);
	}
	
	@Override
	public void setHeight(int height) {
		super.setHeight(height);
		super.setWidth(height);
	}
	
	@Override
	public void setWidth(int width) {
		super.setHeight(width);
		super.setWidth(width);
	}

	@Override
	public String toString() {
		return "Square [getHeight()=" + getHeight() + ", getWidth()=" + getWidth() + "]";
	}
	
	@Override 
	public void draw() {
		System.out.println("drawing square");
	}
	
	
}
