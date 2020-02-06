package com.revature.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializationDriver {
	
	public static void main(String[] args) {
		User user = new User(13,"bobby","bobbyrox2020");
		
		try(FileOutputStream fos = new FileOutputStream("src/com/revature/io/user.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos)){
			
			oos.writeObject(user);
			
			System.out.println("Serialized User");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
		
	}

}
