package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

	// to read properties files from src/test/resources/config/config.properties
	
private static Properties prop = new Properties();
private static String path = "config/config.properties";
private static String env;

private ConfigManager() {
	
	//task is to restrict to create object outside the class.
	
}

//static block used to initialize static variable.
		//performing the operation of loading the properties file in the memory.
		//static block will be executed! Once during class loading time..


	static {
		
		env= System.getProperty("env");
		
		switch(env) {
		
		case "dev":{
			path="config/config.dev.properties";
		}
		
		case "qa":{
			path="config/config.qa.properties";
		}
		
		case "uat":{
			path="config/config.uat.properties";
		}
		default:
			
			path="config/config.qa.properties";
		}
		
		
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		
		if(input==null) {
			throw new RuntimeException("Can not find the path "+path);
		}
		
		
		try {
			 
			 prop.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	
	
	public static String getProperty(String key)  {

		
		return (prop.getProperty(key));

	}
}