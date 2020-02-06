package com.revature.clothing;

import com.revature.exceptions.InvalidCategoryException;

// In Tops is also abstract - another example of abstraction

public abstract class Tops extends Clothing {
	
	public String category = new String();
	
	public Tops() {
		super();
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		switch (category) {
		case "shirt":
		case "coat":
			this.category = category;
			break;
		default:
			try {
				throw new InvalidCategoryException("Cannot set clothing top to category: " + category + ". Your options are shirt or coat.");
			} catch(InvalidCategoryException e){
				e.printStackTrace();
			}
		}
	}
}