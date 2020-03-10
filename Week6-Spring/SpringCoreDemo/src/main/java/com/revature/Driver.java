package com.revature;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.Bear;
import com.revature.beans.Cave;

public class Driver {

	public static void main(String[] args) {
//		Cave c = new Cave();
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
//		Cave c = (Cave) ac.getBean("cave");
//		System.out.println(c);
		
//		Bear b = (Bear) ac.getBean("bearWithSetter");
//		System.out.println(b);
		
//		Bear b = (Bear) ac.getBean("bearWithConstructor");
//		System.out.println(b);
		
//		Bear b = (Bear) ac.getBean("bearWithAutowiring");
//		System.out.println(b);
		
		Bear b = (Bear) ac.getBean("bearWithAnnotation");
		System.out.println(b);
		
	}

}
