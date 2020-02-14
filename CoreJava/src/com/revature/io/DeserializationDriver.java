package com.revature.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializationDriver {
	
	public static void main(String[] args) {
		
		try (FileInputStream fis = new FileInputStream(
				"C:\\Eclipse\\Workspace\\Revature\\batch-source\\CoreJava\\src\\com\\revature\\io\\user.ser");
				ObjectInputStream ois = new ObjectInputStream(fis);){
			
			User u = (User) ois.readObject();
			System.out.println(u);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}