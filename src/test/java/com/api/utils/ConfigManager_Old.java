package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager_Old {

	// to read properties files from src/test/resources/config/config.properties
	
private static Properties prop = new Properties();

private ConfigManager_Old() {
	
	//task is to restrict to create object outside the class.
	
}


	static {
		//static block used to initialize static variable.
		//performing the operation of loading the properties file in the memory.
		//static block will be executed! Once during class loading time..
		
		File configFile = new File(System.getProperty("user.dir") + File.separator+"src"+
				File.separator+"test"+File.separator+"resources"+File.separator+"config"+File.separator+"config.properties");	
		
		FileReader fileReader = null;
		try {
			 fileReader = new FileReader(configFile);
			 prop.load(fileReader);
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