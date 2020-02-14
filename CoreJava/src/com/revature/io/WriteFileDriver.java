package com.revature.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFileDriver {

	public static void main(String[] args) {
		File file = new File("src/com/revature/io/data.txt");
		
		try(FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);){
			bw.write("Hello World!!\n");
				
				System.out.println("wrote to file");
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
