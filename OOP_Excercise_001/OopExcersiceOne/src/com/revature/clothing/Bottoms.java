package com.revature.clothing;

import com.revature.exceptions.InvalidCategoryException;

//In Bottoms is also abstract - another example of abstraction

public abstract class Bottoms extends Clothing {
	
	public String category = new String();
	
	public Bottoms() {
		super();
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		switch (category) {
		case "pants":
		case "shorts":
			this.category = category;
			break;
		default:
			try {
				throw new InvalidCategoryException("Cannot set clothing bottom to category: " + category + ". Your options are pants or shorts.");
			} catch(InvalidCategoryException e) {
				e.printStackTrace();
			}
		}
	}
}