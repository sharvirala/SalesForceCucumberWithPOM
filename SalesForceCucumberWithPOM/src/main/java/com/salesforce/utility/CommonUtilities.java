package com.salesforce.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.salesforce.utility.Constants;

public class CommonUtilities 
{
	public static String getPropertyValue(String key) throws IOException
	{
	String path=Constants.APPLICATION_PROPERTIES_PATH;
	FileInputStream fi=new FileInputStream(new File(path));
	
	Properties pf=new Properties();
	pf.load(fi);
	String value=pf.getProperty(key);
	return value;
	}
	
	
	
	
}
