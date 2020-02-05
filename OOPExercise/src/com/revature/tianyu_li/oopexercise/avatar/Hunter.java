package com.revature.tianyu_li.oopexercise.avatar;

public class Hunter extends Character {

	public Hunter(String name, int agility) {
		super(name);
		this.agility = agility;
	}
	
	@Override
	public void specialAttack(Character target) {
		System.out.println(this.getName() + " shoots " + target.getName() + " an poison arrow!");
		target.beAttacked(agility * 2);
		System.out.println(target.getName() + " lost " + agility * 2 + " hp");
		
	}
	
	
}
