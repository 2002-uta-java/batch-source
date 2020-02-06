package com.revature.shapes;

public class Rectangle extends Shape {
	
	private int height;
	private int width;
	
	public Rectangle() {
		super();
		setNumOfSides(4);
	}
	
	public Rectangle(int height, int width) {
		this();
		this.height = height;
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		if(height>0) {
			this.height = height;
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		if(width>0) {
			this.width = width;
		}
	}

	@Override
	public String toString() {
		return "Rectangle [height=" + height + ", width=" + width + "]";
	}

	@Override
	public void draw() {
		System.out.println("drawing rectangle");
	}

	@Override
	public int calculateArea() {
		return height*width;
	}

	@Override
	public int calculatePerimeter() {
		return 2*(height+width);
	}
	
	

}
