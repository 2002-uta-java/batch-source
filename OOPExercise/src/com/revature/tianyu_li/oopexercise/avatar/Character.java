package com.revature.tianyu_li.oopexercise.avatar;

public abstract class Character implements Doable {

	//this character class defines the general stats and ability each avatar should be able to have (inheritance)
	
	//all fields are protected which means they are only accessible through within the package or subclass or getter and setters (encapsulation)
	
	protected String name;
	protected int health;
	protected int mana;
	protected int strength;
	protected int agility;
	protected int intelligence;
	
	public Character() {
		super();
	}
	
	public Character(String name) {
		this.name = name;
		this.health = 100;
	}
	
	public String getName() {
		return name;
	}
	
	public void beAttacked(int damage) {
		if (this.health - damage < 0) {
			this.health = 0;
		}
		else {
			this.health -= damage;
		}
	}
	
	public void attack(Character target) {
		target.beAttacked(strength);
		System.out.println(name + " punches " + target.getName());
		System.out.println(target.getName() + " lost " + strength + " hp");
	}
	
	public void showStats() {
		System.out.println(name + " has health: " + health + " mana: " + mana + " strength: " + strength + " agility: " + agility + " intelligence: " + intelligence);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + agility;
		result = prime * result + health;
		result = prime * result + intelligence;
		result = prime * result + mana;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + strength;
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
		Character other = (Character) obj;
		if (agility != other.agility)
			return false;
		if (health != other.health)
			return false;
		if (intelligence != other.intelligence)
			return false;
		if (mana != other.mana)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (strength != other.strength)
			return false;
		return true;
	}
	
	
}
