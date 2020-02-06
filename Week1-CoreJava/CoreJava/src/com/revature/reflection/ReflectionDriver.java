package com.revature.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.revature.shapes.Rectangle;
import com.revature.shapes.Square;

public class ReflectionDriver {
	
	public static void main(String[] args) {
		
		try {
			Class<?> c1 = Class.forName("com.revature.shapes.Square");
			System.out.println("Class: "+c1.getName());
			System.out.println("Parent Class: "+c1.getSuperclass());
			System.out.println("Parent of Parent Class: "+c1.getSuperclass().getSuperclass());
			
			System.out.println("---------- interfaces ------------");
			Class<?>[] interfaces = c1.getSuperclass().getSuperclass().getInterfaces();
			for(Class<?> interf : interfaces) {
				System.out.println(interf);
			}
			System.out.println("------------ methods -----------");
			Method[] methods = c1.getDeclaredMethods();
			for(Method method : methods) {
				System.out.println(method);
			}
			System.out.println("------------ rectangle fields -------------");
			Field[] fields = c1.getSuperclass().getFields(); // only returns public fields :(
			for(Field field: fields) {
				System.out.println(field);
			}
			
			System.out.println("------------ creating an instance with reflection -------------");
			Rectangle r = (Rectangle) c1.getSuperclass().newInstance();
			System.out.println(r);
			
			System.out.println("------------ changing a field value -------------");
			Field height = c1.getSuperclass().getDeclaredField("height");
			height.setAccessible(true);
			height.set(r, -10);
			height.setAccessible(false);
			System.out.println(r);
			
			System.out.println("------------ invoking a method -------------");
			Method setWidthMethod = c1.getSuperclass().getMethod("setWidth", int.class);
			setWidthMethod.invoke(r, -25);
			System.out.println(r);
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
