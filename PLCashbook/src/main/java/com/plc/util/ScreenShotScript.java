package com.plc.util;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenShotScript {
	private String dateTime;
	
	//To get the current system date and time
	public String getDateTime(String dateTimeFormat){
		try{
			DateFormat df = new SimpleDateFormat(dateTimeFormat);
			Date dt = new Date();
			dateTime = df.format(dt);
			return dateTime;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void getScreenShot(String dateTimeFormat, String imgFileName, String imgType) throws Exception{
		
		try{
			dateTime = new ScreenShotScript().getDateTime(dateTimeFormat);
			TakesScreenshot ts = (TakesScreenshot)InitializeDriver.driver;
			File filePath = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(filePath, new File(".\\Screenshot\\" + imgFileName + " " + dateTime + "." + imgType));
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
	}
	
}
