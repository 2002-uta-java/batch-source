package com.exercise.piracy;

public class Driver {

	public static void main(String[] args) {
			
		SailorAbstract[] sailors = new SailorAbstract[3];
		sailors[0] = new Swabby("Terry");
		sailors[1] = new Captain("Jerry");
		sailors[2] = new Pirate("Kerry");
			
		arrayCheck(sailors);
		
		for (SailorAbstract i : sailors) {
			try {
				i.report();
			} catch(ScuvryDogException e) {
				if (i.getMutinous()) {
					for(int j = 0; j< sailors.length;j++) {
						if (sailors[j].getRank() == "Captain") {
							sailors[j] = new Pirate(((Pirate)i).mutiny(sailors[j]));
						}
					}
				}
			}
		}
	
		arrayCheck(sailors);
	}
	public static void arrayCheck(SailorAbstract[] arr) {
		System.out.println("\n-----\nArray Check\n-----\n");
		for (SailorAbstract i : arr) {
			System.out.println("		"+i.toString());
		}
		System.out.println();
	}

}
