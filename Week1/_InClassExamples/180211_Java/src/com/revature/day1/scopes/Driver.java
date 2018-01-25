package com.revature.day1.scopes;

public class Driver {

	public static void main(String[] args) {
		System.out.println("=====\nAmount of persons: "+ Person.personCount+ "\n=====");
		//When instantiating a class, you need to follow the format below
		//Reference = assignment
		//ClassName = varName = new ClassName()
		Person p = new Person();
		/*
		 * The new keyword calls the constructor of any given class
		 */
		System.out.println("Name: " +p.name);
		System.out.println("Age: " +p.age);
	
	
		Person p2 = new Person();
		Person p3 = new Person();
		Person p4 = new Person();
	
		System.gc(); // This is a REQUEST for garbage collection, not a force.
		
		//Tell the main thread to wait for  1 second.
	//	try {
	//		Thread.sleep(1000);
	//	} catch {}
		
		
		System.out.println("=====\nAmount of persons: "+ Person.personCount+ "\n=====");
	}

}
