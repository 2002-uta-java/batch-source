package com.clearlabs.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    // 1- I created properties object
    private static Properties properties = new Properties();


    static {
        //2- Get the path and store in String, or directly pass into fileInputStream object
        String path = "configuration.properties";

        try {
            // 3-I need to open the file
            FileInputStream file = new FileInputStream(path);
            //4-I need to load the file to properties object
            properties.load(file);

            //5- Close the file
            file.close();

        } catch (IOException e) {
            System.out.println("Properties file not found.");
        }
    }

    //Our own custom method to read and get values from configuration.properties file
    public static String get(String keyWord){
        return properties.getProperty(keyWord);
    }
}
