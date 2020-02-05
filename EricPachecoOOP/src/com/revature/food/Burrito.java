package com.revature.food;

/**
 * 
 * @author erpac
 *
 */
public class Burrito extends fud{
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ingredients == null) ? 0 : ingredients.hashCode());
		result = prime * result + (taste ? 1231 : 1237);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Burrito other = (Burrito) obj;
		if (ingredients == null) {
			if (other.ingredients != null)
				return false;
		} else if (!ingredients.equals(other.ingredients))
			return false;
		if (taste != other.taste)
			return false;
		return true;
	}
	/**
	 * setting default values for our class members
	 */
	String ingredients = null;
	boolean taste = false;
	/**
	 * default constructor that call our pearent constructor
	 */
	public Burrito() {
		super();
	}
	public Burrito(String ingredients, boolean taste) {
		super();
		this.ingredients = ingredients;
		this.taste = taste;
	}
	/**
	 * getters and setters
	 */
	public String getIngredients() {
		return ingredients;
	}
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	public boolean isTaste() {
		return taste;
	}
	public void setTaste(boolean taste) {
		this.taste = taste;
	}
	/**
	 * overriding our Time
	 */
	@Override
	public void timeEaten() {
		System.out.println("Yay It's lunch Time!");
	}

}
