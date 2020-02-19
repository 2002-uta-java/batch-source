package com.revature.cuisines;

public class Italian extends Cuisine {
	
	public Italian() {
		super();
	}
	
	public Italian(String food) {
		this.food = food;
		if("pasta".equals(food)) {
			utensil = "fork and spoon";
		}
		else {
			utensil = "fork";
		}
	}
	
	public void cook() {
		System.out.println("Cook me with pasta!");
	}
	
	public void eat() {
		System.out.println("Eat me with a " + utensil);
	}

	public String getFood() {
		return food;
	}
}
