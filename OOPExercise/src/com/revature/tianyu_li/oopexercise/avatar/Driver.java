package com.revature.tianyu_li.oopexercise.avatar;

public class Driver {

	public static void main(String[] args) {
		Character alan = new Warrior("Alan", 10);
		alan.showStats();
		Character jake = new Hunter("Jake", 10);
		jake.showStats();
		Character keith = new Wizard("Keith", 10);
		keith.showStats();
		
		System.out.println("The fight begins");
		
		alan.attack(jake);
		jake.showStats();
		
		jake.specialAttack(alan);
		alan.showStats();
		
		keith.specialAttack(jake);
		jake.showStats();
	}
}
