package com.revature.tianyu_li.oopexercise.avatar;

public class Wizard extends Character {
	
	public Wizard(String name, int intelligence) {
		super(name);
		this.intelligence = intelligence;
	}
	
	@Override
	public void specialAttack(Character target) {
		System.out.println(this.getName() + " throws " + target.getName() + " a fireball!");
		target.beAttacked(intelligence * 2);
		System.out.println(target.getName() + " lost " + intelligence * 2 + " hp");
		
	}

}
