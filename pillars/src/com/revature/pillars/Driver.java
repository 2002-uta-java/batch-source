package com.revature.pillars;

import java.util.ArrayList;

import com.revature.construction.ConstructionException;

public class Driver {
	public static void main(String[] args) {	
		ArrayList<Pillar> oop = new ArrayList<Pillar>();
		oop.add(new Tenet("abstraction", "simplifying interface"));
		oop.add(new Tenet("encapsulation", "hiding data"));
		oop.add(new Tenet("inheritance", "reusing code with a class hierarchy"));
		oop.add(new Tenet("polymorphism", "many forms"));
		for(Pillar p: oop) {
			System.out.println(p);
		}
		System.out.println("**");
		
		ArrayList<Column> columns = new ArrayList<Column>();
		columns.add(new Column());
		columns.get(0).setHeight(3);
		columns.add(new Column(100));
		columns.add(new Column("stone"));
		columns.add(new ClassicalColumn(ClassicalColumn.COLUMN_TYPE.DORIC));
		columns.get(3).setHeight(10);
		columns.add(new Column(4));
		try { 
			columns.get(4).build();
		} catch (Exception e) { /* ignore it, as we aren't throwing above */ }
		
		for(Column c: columns) {
			//System.out.println("--");
			System.out.println(c);
			try {
				c.build();
				c.raze();
			} catch (ConstructionException e) {
				System.err.println("Trouble with construction: " + e.getMessage());
			}
		}
	}
}
