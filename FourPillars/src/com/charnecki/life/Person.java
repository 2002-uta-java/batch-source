package com.charnecki.life;

import java.util.ArrayList;
import java.util.List;

// extends an abstract class and implements two interfaces
public class Person extends Living implements CanWork, CanSpeak {
	
	private String name;
	private List<Person> children = new ArrayList<Person>();
	
	// Constructors for no, 1, 2, or 3+ fields
	public Person() {
		super();
		this.setType("Person");
		this.setName("A Person");
		this.setAge(0);
		birth();
	}
	
	// Reusing same code as a no arg constructor so birth can be unique if a person is named
	public Person(String name) {
		super();
		this.setType("Person");
		this.setName(name);
		this.setAge(0);
		birth();
	}
	
	public Person(String name, int age) {
		this(name);
		this.setAge(age);
	}
	
	public Person(String name, int age, Person... children) {
		this(name, age);
		for(Person child: children) {
			this.children.add(child);
		}
	}

	// Getters and Setters for private fields
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Person> getChildren() {
		// returns a new array list as to not break encapsulation but I think it does anyway
		return new ArrayList<Person>(children);
	}

	public void setChildren(Person... children) {
		this.children.clear();
		
		for(Person child: children) {
			this.children.add(child);
		}
	}

	// Overrides of abstract methods from interfaces
	@Override
	public void birth() {
		System.out.println(name+" has been born.");
	}
	
	@Override 
	public void die() {
		System.out.println(name + " has died.");
		this.setAlive(false);
	}
	
	@Override
	public void speak() {
		if (getAlive()) {
			System.out.println("\"Hello, Bob. Isn't the weather great today?\"");
		} else {
			// Exception will be thrown if someone is dead and tries to speak
			throw new IsDeadException(name+" is dead and cannot speak.");
		}
	}

	@Override
	public void doWork() {
		if (getAlive()) {
			System.out.println(name + " is doing work.");			
		} else {
			// Exception will be thrown if someone is dead and tries to work
			throw new IsDeadException(name+" is dead and cannot work.");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	// overriding equals for meaningful comparisons
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	// toString override to be able to display Person data
	@Override
	public String toString() {
		return "Person [name=" + name + ", children=" + children + ", getAge()=" + getAge() + ", getAlive()=" + getAlive() +"]";
	}
	
}
