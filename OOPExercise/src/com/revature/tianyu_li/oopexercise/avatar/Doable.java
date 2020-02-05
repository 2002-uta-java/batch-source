package com.revature.tianyu_li.oopexercise.avatar;

public interface Doable {
	
	//this interface defines that all character should be able to perform a special attack depending on their own specialty (abstraction)
	
	//all sub-avatar class that implements this interface will have to implement their own version of special attack (polymorphism)
	
	void specialAttack(Character target);
	
}
