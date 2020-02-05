package com.revature.tianyu_li.oopexercise.avatar;

import com.revature.tianyu_li.oopexercise.exception.OverPowerException;

public class Warrior extends Character {
	
	public Warrior(String name, int strength) {
		super(name);
		if (strength > 10) {
			throw new OverPowerException("You cannot possibly have that much strength!");
		}
		else {
			this.strength = strength;
		}
		
	}
	
	@Override
	public void specialAttack(Character target) {
		System.out.println(this.getName() + " stabs " + target.getName() + " with his claymore!");
		target.beAttacked(strength * 2);
		System.out.println(target.getName() + " lost " + strength * 2 + " hp");
	}
}
