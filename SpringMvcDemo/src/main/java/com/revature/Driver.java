package com.revature;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.Bear;

public class Driver {
	public static void main(String[] args) {
		final ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		final Bear b = (Bear) ac.getBean("bear");
		b.setAwake(false);
		System.out.println(b);

		b.hibernate();

		((ConfigurableApplicationContext) ac).close();
	}
}
