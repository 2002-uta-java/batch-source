package com.revature;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.Bear;

public class Driver {
	public static void main(String[] args) {
		final ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
//		final Cave cave = (Cave) ac.getBean("cave");
//		System.out.println(cave);

//		final Bear b1 = (Bear) ac.getBean("bearWithSetter");
//		System.out.println(b1);

//		final Bear b2 = (Bear) ac.getBean("bearWithConstructor");
//		System.out.println(b2);

//		final Bear b3 = (Bear) ac.getBean("bearWithAutowiring");
//		System.out.println(b3);

		final Bear b4 = (Bear) ac.getBean("bearWithAnnotations");
		System.out.println(b4);
	}
}
