package com.foundation4u.util;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeServiceHelper {

	private static final Logger log = LoggerFactory.getLogger(EmployeeServiceHelper.class);
	
	public static Properties getPropertiesFile(){
		Properties properties = new Properties();
		try {
			properties.load(EmployeeServiceHelper.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			log.error("error in loading properties file: "+e);
		}
		
		return properties;
	}
}
